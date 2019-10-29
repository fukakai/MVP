package fr.dalichamp.trainingcrud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.dalichamp.trainingcrud.entities.Author;
import fr.dalichamp.trainingcrud.repositories.AuthorRepository;

@Component
public class AuthorController {
	
	@Autowired
	AuthorRepository registry;
	
	public Optional<Author> getAuthor(Long id) {
		return registry.findById(id);
	}
	
	public List<Author> getAllAuthors(){
		return registry.findAll();
	}
	
	public Optional<Author> addAuthor(Author author) {
		return this.createOrUpdateAuthor(author);
	}
	
	public void deleteAuthor(Long id) {
		registry.deleteById(id);
	}
	
	public Optional<Author> updateAuthor(Author author) {
		return this.createOrUpdateAuthor(author);
	}
	
	private Optional<Author> createOrUpdateAuthor(Author author) {
		registry.save(author);
		return registry.findById(author.getId());
	}
}
