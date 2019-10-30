package fr.dalichamp.romain.repositories;

import fr.dalichamp.romain.entities.aUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<aUser, Long> {
    List<aUser> findAll();
}