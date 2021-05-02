package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PublicReview extends PremiumReview {

    @Column
    private Boolean spoilerAlert;
    @Column
    private String nickName;
    @Column
    private String geographicPosition;


    public PublicReview() {
        super();
    }

    public PublicReview(String aExtendedDescription, String aSumaryDescription, Integer aRating, Boolean aSpoilerAlert, LocalDate aDate,
                        String aSourcePlatform, String aPlatformUserID, String aNickName, String aLenguage, String aGeogrephicPosition){

        super(aExtendedDescription, aSumaryDescription, aRating, aDate, aSourcePlatform, aPlatformUserID, aLenguage);

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


