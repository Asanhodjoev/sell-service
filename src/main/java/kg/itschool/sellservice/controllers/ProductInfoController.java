package kg.itschool.sellservice.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kg.itschool.sellservice.models.dtos.CategoryIdDto;
import kg.itschool.sellservice.services.ProductInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/info")
public class ProductInfoController {

    private ProductInfoService productInfoService;

    public ProductInfoController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @GetMapping("/get_product_info")
    public ResponseEntity<?> getProductInfo(@RequestHeader String token, @RequestBody CategoryIdDto categoryIdDto){
        return productInfoService.getProductInfo(token, categoryIdDto);
    }
}
