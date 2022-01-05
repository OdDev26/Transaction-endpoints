package comvfdgroup.transactionendpoints.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Data
public class SuccessMessageDto {
    private String message;
    private HttpStatus status;
}
