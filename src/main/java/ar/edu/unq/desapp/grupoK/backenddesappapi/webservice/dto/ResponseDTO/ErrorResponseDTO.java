package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO;

public class ErrorResponseDTO {

    private Object message;

    public ErrorResponseDTO(Object message){
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }
}
