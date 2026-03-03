package com.green.greengram.configuration.exception;

import com.green.greengram.configuration.model.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Validation 예외가 발생되었을 때 캐치
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
                                                                , HttpHeaders headers
                                                                , HttpStatusCode statusCode
                                                                , WebRequest request) {
        List<ValidationError> errors = getValidationError(ex);


        List<String> messages = errors.stream().map(item -> String.format("%s: %s", item.getField(), item.getMessage())).toList();
        StringBuilder sb = new StringBuilder();
        for(String message : messages){
            sb.append(message);
            sb.append("\n");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResultResponse<>(sb.toString(), errors.toString()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResultResponse<String>> handleResponseStatusException(ResponseStatusException ex) {
        log.error("ResponseStatusException: {}", ex.getReason());

        String statusMessage = HttpStatus.valueOf(ex.getStatusCode().value()).getReasonPhrase();

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ResultResponse<>(statusMessage, ex.getReason()));
    }

    private List<ValidationError> getValidationError(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<ValidationError> result = new ArrayList<>(fieldErrors.size());
        for(FieldError fieldError : fieldErrors){
            result.add(ValidationError.of(fieldError));
        }
        return result;
        //return fieldErrors.stream().map(item -> ValidationError.of(item)).toList();
    }


}