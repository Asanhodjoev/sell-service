package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.DicountResponseDto;
import kg.itschool.sellservice.models.entities.Diccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DicountMapper {
    DicountMapper INSTANCE = Mappers.getMapper(DicountMapper.class);
    default DicountResponseDto dicountToDicountResponseDto(Diccount diccount){
        DicountResponseDto dicountResponseDto = new DicountResponseDto();
        dicountResponseDto.setId(diccount.getId());
        dicountResponseDto.setDicount(diccount.getDicount());
        dicountResponseDto.setStart_date(diccount.getStart_date());
        dicountResponseDto.setEnd_date(diccount.getEnd_date());
        dicountResponseDto.setProducts(diccount.getProducts().getId());
        return dicountResponseDto;
    }
}
