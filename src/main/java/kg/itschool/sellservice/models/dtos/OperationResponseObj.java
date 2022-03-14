package kg.itschool.sellservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OperationResponseObj {

    private String productName;
    private String barcode;
    private int quantity;
    private Double price;
    private Double discount;
    private Double amount;
}
