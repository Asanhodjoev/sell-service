package kg.itschool.sellservice.services.impl;


import kg.itschool.sellservice.dao.CategoriesRepo;
import kg.itschool.sellservice.exceptions.SaleException;
import kg.itschool.sellservice.mappers.CategoryMapper;
import kg.itschool.sellservice.models.dtos.CategoryRequestDto;
import kg.itschool.sellservice.models.entities.Categories;
import kg.itschool.sellservice.services.CategoriesService;
import kg.itschool.sellservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepo categoriesRepo;
    @Autowired
    private UserService userService;

    public CategoriesServiceImpl(CategoriesRepo categoriesRepo, UserService userService) {
        this.categoriesRepo = categoriesRepo;
        this.userService = userService;
    }
    public Categories getCategory(Long id){
        return categoriesRepo.getById(id);
    }

    @Override
    public ResponseEntity<?> saveCategory(String token, CategoryRequestDto categoryRequestDto) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Categories categories1 = categoriesRepo.getByName(categoryRequestDto.getName());
        if(Objects.nonNull(categories1)){
            throw new SaleException("ошибка", "Эта категория уже есть!");
        }
        Categories categories = new Categories();
        categories.setName(categoryRequestDto.getName());
        categories.setActive(true);
        categories = categoriesRepo.save(categories);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.categoriesToCategoryDto(categories));
    }

    @Override
    public Categories getById(Long id) {
        return categoriesRepo.getById(id);
    }
}
