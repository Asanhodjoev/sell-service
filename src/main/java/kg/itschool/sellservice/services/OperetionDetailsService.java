package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.entities.OperationDetails;
import kg.itschool.sellservice.models.entities.Operations;

import java.util.List;

public interface OperetionDetailsService {
    public void saveOperationDetails(OperationDetails operationDetails);

    List<OperationDetails> findAllOperetionsDeteils(Operations operations);
}
