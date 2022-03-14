package kg.itschool.sellservice.models.dtos;

import kg.itschool.sellservice.models.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DicountResponseDto {
    private Long id;
    private Double dicount;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private Long products;
}
