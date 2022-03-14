package kg.itschool.sellservice.models.dtos;

import lombok.Data;

@Data
public class InputDataForOperation {
    private String barcode;
    private int quantity;
}
