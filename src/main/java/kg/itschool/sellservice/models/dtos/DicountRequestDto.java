package kg.itschool.sellservice.models.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DicountRequestDto {
    double dicount;
    LocalDateTime start_date;
    LocalDateTime end_date;
    Long id_products;
}
