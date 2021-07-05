package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Report;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer>  {

    Optional<Report> findById(Integer id);

    List<Report> findAll();
}
