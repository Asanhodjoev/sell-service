package kg.itschool.sellservice.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="price")
    private Double price = 0.0;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private boolean active;
    @ManyToOne
    private Product products;

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", price=" + price +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", active=" + active +
                ", products=" + products +
                '}';
    }
}
