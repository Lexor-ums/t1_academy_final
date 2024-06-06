package t1.limitservice.aop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import t1.limitapi.dto.response.ErrorResponse;
import t1.limitservice.exception.LimitExhaustedException;

import static t1.limitservice.enums.ErrorCode.LIMIT_EXCEEDED;
import static t1.limitservice.enums.ErrorCode.UNDEFINED_ERROR;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({LimitExhaustedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse processLimitExceedingException(LimitExhaustedException ex) {
        return new ErrorResponse(LIMIT_EXCEEDED.toString(), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse processRunTimeException(RuntimeException ex) {
        return new ErrorResponse(UNDEFINED_ERROR.toString(), ex.getMessage());
    }
}