package kg.itschool.sellservice.models.dtos;

import lombok.Data;

@Data
public class UserProductInfoObj {
    private String productName;
    private Double amount;
    private int quantity;
}
