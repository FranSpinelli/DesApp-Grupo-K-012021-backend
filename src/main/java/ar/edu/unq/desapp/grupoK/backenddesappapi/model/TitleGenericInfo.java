package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Titles")
public class TitleGenericInfo implements Serializable {

    @Id
    private Integer id;
    private String originalTitle;
    private Integer runtimeMinutes;
    private Double averageRating;
    private Integer numberOfReviews;
}
