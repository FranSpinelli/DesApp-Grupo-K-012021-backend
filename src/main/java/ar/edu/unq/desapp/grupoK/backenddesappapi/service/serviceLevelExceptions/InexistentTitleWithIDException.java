package ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions;

public class InexistentTitleWithIDException extends Throwable {

    public InexistentTitleWithIDException(String message){
        super(message);
    }
}
