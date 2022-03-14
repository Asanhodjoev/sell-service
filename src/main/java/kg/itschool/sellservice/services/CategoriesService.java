package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.CategoryRequestDto;
import kg.itschool.sellservice.models.entities.Categories;
import org.springframework.http.ResponseEntity;

public interface CategoriesService {
    ResponseEntity<?> saveCategory(String token, CategoryRequestDto categoryRequestDto);
    Categories getById(Long id);
    Categories getCategory(Long id);
}
