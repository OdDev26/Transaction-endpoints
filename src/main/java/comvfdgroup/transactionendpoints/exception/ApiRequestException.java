package comvfdgroup.transactionendpoints.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }
}
