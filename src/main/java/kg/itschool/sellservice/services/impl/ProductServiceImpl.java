package kg.itschool.sellservice.services.impl;


import kg.itschool.sellservice.dao.ProductRepo;
import kg.itschool.sellservice.mappers.ProductMapper;
import kg.itschool.sellservice.models.dtos.CategoryIdDto;
import kg.itschool.sellservice.models.dtos.ProductInfoDto;
import kg.itschool.sellservice.models.dtos.ProductRequestDto;
import kg.itschool.sellservice.models.dtos.ProductResponseDto;
import kg.itschool.sellservice.models.entities.Categories;
import kg.itschool.sellservice.models.entities.OperationDetails;
import kg.itschool.sellservice.models.entities.Product;
import kg.itschool.sellservice.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoriesService categoriesService;
//    @Autowired
//    private PriceService priceService;
//    @Autowired
//    private DicountService dicountService;


    public ProductServiceImpl(ProductRepo productRepo, UserService userService, CategoriesService categoriesService) {
        this.productRepo = productRepo;
        this.userService = userService;
        this.categoriesService = categoriesService;
    }

    public Product getById(Long id){
        return productRepo.getById(id);
    }

    @Override
    public ResponseEntity<?> getActualById(String token, CategoryIdDto categoryIdDto) {
//        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
//        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)){
//            return responseEntity;
//        }
//        Categories categories = categoriesService.getCategory(categoryIdDto.getId());
//        if(Objects.isNull(categories)) {
//            return ResponseEntity.ok("Этой категории нет!");
//        }
//
////        List<Product> productList = productRepo.findAllByCategoriesAndActive(categories, true);
//        List<ProductInfoDto> productInfoDtoList = productRepo.getProductInfoDto(categories.getId());

        return null;
    }

    @Override
    public List<Product> getByCategory(Categories categories) {
        return productRepo.findAllByCategoriesAndActive(categories, true);
    }

    @Override
    public Product findByBarcode(String barcode) {
        return productRepo.findByBarcode(barcode);
    }



    @Override
    public ResponseEntity<?> saveProduct(String token, ProductRequestDto productRequestDto) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        Product product = productRepo.getByName(productRequestDto.getName());

        if(Objects.nonNull(product)){
            return ResponseEntity.ok("Этот продукт уже есть!");
        }

        Product product1 = new Product();
        product1.setName(productRequestDto.getName());
        product1.setActive(true);
        Categories categories = categoriesService.getById(productRequestDto.getCategory_id());

        if(Objects.isNull(categories)){
            return ResponseEntity.ok("Этой категории нет!");
        }

        product1.setCategories(categories);
        product1.setBarcode(generateRandomCode());
        product1 = productRepo.save(product1);
        return ResponseEntity.ok(ProductMapper.INSTANCE.productToProductResponseDto(product1));
    }

    private String generateRandomCode() {
        int code = (int) (Math.random()*(9999-1000+1)+1000);
        int code1 = (int) (Math.random()*(9999-1000+1)+1000);
        int code2 = (int) (Math.random()*(9999-1000+1)+1000);
        return String.valueOf(code)+String.valueOf(code1)+String.valueOf(code2);
    }
}
