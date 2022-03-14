package kg.itschool.sellservice.services.impl;


import kg.itschool.sellservice.dao.CodeRepo;
import kg.itschool.sellservice.mappers.CodeMapper;
import kg.itschool.sellservice.models.dtos.CodeEntitiesDto;
import kg.itschool.sellservice.models.dtos.CodeRequestDto;
import kg.itschool.sellservice.models.dtos.CodeResponseDto;
import kg.itschool.sellservice.models.entities.Codes;
import kg.itschool.sellservice.models.entities.User;
import kg.itschool.sellservice.models.enums.CodeStatus;
import kg.itschool.sellservice.services.CodeService;
import kg.itschool.sellservice.services.SendSimpleMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeRepo codeRepo;
    @Autowired
    private SendSimpleMassage simpleMassage;

    public CodeServiceImpl(CodeRepo codeRepo, SendSimpleMassage simpleMassage) {
        this.codeRepo = codeRepo;
        this.simpleMassage = simpleMassage;
    }

    @Override
    public CodeResponseDto createCode(User user) {
        Codes codes1 = codeRepo.findByUserAndStatus(user, CodeStatus.NEW);
        if(Objects.nonNull(codes1)) {
            codes1.setStatus(CodeStatus.CANCELLED);
            codes1 = codeRepo.save(codes1);
        }
        Codes code = new Codes();
        code.setUser(user);
        int randomCode = generateRandomCode();
        simpleMassage.sendSimpleMassage(user.getLogin(), String.valueOf(randomCode));
        String hashedCode = BCrypt.hashpw(Integer.toString(randomCode), BCrypt.gensalt());
        code.setCode(hashedCode);
        code.setStatus(CodeStatus.NEW);
        code.setEnd_date(LocalDateTime.now().plusMinutes(3));
        code = codeRepo.save(code);
        CodeResponseDto codeResponseDto = new CodeResponseDto();
        codeResponseDto.setId(code.getId());
        codeResponseDto.setMessage("Ваш код отправлен на почту!");

        return codeResponseDto;
    }


    private int generateRandomCode() {
        int code = (int) (Math.random()*(9999-1000+1)+1000);
        return code;
    }

//    public ResponseEntity<?> activeCode(User user, String code){
//        Codes codes = codeRepo.findByUser(user);
//        if(LocalDateTime.now().isAfter(codes.getEnd_date())){
//            return ResponseEntity.ok("Время действия кода истекло получите новый");
//        }
//        if (!BCrypt.checkpw(code, codes.getCode())){
//
//        }
//    }
    @Override
    public CodeEntitiesDto createCodeEntitiesDto(User user) {
        Codes codes = codeRepo.findByUserAndStatus(user, CodeStatus.NEW);
        return CodeMapper.INSTANCE.codeToCodeEntitiesDto(codes);
    }

    @Override
    public void saveCode(CodeEntitiesDto codeEntitiesDto) {
        Codes codes = CodeMapper.INSTANCE.CodeEntitiesDtoToCodes(codeEntitiesDto);
        codes = codeRepo.save(codes);
    }

    @Override
    public void saveCodeAndStatusAprowed(CodeEntitiesDto codeEntitiesDto) {
        Codes codes = CodeMapper.INSTANCE.CodeEntitiesDtoToCodes(codeEntitiesDto);
        codes.setStatus(CodeStatus.APPROVED);
        codes = codeRepo.save(codes);
    }

}
