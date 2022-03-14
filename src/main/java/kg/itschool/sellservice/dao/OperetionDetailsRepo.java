package kg.itschool.sellservice.dao;


import kg.itschool.sellservice.models.entities.OperationDetails;
import kg.itschool.sellservice.models.entities.Operations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperetionDetailsRepo extends JpaRepository<OperationDetails, Long> {
    List<OperationDetails> findAllByOperations(Operations operations);
}
