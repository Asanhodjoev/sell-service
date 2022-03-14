package kg.itschool.sellservice.controllers;


import kg.itschool.sellservice.models.dtos.UserRequestDto;
import kg.itschool.sellservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }
    @PostMapping("/send")
    public ResponseEntity<?> sendCode(@RequestParam String login){
        return userService.sendCode(login);
    }

    @PostMapping("/active_code")
    public ResponseEntity<?> configCode(@RequestParam String login, @RequestParam String code){
        return userService.configCode(login, code);
    }
    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestHeader String token) {
        return userService.verifyLogin(token);
    }

}
