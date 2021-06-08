package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

public class RegisterDTO {

    private String clientPlatformName;
    private String password;
    private String contactMail;

    public RegisterDTO(String clientPlatformName, String password, String contactMail){
        this.clientPlatformName = clientPlatformName;
        this.password = password;
        this.contactMail = contactMail;
    }

    public String getClientPlatformName() {
        return clientPlatformName;
    }

    public String getPassword() {
        return password;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void assertEmpty() throws EmptyDTOException {

        if(clientPlatformName == null || password == null || contactMail == null){
            throw new EmptyDTOException("Wrong json received as parameter");
        }

        if(clientPlatformName.equals("") || password.equals("") || contactMail.equals("")){
            throw new EmptyDTOException("There is an empty field in the body json");
        }
    }
}
