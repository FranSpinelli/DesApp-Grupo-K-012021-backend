package ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions;

public class NameAlreadyInUseException extends Throwable {
    public NameAlreadyInUseException(){
        super("The given name is already in use");
    }
}
