package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.ProductResponseDto;
import kg.itschool.sellservice.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    default ProductResponseDto productToProductResponseDto (Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setBarcode(product.getBarcode());
        productResponseDto.setCategory_id(product.getCategories().getId());
        productResponseDto.setActive(product.isActive());
        return productResponseDto;
    }
}
