package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

public class InexistentReviewWithIDError extends Throwable {
    public InexistentReviewWithIDError(String message) {
        super(message);
    }
}
