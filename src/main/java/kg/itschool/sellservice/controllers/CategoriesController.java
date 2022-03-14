package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.CategoryRequestDto;
import kg.itschool.sellservice.services.CategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoriesController {

    private CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestHeader String token, @RequestBody CategoryRequestDto categoryRequestDto){
        return categoriesService.saveCategory(token, categoryRequestDto);
    }

}
