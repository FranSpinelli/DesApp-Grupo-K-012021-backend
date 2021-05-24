package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

public class ReportDTO {

    private Integer reviewID;
    private String reportCause;
    private String sourcePlatform;
    private String platformReporterID;
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

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public String getReportCause() {
        return reportCause;
    }

    public void setReportCause(String reportCause) {
        this.reportCause = reportCause;
    }

    public String getSourcePlatform() {
        return sourcePlatform;
    }

    public void setSourcePlatform(String sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
    }

    public String getPlatformReporterID() {
        return platformReporterID;
    }

    public void setPlatformReporterID(String platformReporterID) {
        this.platformReporterID = platformReporterID;
    }

    public String getReporterNickName() {
        return reporterNickName;
    }

    public void setReporterNickName(String reporterNickName) {
        this.reporterNickName = reporterNickName;
    }

    public void assertEmpty() throws EmptyDTOException {

        if(this.reviewID == null || this.reportCause == null || this.reporterNickName == null ||
                this.platformReporterID == null || this.sourcePlatform == null){
            throw new EmptyDTOException("Wrong json received as parameter");
        }

        if(this.reportCause.isEmpty() || this.reporterNickName.isEmpty() || this.platformReporterID.isEmpty() ||
            this.sourcePlatform.isEmpty()){
            throw new EmptyDTOException("There is an empty field in the body json");
        }
    }
}
