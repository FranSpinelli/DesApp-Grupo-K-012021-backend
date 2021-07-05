package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Valid
@AllArgsConstructor
@Getter
public class TitleSubscriptionDTO {

    @NotNull(message = "TitleID field can't be null in the body json")
    private Integer titleID;

    @NotNull(message = "SubscriberClientID field can't be null in the body json")
    private Integer subscriberClientID;

    @NotNull(message = "EndpointToReach field can't be null in the body json")
    @NotEmpty(message = "EndpointToReach field can't be empty in the body json")
    private String endpointToReach;
}