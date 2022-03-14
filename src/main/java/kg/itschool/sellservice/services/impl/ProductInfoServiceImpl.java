package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.ProductInfoRepo;
import kg.itschool.sellservice.dao.ProductRepo;
import kg.itschool.sellservice.exceptions.SaleException;
import kg.itschool.sellservice.models.dtos.CategoryIdDto;
import kg.itschool.sellservice.models.dtos.ProductInfoDto;
import kg.itschool.sellservice.models.entities.Categories;
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
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private DicountService dicountService;

    @Override

    public ResponseEntity<?> getProductInfo(String token, CategoryIdDto categoryIdDto) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Categories categories = categoriesService.getCategory(categoryIdDto.getId());
        if(Objects.isNull(categories)) {
            throw new SaleException("ошибка", "нет категории");
        }
        List<Product> productList = productService.getByCategory(categories);
        List<ProductInfoDto> productInfoDtoList = new ArrayList<>();
        for (Product s : productList) {
            ProductInfoDto productInfoDto = new ProductInfoDto();
            productInfoDto.setName(s.getName());
            productInfoDto.setDiscount(dicountService.findActualDicount(s.getId()));
            productInfoDto.setPrice(priceService.findActualPrice(s.getId()));
            productInfoDtoList.add(productInfoDto);
        }

        return ResponseEntity.ok(productInfoDtoList);
    }
}
