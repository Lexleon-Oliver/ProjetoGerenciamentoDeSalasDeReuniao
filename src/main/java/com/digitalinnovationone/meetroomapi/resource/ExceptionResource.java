package com.digitalinnovationone.meetroomapi.resource;

import com.digitalinnovationone.meetroomapi.dto.response.ErrorMessageResponse;
import com.digitalinnovationone.meetroomapi.exception.ResourceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}
