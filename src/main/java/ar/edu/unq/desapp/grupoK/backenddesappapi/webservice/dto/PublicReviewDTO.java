package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Valid
public class PublicReviewDTO extends PremiumReviewDTO{

    @NotNull(message = "SpoilerAlert field can't be null in the body json")
    private Boolean spoilerAlert;

    @NotNull(message = "NickName field can't be null in the body json")
    @NotEmpty(message = "NickName field can't be empty in the body json")
    private String nickName;

    @NotNull(message = "GeographicPosition field can't be null in the body json")
    @NotEmpty(message = "GeographicPosition field can't be empty in the body json")
    private String geographicPosition;

    public PublicReviewDTO() {}

    public PublicReviewDTO(Integer titleID,String extendedDescription,String summaryDescription,Integer rating,
                           String sourcePlatform, String platformWriterID, String lenguage, Boolean spoilerAlert,
                           String nickName, String geographicPosition) {

        super(titleID, extendedDescription, summaryDescription, rating, sourcePlatform, platformWriterID, lenguage);
        this.spoilerAlert = spoilerAlert;
        this.nickName = nickName;
        this.geographicPosition = geographicPosition;
    }

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public String getNickName() {
        return nickName;
    }

    public String getGeographicPosition() {
        return geographicPosition;
    }
}
