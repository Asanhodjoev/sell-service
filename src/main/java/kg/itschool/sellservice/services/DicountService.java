package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.DicountDto;

import org.springframework.http.ResponseEntity;

public interface DicountService {
    ResponseEntity<?> saveDicount(String token, DicountDto dicountDto);

    double findActualDicount(Long id);
}
