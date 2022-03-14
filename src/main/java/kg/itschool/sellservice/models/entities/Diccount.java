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
public class Diccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="dicount")
    private Double dicount = 0.0;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private boolean active;
    @ManyToOne
    private Product products;
}
