package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO;

public class ApiKeyResponseDTO {

    private String apikey;

    public ApiKeyResponseDTO(String apiKey) {

    this.apikey = apiKey;
    }

    public String getApikey() {
        return apikey;
    }
}
