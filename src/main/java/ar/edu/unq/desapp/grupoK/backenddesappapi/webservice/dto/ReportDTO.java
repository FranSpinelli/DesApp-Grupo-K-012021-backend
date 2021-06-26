package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Valid
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

    public ReportDTO(){}

    public ReportDTO(Integer reviewID, String reportCause, String sourcePlartform, String platformReporterID,
                    String reporterNickName){
        this.reviewID = reviewID;
        this.reportCause = reportCause;
        this.sourcePlatform = sourcePlartform;
        this.platformReporterID = platformReporterID;
        this.reporterNickName = reporterNickName;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public String getReportCause() {
        return reportCause;
    }

    public String getSourcePlatform() {
        return sourcePlatform;
    }

    public String getPlatformReporterID() {
        return platformReporterID;
    }

    public String getReporterNickName() {
        return reporterNickName;
    }
}
