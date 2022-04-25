package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.CategoryIdDto;
import kg.itschool.sellservice.models.dtos.ProductRequestDto;
import kg.itschool.sellservice.models.entities.Categories;
import kg.itschool.sellservice.models.entities.OperationDetails;
import kg.itschool.sellservice.models.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> saveProduct(String token, ProductRequestDto productRequestDto);
    Product getById(Long id);

    ResponseEntity<?> getActualById(String token, CategoryIdDto categoryIdDto);

    List<Product> getByCategory(Categories categories);

    Product findByBarcode(String barcode);


}
