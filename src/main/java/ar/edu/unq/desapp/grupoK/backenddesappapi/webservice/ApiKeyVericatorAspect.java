package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ClientPlatform;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ClientPlatformRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.LinkedHashMap;

@Aspect
@Component
public class ApiKeyVericatorAspect {

    @Autowired
    private ClientPlatformRepository clientPlatformRepository;

    @Pointcut("execution(* ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.TitleController.*(..)) ||" +
            "execution(* ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.ReviewController.*(..))")
    public void methodStarterServicePointcut() {}

    @Before("methodStarterServicePointcut()")
    public void beforeMethod() throws Throwable {

        LinkedHashMap hashMapWithPathVariable = (LinkedHashMap) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String apiKey = (String) hashMapWithPathVariable.get("apiKey");

        ClientPlatform clientPlatformWithApiKey = clientPlatformRepository.findByApiKey(apiKey);

        if(clientPlatformWithApiKey == null){
            throw new WrongApiKeyException();
        }
    }
}
