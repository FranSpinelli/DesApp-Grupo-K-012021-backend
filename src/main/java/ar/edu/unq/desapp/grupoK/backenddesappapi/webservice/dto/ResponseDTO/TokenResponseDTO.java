package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO;

public class TokenResponseDTO {

    private String token;

    public TokenResponseDTO(String token) {
        this.token = "Bearer " + token;
    }

    public String getToken() {
        return token;
    }
}
