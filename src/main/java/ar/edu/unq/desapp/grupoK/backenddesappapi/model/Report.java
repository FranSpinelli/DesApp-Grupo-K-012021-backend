package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String cause;
    @Column
    private String sourcePlatform;
    @Column
    private String platformReporterID;
    @Column
    private String reporterNickName;

    public Report() {}

    public Report(String aCause, String aSourcePlatform, String aPlatformReporterID,
                  String aReporterNickName){

        cause = aCause;
        sourcePlatform = aSourcePlatform;
        platformReporterID = aPlatformReporterID;
        reporterNickName = aReporterNickName;
    }

    public Integer getId() {
        return id;
    }

    public String getCause() {
        return cause;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setSourcePlatform(String sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
    }

    public void setPlatformReporterID(String platformWriterID) {
        this.platformReporterID = platformWriterID;
    }

    public void setNickName(String nickName) {
        this.reporterNickName = nickName;
    }
}
