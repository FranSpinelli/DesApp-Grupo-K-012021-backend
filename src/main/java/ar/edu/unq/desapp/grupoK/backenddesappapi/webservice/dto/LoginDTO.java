package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

public class LoginDTO {

    private String password;
    private String clientPlatformName;

    public LoginDTO(String password, String clientPlatformName){
        this.password = password;
        this.clientPlatformName = clientPlatformName;
    }

    public String getClientPlatformName() {
        return clientPlatformName;
    }

    public String getPassword() {
        return password;
    }

    public void assertEmpty() throws EmptyDTOException {
        if(password == null || clientPlatformName == null){
            throw new EmptyDTOException("Wrong json received as parameter");
        }

        if(password.equals("") || clientPlatformName.equals("")){
            throw new EmptyDTOException("There is an empty field in the body json");
        }
    }
}
