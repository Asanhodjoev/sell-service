package kg.itschool.sellservice.dao;


import kg.itschool.sellservice.models.dtos.UserResponseDto;
import kg.itschool.sellservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users where login=?1",nativeQuery = true)
    User getUserLogin(String login);

    User findByLogin(String login);
}
