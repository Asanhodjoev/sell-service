package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Codes;
import kg.itschool.sellservice.models.entities.Requests;
import org.apache.coyote.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Requests, Long> {


    @Query(value = "SELECT COUNT(*) FROM requests where codes=?1",nativeQuery = true)
    int getCount(Codes codes);
}
