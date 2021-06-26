package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO;

public abstract class ErrorResponseDTO<T> {

    private T message;

    public ErrorResponseDTO(T message){
        this.message = message;
    }
}
