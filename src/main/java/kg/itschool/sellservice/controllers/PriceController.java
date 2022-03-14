package kg.itschool.sellservice.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kg.itschool.sellservice.models.dtos.PriceDto;
import kg.itschool.sellservice.models.dtos.PriceRequestDto;
import kg.itschool.sellservice.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/price")
public class PriceController {
    private PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePrice(@RequestHeader String token, @RequestBody PriceRequestDto priceRequestDto){
        return priceService.createPrice(priceRequestDto);
    }
}
