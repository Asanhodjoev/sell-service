package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.CodeEntitiesDto;
import kg.itschool.sellservice.models.dtos.CodeRequestDto;
import kg.itschool.sellservice.models.dtos.CodeResponseDto;
import kg.itschool.sellservice.models.entities.Codes;
import kg.itschool.sellservice.models.entities.User;
import org.springframework.http.ResponseEntity;

public interface CodeService {
   CodeResponseDto createCode(User user);


   CodeEntitiesDto createCodeEntitiesDto(User user);

   void saveCode(CodeEntitiesDto codeEntitiesDto);


   void saveCodeAndStatusAprowed(CodeEntitiesDto codeEntitiesDto);
}