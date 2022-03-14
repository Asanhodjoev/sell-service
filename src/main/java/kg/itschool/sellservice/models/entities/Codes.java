package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.enums.CodeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Codes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String code;
    @CreationTimestamp
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    @Enumerated(EnumType.STRING)
    private CodeStatus status;
    @ManyToOne
    private User user;
}
