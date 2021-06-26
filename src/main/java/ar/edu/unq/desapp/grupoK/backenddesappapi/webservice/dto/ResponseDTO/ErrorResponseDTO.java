package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ErrorResponseDTO<T> {

    private T message;
}
