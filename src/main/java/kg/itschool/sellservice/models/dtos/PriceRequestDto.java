package kg.itschool.sellservice.models.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceRequestDto {
    Double price;
    String start_date;
    String end_date;
    Long products_id;

    @Override
    public String toString() {
        return "PriceRequestDto{" +
                "price=" + price +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", products_id=" + products_id +
                '}';
    }
}
