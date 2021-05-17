package ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions;

public class InexistentReviewWithIDError extends Throwable {
    public InexistentReviewWithIDError(String message) {
        super(message);
    }
}
