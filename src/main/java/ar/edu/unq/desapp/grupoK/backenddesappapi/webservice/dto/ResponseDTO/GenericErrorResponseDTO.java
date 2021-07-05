package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO;

public class GenericErrorResponseDTO extends ErrorResponseDTO<String>{

    public GenericErrorResponseDTO(String message) {
        super(message);
    }
}
