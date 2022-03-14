package kg.itschool.sellservice.services;


import kg.itschool.sellservice.models.dtos.UserRequestDto;
import kg.itschool.sellservice.models.entities.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createUser(UserRequestDto userRequestDto);
    ResponseEntity<?> sendCode(String login);
    boolean userLockOutChecking(User user);
    ResponseEntity<?> configCode(String login, String code);

    ResponseEntity<?> verifyLogin(String token);
    String verifyLoginString(String token);

    User findByLogin(String login);
}
