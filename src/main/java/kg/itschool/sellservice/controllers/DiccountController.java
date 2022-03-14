package kg.itschool.sellservice.controllers;
import kg.itschool.sellservice.models.dtos.DicountDto;
import kg.itschool.sellservice.services.DicountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/diccount")
public class DiccountController {

    private DicountService dicountService;

    public DiccountController(DicountService dicountService) {
        this.dicountService = dicountService;
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveDicount(@RequestHeader String token, @RequestBody DicountDto dicountDto){
        return dicountService.saveDicount(token, dicountDto);
    }
}
