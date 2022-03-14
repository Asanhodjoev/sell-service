package kg.itschool.sellservice.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kg.itschool.sellservice.models.dtos.CategoryIdDto;
import kg.itschool.sellservice.models.dtos.ProductRequestDto;
import kg.itschool.sellservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestHeader String token, @RequestBody ProductRequestDto productRequestDto){
        return productService.saveProduct(token, productRequestDto);
    }
    @GetMapping("/get_product")
    public ResponseEntity<?> getActualProduct(@RequestHeader String token, @RequestBody CategoryIdDto categoryIdDto){
        return productService.getActualById(token, categoryIdDto);
    }

}
