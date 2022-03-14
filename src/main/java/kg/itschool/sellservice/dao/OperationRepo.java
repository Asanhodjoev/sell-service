package kg.itschool.sellservice.dao;


import kg.itschool.sellservice.models.entities.Operations;
import kg.itschool.sellservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Operations,Long> {

    Operations getOperationsById(Long id);

    List<Operations> findAllByUser(User user);
}
