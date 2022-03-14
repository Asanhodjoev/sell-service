package kg.itschool.sellservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleException extends RuntimeException{
    String title;

    public SaleException(String message, String title) {
        super(message);
        this.title = title;
    }
}
