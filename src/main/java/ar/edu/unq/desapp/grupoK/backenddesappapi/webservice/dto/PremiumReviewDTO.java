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
}
