package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public abstract class ErrorResponseDTO<T> {

    private T message;

    public ErrorResponseDTO(T message){
        this.message = message;
    }
}
