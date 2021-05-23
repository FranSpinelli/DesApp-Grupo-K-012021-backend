package ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions;

public class InexistentElementWithIDException extends Throwable {
    public InexistentElementWithIDException(String message) {
        super(message);
    }
}
