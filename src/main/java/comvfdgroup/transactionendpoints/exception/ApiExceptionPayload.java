package comvfdgroup.transactionendpoints.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiExceptionPayload {
    private String message;
    private HttpStatus httpStatus;
}
