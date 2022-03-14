package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.PriceResponseDto;
import kg.itschool.sellservice.models.entities.Prices;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
    default PriceResponseDto priceToPriceResponseDto (Prices prices){
        PriceResponseDto priceResponseDto = new PriceResponseDto();
        priceResponseDto.setId(prices.getId());
        priceResponseDto.setPrice(prices.getPrice());
        priceResponseDto.setStart_date(prices.getStart_date());
        priceResponseDto.setEnd_date(prices.getEnd_date());
        priceResponseDto.setActive(prices.isActive());
        priceResponseDto.setProducts(prices.getProducts().getId());
        return priceResponseDto;
    }
}
