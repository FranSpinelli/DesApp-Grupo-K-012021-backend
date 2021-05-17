package ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions;

public class InexistentTitleWithIDError extends Throwable {

    public InexistentTitleWithIDError(String message){
        super(message);
    }
}
