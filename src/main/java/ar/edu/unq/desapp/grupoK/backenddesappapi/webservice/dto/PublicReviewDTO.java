package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

public class PublicReviewDTO extends PremiumReviewDTO{

    /*private Integer titleID;
    private String extendedDescription;
    private String summaryDescription;
    private Integer rating;
    private String sourcePlatform;
    private String platformWriterID;
    private String lenguage;*/
    private Boolean spoilerAlert;
    private String nickName;
    private String geographicPosition;

    public PublicReviewDTO() {}

    public PublicReviewDTO(Integer titleID,String extendedDescription,String summaryDescription,Integer rating,
                           String sourcePlatform, String platformWriterID, String lenguage, Boolean spoilerAlert,
                           String nickName, String geographicPosition) {
        /*this.titleID = titleID;
        this.extendedDescription = extendedDescription;
        this.summaryDescription = summaryDescription;
        this.rating = rating;
        this.sourcePlatform = sourcePlatform;
        this.platformWriterID = platformWriterID;
        this.lenguage = lenguage;*/

        super(titleID, extendedDescription, summaryDescription, rating, sourcePlatform, platformWriterID, lenguage);
        this.spoilerAlert = spoilerAlert;
        this.nickName = nickName;
        this.geographicPosition = geographicPosition;
    }

    /*public Integer getTitleID() {
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
        return lenguage;
    }*/

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
        if(getTitleID()== null || getExtendedDescription() == null || getSummaryDescription() == null || getRating() == null ||
                getSourcePlatform() == null || getPlatformWriterID() == null || getLanguage() == null || spoilerAlert == null ||
                nickName == null || geographicPosition == null){
            throw new EmptyDTOError("Wrong json received as parameter");
        }

        if(getExtendedDescription().isEmpty() || getSummaryDescription().isEmpty() || getSourcePlatform().isEmpty() ||
                getPlatformWriterID().isEmpty() || getLanguage().isEmpty() || this.nickName.isEmpty() || this.geographicPosition.isEmpty()){
            throw new EmptyDTOError("There is an empty field in the body json");
        }
    }
}
