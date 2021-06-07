package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PublicReview extends Review {

    @Column
    private Boolean spoilerAlert;
    @Column
    private String nickName;
    @Column
    private String geographicPosition;

    private String type;


    public PublicReview() {
        super();
    }


    public PublicReview(String aExtendedDescription, String aSumaryDescription, Double aRating, Boolean aSpoilerAlert, LocalDate aDate,
                        String aSourcePlatform, String aPlatformUserID, String aNickName, String aLenguage, String aGeogrephicPosition,
                        String type){

        super(aExtendedDescription, aSumaryDescription, aRating, aDate, aSourcePlatform, aPlatformUserID, aLenguage);

        this.spoilerAlert =  aSpoilerAlert;
        this.nickName = aNickName;
        this.geographicPosition = aGeogrephicPosition;
        this.type = type;

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

    public String getType(){ return type;}

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setGeographicPosition(String geographicPosition) {
        this.geographicPosition = geographicPosition;
    }

    public void setType(String type){ this.type = type;  }

}


