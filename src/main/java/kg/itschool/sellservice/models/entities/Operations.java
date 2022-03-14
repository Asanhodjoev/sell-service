package kg.itschool.sellservice.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime add_date;
    @Column(name="total_price")
    private Double total_price = 0.0;
    @Column(name="change")
    private Double change = 0.0;
    @ManyToOne
    private User user;
    @Column(name="cash")
    private Double cash = 0.0;
}
