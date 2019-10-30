package fr.dalichamp.trainingcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dalichamp.trainingcrud.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
