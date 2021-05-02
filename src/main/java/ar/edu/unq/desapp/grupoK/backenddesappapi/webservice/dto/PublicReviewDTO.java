package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

public class PublicReviewDTO {

    private Integer titleID;
    private String extendedDescription;
    private String summaryDescription;
    private Integer rating;
    private String sourcePlatform;
    private String platformWriterID;
    private String lenguage;
    private Boolean spoilerAlert;
    private String nickName;
    private String geographicPosition;

    public PublicReviewDTO() {}

    public PublicReviewDTO(Integer titleID,String extendedDescription,String summaryDescription,Integer rating,
                           String sourcePlatform, String platformWriterID, String lenguage, Boolean spoilerAlert,
                           String nickName, String geographicPosition) {
        this.titleID = titleID;
        this.extendedDescription = extendedDescription;
        this.summaryDescription = summaryDescription;
        this.rating = rating;
        this.sourcePlatform = sourcePlatform;
        this.platformWriterID = platformWriterID;
        this.lenguage = lenguage;
        this.spoilerAlert = spoilerAlert;
        this.nickName = nickName;
        this.geographicPosition = geographicPosition;
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

    public String getLenguage() {
        return lenguage;
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

    public void assertEmpty() throws EmptyDTOError {
        if(titleID == null || extendedDescription == null || summaryDescription == null || rating == null || sourcePlatform == null ||
                platformWriterID == null || lenguage == null || this.spoilerAlert == null || this.nickName == null ||
                this.geographicPosition == null){
            throw new EmptyDTOError("Wrong json received as parameter");
        }

        if(this.extendedDescription.isEmpty() || this.summaryDescription.isEmpty() || this.sourcePlatform.isEmpty() ||
                this.platformWriterID.isEmpty() || this.lenguage.isEmpty() || this.nickName.isEmpty() || this.geographicPosition.isEmpty()){
            throw new EmptyDTOError("There is an empty field in the body json");
        }
    }
}
