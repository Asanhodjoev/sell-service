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
public class PriceDto {

    Double price;
    LocalDateTime start_date;
    LocalDateTime end_date;
    Long products_id;

    @Override
    public String toString() {
        return "PriceDto{" +
                "price=" + price +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", products_id=" + products_id +
                '}';
    }
}
