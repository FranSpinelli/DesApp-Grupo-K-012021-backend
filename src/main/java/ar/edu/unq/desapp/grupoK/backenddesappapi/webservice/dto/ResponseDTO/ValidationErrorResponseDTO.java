package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO;

import java.util.Map;

public class ValidationErrorResponseDTO extends ErrorResponseDTO<Map<String,String>>{

    public ValidationErrorResponseDTO(Map<String, String> message) {
        super(message);
    }
}
