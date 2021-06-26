package ar.edu.unq.desapp.grupoK.backenddesappapi;

import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.ClientAccessException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.RepeatedElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.TokenValidationException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.WrongApiKeyException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(WrongApiKeyException.class)
    public ResponseEntity handleWrongApiKeyException(WrongApiKeyException itemNotFoundException){
        return ResponseEntity.status(400).body(new ErrorResponseDTO(itemNotFoundException.getMessage()));
    }

    @ExceptionHandler(ClientAccessException.class)
    public ResponseEntity handleClientAccessException(ClientAccessException clientAccessException){
        return ResponseEntity.status(400).body(new ErrorResponseDTO(clientAccessException.getMessage()));
    }

    @ExceptionHandler(InexistentElementException.class)
    public ResponseEntity handleInexistentElementException(InexistentElementException inexistentElementException){
        return ResponseEntity.status(400).body(new ErrorResponseDTO(inexistentElementException.getMessage()));
    }

    @ExceptionHandler(RepeatedElementException.class)
    public ResponseEntity handleRepeatedElementException(RepeatedElementException repeatedElementException){
        return ResponseEntity.status(400).body(new ErrorResponseDTO(repeatedElementException.getMessage()));
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity handleTokenValidationException(TokenValidationException tokenValidationException){
        return ResponseEntity.status(400).body(new ErrorResponseDTO(tokenValidationException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){

        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(400).body(new ErrorResponseDTO(errors));
    }
}
