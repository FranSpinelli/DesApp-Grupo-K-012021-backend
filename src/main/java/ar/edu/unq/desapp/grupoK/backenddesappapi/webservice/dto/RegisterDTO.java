package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Valid
public class RegisterDTO {

    @NotNull(message = "NickName field can't be null in the body json")
    @NotEmpty(message = "NickName field can't be empty in the body json")
    private String clientPlatformName;

    @NotNull(message = "NickName field can't be null in the body json")
    @NotEmpty(message = "NickName field can't be empty in the body json")
    private String password;

    @NotNull(message = "NickName field can't be null in the body json")
    @NotEmpty(message = "NickName field can't be empty in the body json")
    private String contactMail;

    public RegisterDTO(String clientPlatformName, String password, String contactMail){
        this.clientPlatformName = clientPlatformName;
        this.password = password;
        this.contactMail = contactMail;
    }

    public String getClientPlatformName() {
        return clientPlatformName;
    }

    public String getPassword() {
        return password;
    }

    public String getContactMail() {
        return contactMail;
    }
}
