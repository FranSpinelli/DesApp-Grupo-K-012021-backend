package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

public class PremiumReviewDTO {

    private Integer titleID;
    private String extendedDescription;
    private String summaryDescription;
    private Integer rating;
    private String sourcePlatform;
    private String platformWriterID;
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

    public void assertEmpty() throws EmptyDTOError {

        if(titleID == null || extendedDescription == null || summaryDescription == null || rating == null || sourcePlatform == null ||
        platformWriterID == null || language == null){
            throw new EmptyDTOError("Wrong json received as parameter");
        }

        if(this.extendedDescription.isEmpty() || this.summaryDescription.isEmpty() || this.sourcePlatform.isEmpty() ||
                this.platformWriterID.isEmpty() || this.language.isEmpty()){
            throw new EmptyDTOError("There is an empty field in the body json");
        }
    }
}
