package ar.edu.unq.desapp.grupoK.backenddesappapi;

import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.ClientAccessException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.RepeatedElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.TokenValidationException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.WrongApiKeyException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WrongApiKeyException.class)
    public ResponseEntity handleWrongApiKeyException(WrongApiKeyException itemNotFoundException){
        return ResponseEntity.status(400).body(itemNotFoundException.getMessage());
    }

    @ExceptionHandler(EmptyDTOException.class)
    public ResponseEntity handleEmptyDTOException(EmptyDTOException emptyDTOException){
        return ResponseEntity.status(400).body(emptyDTOException.getMessage());
    }

    @ExceptionHandler(ClientAccessException.class)
    public ResponseEntity handleClientAccessException(ClientAccessException clientAccessException){
        return ResponseEntity.status(400).body(clientAccessException.getMessage());
    }

    @ExceptionHandler(InexistentElementException.class)
    public ResponseEntity handleInexistentElementException(InexistentElementException inexistentElementException){
        return ResponseEntity.status(400).body(inexistentElementException.getMessage());
    }

    @ExceptionHandler(RepeatedElementException.class)
    public ResponseEntity handleRepeatedElemntException(RepeatedElementException repeatedElementException){
        return ResponseEntity.status(400).body(repeatedElementException.getMessage());
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity handleTokenValidationException(TokenValidationException tokenValidationException){
        return ResponseEntity.status(400).body(tokenValidationException.getMessage());
    }
}
