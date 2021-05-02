package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PublicReview extends PremiumReview {

    @Column
    private Boolean spoilerAlert;
    @Column
    private String nickName;
    @Column
    private String geographicPosition;


    public PublicReview(Integer anID, String aExtendedDescription, String aSumaryDescription, Double aRaiting, Date aDate,
                        String aPlatform, String aLenguage, Boolean aSpoilerAlert, String aNickName,
                        String aGeogrephicPosition){

        super(anID, aExtendedDescription, aSumaryDescription, aRaiting, aDate, aPlatform, aLenguage);

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


