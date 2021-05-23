package ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions;

public class ClientPlatformLoginException extends Throwable {

    public ClientPlatformLoginException(){
        super("incorrect name or password");
    }
}
