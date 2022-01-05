package comvfdgroup.transactionendpoints.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiRequestExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleAPiRequestException(ApiRequestException exception){
        ApiExceptionPayload exceptionPayload= new ApiExceptionPayload(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exceptionPayload,HttpStatus.BAD_REQUEST);
    }
}
