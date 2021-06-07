package ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions;

public class InexistentReviewWithRatingError extends Throwable {

    public InexistentReviewWithRatingError(String message) {
        super(message);
    }
}
