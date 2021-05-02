package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

public class InexistentFilmWithIDError extends Throwable {

    public InexistentFilmWithIDError(String message){
        super(message);
    }
}
