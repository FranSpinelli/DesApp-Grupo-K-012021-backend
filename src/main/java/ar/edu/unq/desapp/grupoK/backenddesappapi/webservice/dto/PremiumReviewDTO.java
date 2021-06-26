package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Valid
public class PremiumReviewDTO {

    @NotNull(message = "titleID field can't be null in the body json")
    private Integer titleID;

    @NotNull(message = "ExtendedDescription field can't be null in the body json")
    @NotEmpty(message = "ExtendedDescription field can't be empty in the body json")
    private String extendedDescription;

    @NotNull(message = "SummaryDescription field can't be null in the body json")
    @NotEmpty(message = "SummaryDescription field can't be empty in the body json")
    private String summaryDescription;

    @NotNull(message = "Rating field can't be null in the body json")
    private Integer rating;

    @NotNull(message = "SourcePlatform field can't be null in the body json")
    @NotEmpty(message = "SourcePlatform field can't be empty in the body json")
    private String sourcePlatform;

    @NotNull(message = "PlatformWriterID field can't be null in the body json")
    @NotEmpty(message = "PlatformWriterID field can't be empty in the body json")
    private String platformWriterID;

    @NotNull(message = "Language field can't be null in the body json")
    @NotEmpty(message = "Language field can't be empty in the body json")
    private String language;

    public PremiumReviewDTO() {}

    public PremiumReviewDTO(Integer titleID,String extendedDescription,String summaryDescription,Integer rating,
                            String sourcePlatform, String platformWriterID, String language) {
        this.titleID = titleID;
        this.extendedDescription = extendedDescription;
        this.summaryDescription = summaryDescription;
        this.rating = rating;
        this.sourcePlatform = sourcePlatform;
        this.platformWriterID = platformWriterID;
        this.language = language;
    }

    public Integer getTitleID() {
        return titleID;
    }

    public String getExtendedDescription() {
        return extendedDescription;
    }

    public String getSummaryDescription() {
        return summaryDescription;
    }

    public Integer getRating() {
        return rating;
    }

    public String getSourcePlatform() {
        return sourcePlatform;
    }

    public String getPlatformWriterID() {
        return platformWriterID;
    }

    public String getLanguage() {
        return language;
    }
}
