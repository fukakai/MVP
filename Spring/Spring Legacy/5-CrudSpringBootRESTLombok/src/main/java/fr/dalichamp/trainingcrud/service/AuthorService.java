package fr.dalichamp.trainingcrud.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import fr.dalichamp.trainingcrud.entities.Author;
import fr.dalichamp.trainingcrud.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository registry;
	
	public Optional<Author> getAuthor(Long id) {
		return registry.findById(id);
	}
	
	public List<Author> getAllAuthors(){
		return registry.findAll();
	}
	
	public Optional<Author> addAuthor(@Valid Author author) {
		return this.createOrUpdateAuthor(author);
	}
	
	public void deleteAuthor(Long id) {
		registry.deleteById(id);
	}
	
	public Optional<Author> updateAuthor(@Valid Author author) {
		return this.createOrUpdateAuthor(author);
	}
	
	private Optional<Author> createOrUpdateAuthor(@Valid Author author) {
		registry.save(author);
		return registry.findById(author.getId());
	}
}
