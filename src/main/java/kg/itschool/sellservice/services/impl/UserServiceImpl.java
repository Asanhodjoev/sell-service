package kg.itschool.sellservice.services.impl;


import io.jsonwebtoken.*;
import kg.itschool.sellservice.dao.UserRepo;
import kg.itschool.sellservice.mappers.UserMapper;
import kg.itschool.sellservice.models.dtos.*;
import kg.itschool.sellservice.models.entities.Codes;
import kg.itschool.sellservice.models.entities.User;
import kg.itschool.sellservice.models.enums.CodeStatus;
import kg.itschool.sellservice.services.CodeService;
import kg.itschool.sellservice.services.RequestService;
import kg.itschool.sellservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CodeService codeService;
    @Autowired
    private RequestService requestService;

    public UserServiceImpl(UserRepo userRepo, CodeService codeService, RequestService requestService) {
        this.userRepo = userRepo;
        this.codeService = codeService;
        this.requestService = requestService;
    }
    @Value("${jwtSecret}")
    private String secretKey;

    @Override
    public ResponseEntity<?> createUser(UserRequestDto userRequestDto) {
        User user1 = userRepo.getUserLogin(userRequestDto.getLogin());

        if(Objects.nonNull(user1)) {
            return ResponseEntity.ok("Вы уже зарегистрированы");
        }
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setLogin(userRequestDto.getLogin());
        user.setActive(false);
        user = userRepo.save(user);

        return ResponseEntity.ok(UserMapper.INSTANCE.userToUserResponseDto(user));
    }


    @Override
    public ResponseEntity<?> sendCode(String login) {
        User user = userRepo.getUserLogin(login);
        if(Objects.isNull(user)) {
            return ResponseEntity.ok("Логин не правильный или его нет!");
        }
        boolean check = userLockOutChecking(user);
        if(check) {
            SimpleDateFormat formatBlockDate = new SimpleDateFormat("hh:mm a");
            return ResponseEntity.ok("Вы ошиблись 3 раза и заблокированы, попробуйте в - "+formatBlockDate.format(user.getBlock_date()));
        }
        CodeResponseDto codeResponseDto = codeService.createCode(user);
        return ResponseEntity.ok(codeResponseDto);
    }

    @Override
    public boolean userLockOutChecking(User user) {
        if(Objects.nonNull(user.getBlock_date()) && LocalDateTime.now().isBefore(user.getBlock_date())) {
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<?> configCode(String login, String code) {
        User user = userRepo.getUserLogin(login);
        if(Objects.isNull(user)) {
            return ResponseEntity.ok("Логин не правильный или его нет!");
        }

        CodeEntitiesDto codeEntitiesDto = codeService.createCodeEntitiesDto(user);

        boolean check = userLockOutChecking(user);
        if(check) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return ResponseEntity.ok("Вы ошиблись 3 раза и заблокированы, запросите новый код и попробуйте в - "+user.getBlock_date().format(formatter));
        }
        if(Objects.isNull(codeEntitiesDto)){
            return ResponseEntity.ok("Вы не еще не получили код");
        }
        if(LocalDateTime.now().isAfter(codeEntitiesDto.getEnd_date())) {
            requestService.saveRequest(codeEntitiesDto, false);
            return ResponseEntity.ok("Время действия кода истекло получите новый");
        }
        if (!BCrypt.checkpw(code, codeEntitiesDto.getCode())) {
            requestService.saveRequest(codeEntitiesDto, false);

            if(requestService.countOfError(codeEntitiesDto) >= 3) {
                LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(3);
                user.setBlock_date(localDateTime);
                userRepo.save(user);

                codeEntitiesDto.setStatus(CodeStatus.FAILED);
                codeService.saveCode(codeEntitiesDto);
            }
            return ResponseEntity.ok("Код не совпадает");
        }
        requestService.saveRequest(codeEntitiesDto, true);
        codeService.saveCodeAndStatusAprowed(codeEntitiesDto);

        Calendar tokenTimeLive = Calendar.getInstance();
        tokenTimeLive.add(Calendar.HOUR, 3);

        String token =
                Jwts.builder()
                        .claim("login", login)
                        .setExpiration(
                                tokenTimeLive
                                        .getTime())
                        .signWith(
                                SignatureAlgorithm.HS256
                                , secretKey)
                        .compact();

        return ResponseEntity.ok("Пароль успешно набран, ваш токен: "+token);
    }

    @Override
    public ResponseEntity<?> verifyLogin(String token) {
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return ResponseEntity.ok(jwt.getBody().get("login"));

        } catch (ExpiredJwtException jwtException) {
            return new ResponseEntity<>("Время действия токена истек", HttpStatus.CONFLICT);

        } catch (UnsupportedJwtException jwtException) {
            return new ResponseEntity<>("Неподерживаемый токен", HttpStatus.CONFLICT);

        } catch (MalformedJwtException jwtException) {
            return new ResponseEntity<>("Некорректный токен", HttpStatus.CONFLICT);

        } catch (SignatureException signatureException) {
            return new ResponseEntity<>("Некорректная подпись в токене!", HttpStatus.CONFLICT);

        } catch (Exception exception) {
            return new ResponseEntity<>("unauthorized", HttpStatus.CONFLICT);
        }
    }
    public String verifyLoginString(String token) {

        Jws<Claims> jwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return (String) jwt.getBody().get("login");

    }

    @Override
    public User findByLogin(String login) {
        return userRepo.findByLogin(login);
    }
}
