package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Valid
@Getter
@AllArgsConstructor
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
}
