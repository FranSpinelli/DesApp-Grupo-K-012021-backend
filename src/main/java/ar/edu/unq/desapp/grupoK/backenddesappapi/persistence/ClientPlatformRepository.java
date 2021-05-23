package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ClientPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientPlatformRepository extends JpaRepository<ClientPlatform, Integer> {

    ClientPlatform findByName(String name);
}
