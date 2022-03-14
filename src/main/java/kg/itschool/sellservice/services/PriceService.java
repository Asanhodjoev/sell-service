package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.PriceDto;
import kg.itschool.sellservice.models.dtos.PriceRequestDto;
import kg.itschool.sellservice.models.entities.Product;
import org.springframework.http.ResponseEntity;

public interface PriceService {

    ResponseEntity<?> createPrice(PriceRequestDto priceRequestDto);

    double findActualPrice(Long product_id);
}
