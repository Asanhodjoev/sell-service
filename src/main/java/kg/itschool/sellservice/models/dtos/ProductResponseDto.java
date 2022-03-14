package kg.itschool.sellservice.models.dtos;

import kg.itschool.sellservice.models.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private String barcode;
    private Long category_id;
    private boolean active;
}
