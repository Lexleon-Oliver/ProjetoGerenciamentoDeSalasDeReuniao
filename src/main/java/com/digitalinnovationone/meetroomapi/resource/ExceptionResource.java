package com.digitalinnovationone.meetroomapi.resource;

import com.digitalinnovationone.meetroomapi.dto.response.ErrorMessageResponse;
import com.digitalinnovationone.meetroomapi.exception.ResourceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionResource extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorMessageResponse resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        return new ErrorMessageResponse(404, ex.getLocalizedMessage(), "Not Found", "Resource not found");
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessageResponse exceptionResource(Exception ex, WebRequest request){
        return new ErrorMessageResponse(500, ex.getLocalizedMessage(), "Internal Server Error","Internal Server Error Exception");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(400, ex.getFieldError().getDefaultMessage(), "Invalid Argument","Invalid Request Exception");
        return handleExceptionInternal(ex, errorMessageResponse, headers, HttpStatus.BAD_REQUEST, request);
    }
}
