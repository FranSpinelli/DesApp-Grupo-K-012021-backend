package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

public class WrongApiKeyException extends Throwable {
    public WrongApiKeyException(){
        super("You need a valid apikey in order to access to this endpoint");
    }
}
