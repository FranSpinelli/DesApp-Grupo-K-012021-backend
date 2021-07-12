package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PublicReview extends Review {

    @Column
    private Boolean spoiler_alert;
    @Column
    private String nickName;
    @Column
    private String geographic_position;

    private String type;


    public PublicReview() {
        super();
    }


    public PublicReview(String aExtendedDescription, String aSumaryDescription, Double aRating, Boolean aSpoilerAlert, LocalDate aDate,
                        String aSourcePlatform, String aPlatformUserID, String aNickName, String aLenguage, String aGeogrephicPosition,
                        String type, Integer id_title){

        super(aExtendedDescription, aSumaryDescription, aRating, aDate, aSourcePlatform, aPlatformUserID, aLenguage, id_title);

        this.spoiler_alert =  aSpoilerAlert;
        this.nickName = aNickName;
        this.geographic_position = aGeogrephicPosition;
        this.type = type;

    }

    public Boolean getSpoiler_alert(){
        return spoiler_alert;
    }

    public String getNickName(){
        return nickName;
    }

    public String getGeographicPosition(){
        return geographic_position;
    }

    public String getType(){ return type;}

    public void setSpoiler_alert(Boolean spoiler_alert) {
        this.spoiler_alert = spoiler_alert;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setGeographicPosition(String geographicPosition) {
        this.geographic_position = geographicPosition;
    }

    public void setType(String type){ this.type = type;  }

}


