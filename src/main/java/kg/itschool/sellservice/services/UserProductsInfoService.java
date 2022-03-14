package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.LoginDto;
import org.springframework.http.ResponseEntity;

public interface UserProductsInfoService {
    ResponseEntity<?> getProductInfo(String token, String login);
}
