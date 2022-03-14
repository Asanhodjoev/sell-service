package kg.itschool.sellservice.models.dtos;

import kg.itschool.sellservice.models.entities.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceResponseDto {
    Long id;
    Double price;
    LocalDateTime start_date;
    LocalDateTime end_date;
    boolean active;
    Long products;

    @Override
    public String toString() {
        return "PriceResponseDto{" +
                "id=" + id +
                ", price=" + price +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", active=" + active +
                ", products=" + products +
                '}';
    }
}
