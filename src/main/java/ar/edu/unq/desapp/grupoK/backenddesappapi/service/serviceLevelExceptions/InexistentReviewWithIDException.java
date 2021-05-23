package ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions;

public class InexistentReviewWithIDException extends Throwable {
    public InexistentReviewWithIDException(String message) {
        super(message);
    }
}
