package kg.itschool.sellservice.dao;


import kg.itschool.sellservice.models.entities.Codes;
import kg.itschool.sellservice.models.entities.User;
import kg.itschool.sellservice.models.enums.CodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends JpaRepository<Codes,Long> {


    Codes findByUserAndStatus(User user, CodeStatus codeStatus);


}
