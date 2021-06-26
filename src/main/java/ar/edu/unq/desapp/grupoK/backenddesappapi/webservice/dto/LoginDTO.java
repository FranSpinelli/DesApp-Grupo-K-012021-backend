package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Valid
public class LoginDTO {

    @NotNull(message = "Password field can't be null in the body json")
    @NotEmpty(message = "Password field can't be empty in the body json")
    private String password;

    @NotNull(message = "ClientPlatformName field can't be null in the body json")
    @NotEmpty(message = "ClientPlatformName field can't be empty in the body json")
    private String clientPlatformName;

    public LoginDTO(String password, String clientPlatformName){
        this.password = password;
        this.clientPlatformName = clientPlatformName;
    }

    public String getClientPlatformName() {
        return clientPlatformName;
    }

    public String getPassword() {
        return password;
    }
}
