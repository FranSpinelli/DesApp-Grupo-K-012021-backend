package model;

import java.util.Date;

public class PublicReview extends PremiumReview {

    private Boolean spoilerAlert;
    private String nickName;
    private String geographicPosition;


    public PublicReview(String aExtendedDescription, String aSumaryDescription, Float aRaiting, Date aDate,
                        String aPlatform, String aLenguage, Boolean aSpoilerAlert, String aNickName,
                        String aGeogrephicPosition){

        super(aExtendedDescription, aSumaryDescription, aRaiting, aDate, aPlatform, aLenguage);

        this.spoilerAlert =  aSpoilerAlert;
        this.nickName = aNickName;
        this.geographicPosition = aGeogrephicPosition;

    }

    public Boolean getSpoilerAlert(){
        return spoilerAlert;
    }

    public String getNickName(){
        return nickName;
    }

    public String getGeographicPosition(){
        return geographicPosition;
    }

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setGeographicPosition(String geographicPosition) {
        this.geographicPosition = geographicPosition;
    }

}


