package kg.itschool.sellservice.services.impl;


import kg.itschool.sellservice.dao.DicountRepo;

import kg.itschool.sellservice.exceptions.SaleException;
import kg.itschool.sellservice.mappers.DicountMapper;
import kg.itschool.sellservice.models.dtos.DicountDto;
import kg.itschool.sellservice.models.entities.Diccount;
import kg.itschool.sellservice.models.entities.Product;
import kg.itschool.sellservice.services.DicountService;
import kg.itschool.sellservice.services.ProductService;
import kg.itschool.sellservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DicountServiceImpl implements DicountService {
    @Autowired
    private DicountRepo dicountRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public DicountServiceImpl(DicountRepo dicountRepo, UserService userService, ProductService productService) {
        this.dicountRepo = dicountRepo;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<?> saveDicount(String token, DicountDto dicountDto) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
//        DicountEntitiDto dicountEntitiDto = new DicountEntitiDto();
//        dicountEntitiDto.setDicount(dicountDto.getDicount());
//        dicountEntitiDto.setStart_date(dicountDto.getStart_date());
//        dicountEntitiDto.setEnd_date(dicountDto.getEnd_date());
//            dicountEntitiDto.setProducts(product);
        Product product = productService.getById(dicountDto.getId_products());
        if(Objects.isNull(product)) {
            throw new SaleException("Ошибка", "Такого продукта нету");
        }
        List<Diccount> diccountList = dicountRepo.findAllByProducts(product);
        if(Objects.nonNull(diccountList) && !diccountList.isEmpty()) {
            diccountList.stream().filter(x -> x.getStart_date().isBefore(dicountDto.getStart_date()) && x.getEnd_date().isAfter(dicountDto.getEnd_date())
                    || x.getStart_date().isAfter(dicountDto.getStart_date()) && x.getEnd_date().isBefore(dicountDto.getEnd_date())
                    || x.getStart_date().isAfter(dicountDto.getStart_date()) && x.getStart_date().isBefore(dicountDto.getEnd_date())
                    || x.getEnd_date().isAfter(dicountDto.getStart_date()) && x.getEnd_date().isBefore(dicountDto.getEnd_date())
                    || x.getStart_date().isEqual(dicountDto.getStart_date()) || x.getEnd_date().isEqual(dicountDto.getEnd_date()))
                    .forEach(x -> {x.setActive(false);
                        dicountRepo.save(x);});
        }
        Diccount diccount = new Diccount();
        diccount.setDicount(dicountDto.getDicount());
        diccount.setStart_date(dicountDto.getStart_date());
        diccount.setEnd_date(dicountDto.getEnd_date());
        diccount.setActive(true);
        diccount.setProducts(product);
        diccount = dicountRepo.save(diccount);
        return ResponseEntity.ok(DicountMapper.INSTANCE.dicountToDicountResponseDto(diccount));
    }

    @Override
    public double findActualDicount(Long id) {
        try {
            double z = dicountRepo.findDicountByProduct(id);
            return z;
        } catch (Exception e){
            return 0.0;
        }
    }
}
