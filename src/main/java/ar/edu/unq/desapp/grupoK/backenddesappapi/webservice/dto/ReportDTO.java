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
public class ReportDTO {

    @NotNull(message = "ReviewID field can't be null in the body json")
    private Integer reviewID;

    @NotNull(message = "ReportCause field can't be null in the body json")
    @NotEmpty(message = "ReportCause field can't be empty in the body json")
    private String reportCause;

    @NotNull(message = "SourcePlatform field can't be null in the body json")
    @NotEmpty(message = "SourcePlatform field can't be empty in the body json")
    private String sourcePlatform;

    @NotNull(message = "PlatformReporterID field can't be null in the body json")
    @NotEmpty(message = "PlatformReporterID field can't be empty in the body json")
    private String platformReporterID;

    @NotNull(message = "ReporterNickName field can't be null in the body json")
    @NotEmpty(message = "ReporterNickName field can't be empty in the body json")
    private String reporterNickName;
}
