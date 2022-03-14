package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.InputDataForOperation;
import kg.itschool.sellservice.models.entities.Operations;
import kg.itschool.sellservice.models.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationService {
    ResponseEntity<?> provideOperation(String token, List<InputDataForOperation> sellingList);


    ResponseEntity<?> payment(String token, Long operation_id, double cash);

    List<Operations> getOperationByUser(User user);
}
