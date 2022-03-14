package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.CategoryDto;
import kg.itschool.sellservice.models.entities.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDto categoriesToCategoryDto (Categories categories);
    Categories categoryDtoToCategories (CategoryDto categoryDto);
}
