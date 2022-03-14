package kg.itschool.sellservice.models.dtos;

import kg.itschool.sellservice.models.entities.User;
import kg.itschool.sellservice.models.enums.CodeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeResponseDto {
    Long id;
    private String message;
}
