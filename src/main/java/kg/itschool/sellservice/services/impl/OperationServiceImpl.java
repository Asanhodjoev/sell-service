package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.OperationRepo;
import kg.itschool.sellservice.models.dtos.InputDataForOperation;
import kg.itschool.sellservice.models.dtos.OperationResponseDto;
import kg.itschool.sellservice.models.dtos.OperationResponseObj;
import kg.itschool.sellservice.models.entities.OperationDetails;
import kg.itschool.sellservice.models.entities.Operations;
import kg.itschool.sellservice.models.entities.Product;
import kg.itschool.sellservice.models.entities.User;
import kg.itschool.sellservice.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationRepo operationRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private DicountService dicountService;
    @Autowired
    private OperetionDetailsService operetionDetailsService;


    @Override
    public ResponseEntity<?> provideOperation(String token, List<InputDataForOperation> sellingList) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        Operations operations = new Operations();
        operations = operationRepo.save(operations);

        double sum = 0.0;

        for (InputDataForOperation i : sellingList) {

            OperationDetails operationDetails = new OperationDetails();
            Product product = productService.findByBarcode(i.getBarcode());
            operationDetails.setProducts(product);
            operationDetails.setOperations(operations);
            operationDetails.setQuantity(i.getQuantity());
            double price = priceService.findActualPrice(product.getId());
            System.out.println(price);
            double dicount = dicountService.findActualDicount(product.getId());
            System.out.println(dicount);
            double amount = price - (price/100 * dicount);
            amount = amount * i.getQuantity();
            operationDetails.setAmount(amount);
            operetionDetailsService.saveOperationDetails(operationDetails);

            sum = sum + (amount * i.getQuantity());
        }
        operations.setTotal_price(sum);

        String login = userService.verifyLoginString(token);
        User user = userService.findByLogin(login);

        operations.setUser(user);
        operations = operationRepo.save(operations);

        OperationResponseDto operationResponseDto = new OperationResponseDto();
        List<OperationResponseObj> operationResponseObjList = new ArrayList<>();

        List<OperationDetails> operationDetailsList = operetionDetailsService.findAllOperetionsDeteils(operations);

        for (OperationDetails operationDetails : operationDetailsList) {
            OperationResponseObj operationResponseObj = new OperationResponseObj();

            operationResponseObj.setProductName(operationDetails.getProducts().getName());
            operationResponseObj.setBarcode(operationDetails.getProducts().getBarcode());
            operationResponseObj.setQuantity(operationDetails.getQuantity());
            operationResponseObj.setAmount(operationDetails.getAmount());
            operationResponseObj.setPrice(priceService.findActualPrice(operationDetails.getProducts().getId()));
            operationResponseObj.setDiscount(dicountService.findActualDicount(operationDetails.getProducts().getId()));

            operationResponseObjList.add(operationResponseObj);
        }
        operationResponseDto.setOperationResponseObjList(operationResponseObjList);
        operationResponseDto.setSum(operations.getTotal_price());

        return ResponseEntity.ok(operationResponseDto);
    }

    @Override
    public ResponseEntity<?> payment(String token, Long operation_id, double cash) {

        Operations operations = operationRepo.getOperationsById(operation_id);
        if(cash<operations.getTotal_price()){
            return ResponseEntity.ok("Недостаточно средств");
        }
        operations.setCash(cash);
        operations.setChange(cash-operations.getTotal_price());
        operations = operationRepo.save(operations);
        return ResponseEntity.ok(operations);
    }

    @Override
    public List<Operations> getOperationByUser(User user) {
        return operationRepo.findAllByUser(user);
    }
}
