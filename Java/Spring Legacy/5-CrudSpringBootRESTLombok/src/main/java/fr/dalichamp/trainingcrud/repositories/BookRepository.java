package fr.dalichamp.trainingcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dalichamp.trainingcrud.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{}
