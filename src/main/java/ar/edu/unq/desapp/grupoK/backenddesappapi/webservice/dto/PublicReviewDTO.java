package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Valid
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PublicReviewDTO extends PremiumReviewDTO{

    @NotNull(message = "SpoilerAlert field can't be null in the body json")
    private Boolean spoilerAlert;

    @NotNull(message = "NickName field can't be null in the body json")
    @NotEmpty(message = "NickName field can't be empty in the body json")
    private String nickName;

    @NotNull(message = "GeographicPosition field can't be null in the body json")
    @NotEmpty(message = "GeographicPosition field can't be empty in the body json")
    private String geographicPosition;
}
