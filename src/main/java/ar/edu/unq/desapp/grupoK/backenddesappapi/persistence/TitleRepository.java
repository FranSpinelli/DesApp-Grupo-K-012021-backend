package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface TitleRepository extends CrudRepository<Title, Integer> {

        Optional<Title> findById(Integer id);

        List<Title> findAll();
}
