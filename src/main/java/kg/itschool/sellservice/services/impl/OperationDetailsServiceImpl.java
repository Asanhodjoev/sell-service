package kg.itschool.sellservice.services.impl;


import kg.itschool.sellservice.dao.OperationRepo;
import kg.itschool.sellservice.dao.OperetionDetailsRepo;
import kg.itschool.sellservice.models.entities.OperationDetails;
import kg.itschool.sellservice.models.entities.Operations;
import kg.itschool.sellservice.services.OperetionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationDetailsServiceImpl implements OperetionDetailsService {
    @Autowired
    private OperetionDetailsRepo operetionDetailsRepo;

    public OperationDetailsServiceImpl(OperetionDetailsRepo operetionDetailsRepo) {
        this.operetionDetailsRepo = operetionDetailsRepo;
    }

    @Override
    public void saveOperationDetails(OperationDetails operationDetails) {
        operetionDetailsRepo.save(operationDetails);
    }

    @Override
    public List<OperationDetails> findAllOperetionsDeteils(Operations operations) {
        return operetionDetailsRepo.findAllByOperations(operations);
    }
}
