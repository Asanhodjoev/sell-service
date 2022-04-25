package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.models.dtos.LoginDto;
import kg.itschool.sellservice.models.dtos.UserProductInfoDto;
import kg.itschool.sellservice.models.dtos.UserProductInfoObj;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserProductsInfoServiceImpl implements UserProductsInfoService {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private OperetionDetailsService operetionDetailsService;
    @Autowired
    private OperationService operationService;

    @Override
    public ResponseEntity<?> getProductInfo(String token, String login) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);

        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }

        User user = userService.findByLogin(login);


        List<UserProductInfoObj> userProductInfoObjList = new ArrayList<>();
        Double sum = 0.0;

        List<Operations> operationsList = operationService.getOperationByUser(user);
        Set<String> productNameList = new HashSet<>();
        for (Operations operations : operationsList) {

            sum = sum + operations.getTotal_price();

            List<OperationDetails> operationDetailsList = operetionDetailsService.findAllOperetionsDeteils(operations);

            for (OperationDetails operationDetails : operationDetailsList) {
                UserProductInfoObj userProductInfoObj = new UserProductInfoObj();
                userProductInfoObj.setProductName(operationDetails.getProducts().getName());
                productNameList.add(operationDetails.getProducts().getName());
                userProductInfoObj.setAmount(operationDetails.getAmount());
                userProductInfoObj.setQuantity(operationDetails.getQuantity());
                userProductInfoObjList.add(userProductInfoObj);
            }
        }
        List<UserProductInfoObj> userProductInfoObjList2 = new ArrayList<>();
        for (String s : productNameList) {
            UserProductInfoObj userProductInfoObj = new UserProductInfoObj();
            userProductInfoObj.setProductName(s);
            for (UserProductInfoObj u : userProductInfoObjList) {
                if (u.getProductName().equals(s)) {
                    userProductInfoObj.setAmount(userProductInfoObj.getAmount()+u.getAmount());
                    userProductInfoObj.setQuantity(userProductInfoObj.getQuantity()+u.getQuantity());
                }
            }
            userProductInfoObjList2.add(userProductInfoObj);
        }
        UserProductInfoDto userProductInfoDto = new UserProductInfoDto(userProductInfoObjList2, sum);
        return ResponseEntity.ok(userProductInfoDto);
    }
}
