package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.CodeEntitiesDto;
import kg.itschool.sellservice.models.entities.Codes;
import org.aspectj.apache.bcel.classfile.Code;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CodeMapper {
    CodeMapper INSTANCE = Mappers.getMapper(CodeMapper.class);
    CodeEntitiesDto codeToCodeEntitiesDto(Codes codes);
    Codes CodeEntitiesDtoToCodes(CodeEntitiesDto codeEntitiesDto);
}
