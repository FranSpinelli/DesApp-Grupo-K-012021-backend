package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class ClientPlatform {

    @TableGenerator(name = "Client_ID_Generator", initialValue = 1)
    @Id
    @GeneratedValue(generator = "Client_ID_Generator")
    private Integer id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String contactMail;
    @Column
    private String apiKey;

    public ClientPlatform(){}

    public ClientPlatform(String aClientPlatformName, String aPassword, String aContactMail, String anApiKey){
        this.name = aClientPlatformName;
        this.password = aPassword;
        this.contactMail = aContactMail;
        this.apiKey = anApiKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Boolean canLogInWithGivenPass(String aPass){
        return this.password.equals(aPass);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
