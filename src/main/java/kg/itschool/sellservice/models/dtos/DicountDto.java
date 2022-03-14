package kg.itschool.sellservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DicountDto {
    private double dicount;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private Long id_products;
}
