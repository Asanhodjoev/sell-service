package kg.itschool.sellservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SaleException.class)
    public ResponseEntity<?> handlerInviteException(SaleException saleException){
        return new ResponseEntity<>(new ResponseException(saleException.getTitle(), saleException.getMessage()), HttpStatus.ACCEPTED);
    }
}
