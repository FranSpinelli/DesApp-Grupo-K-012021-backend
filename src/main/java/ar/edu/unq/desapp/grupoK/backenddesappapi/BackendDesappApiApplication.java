package ar.edu.unq.desapp.grupoK.backenddesappapi;

import ar.edu.unq.desapp.grupoK.backenddesappapi.service.ThirdPartyNotificator;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.Duration;
import java.util.Collections;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@RestController
@EnableSwagger2
@EnableCaching
public class BackendDesappApiApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/re-senia");
        SpringApplication.run(BackendDesappApiApplication.class, args);
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(20))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    //------------------------------------RABBITMQ QUEUE-----------------------------

    @Bean
    public Queue createQueue() {
        return new Queue("linkQueue", false);
    }

    //------------------------------------SWAGGER------------------------------------

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Re-Seña book API",
                "Proyecto Backend del Grupo K de la materia Desarrollo de Aplicaciones",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Francisco Svrznjak", "https://github.com/FranSpinelli", "a@b.com"),
                "API License",
                "https://github.com/FranSpinelli",
                Collections.emptyList());
    }
}
