package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

public class ErrorResponseDTO {

    private String message;

    public ErrorResponseDTO(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
