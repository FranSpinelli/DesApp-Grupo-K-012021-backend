package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.TitleCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface TitleRepository extends CrudRepository<Title, Integer> {

        Optional<Title> findById(Integer id);

        List<Title> findAll();

        //List<Title> findByCategorySerie(List<Title> titles);

       // Collection<Title> findAll(Example<Title> example);
}
