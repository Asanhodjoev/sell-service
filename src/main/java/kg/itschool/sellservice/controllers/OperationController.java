package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.InputDataForOperation;
import kg.itschool.sellservice.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/operation")
public class OperationController {
    @Autowired
    private OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }
    @PostMapping("/provideOperation")
    public ResponseEntity<?> provideOperation(@RequestHeader String token, @RequestBody List<InputDataForOperation> sellingList){
        return operationService.provideOperation(token, sellingList);
    }

    @GetMapping("/payment")
    public ResponseEntity<?> payment(@RequestHeader String token, @RequestParam Long operation_id, @RequestParam double cash){
        return operationService.payment(token,operation_id,cash);
    }
}
