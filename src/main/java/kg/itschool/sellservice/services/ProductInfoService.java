package kg.itschool.sellservice.services;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kg.itschool.sellservice.models.dtos.CategoryIdDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ProductInfoService {
    ResponseEntity<?> getProductInfo(String token, CategoryIdDto categoryIdDto);
}
