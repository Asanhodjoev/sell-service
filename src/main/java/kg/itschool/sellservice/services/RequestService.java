package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.CodeEntitiesDto;

public interface RequestService {
    void saveRequest(CodeEntitiesDto codeEntitiesDto, boolean b);

    int countOfError(CodeEntitiesDto codeEntitiesDto);
}
