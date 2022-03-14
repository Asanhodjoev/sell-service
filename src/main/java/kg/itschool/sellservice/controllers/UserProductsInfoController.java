package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.LoginDto;
import kg.itschool.sellservice.services.UserProductsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/userInfo")
public class UserProductsInfoController {

    @Autowired
    private UserProductsInfoService userProductsInfoService;

    public UserProductsInfoController(UserProductsInfoService userProductsInfoService) {
        this.userProductsInfoService = userProductsInfoService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getProductInfo(@RequestParam String token, @RequestParam String qwerty){
        return userProductsInfoService.getProductInfo(token, qwerty);
    }
}
