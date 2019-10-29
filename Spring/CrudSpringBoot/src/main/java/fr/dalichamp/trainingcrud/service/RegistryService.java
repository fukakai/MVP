package fr.dalichamp.trainingcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dalichamp.trainingcrud.controllers.AuthorController;
import fr.dalichamp.trainingcrud.entities.Author;

@Service
@RestController
public class RegistryService {

	@Autowired
	AuthorController useRegistry;
	
	@GetMapping("/author/{id}")
	public Optional<Author> getAuthor(@PathVariable Long id) { 
		return useRegistry.getAuthor(id);
	}
	
	@GetMapping("/authors")
	public List<Author> authors(){
		return useRegistry.getAllAuthors();
	}
	
	@PostMapping("/author/add")
	public Optional<Author> addBook(@RequestBody Author author) {
		return useRegistry.addAuthor(author);
	}
	
	@PutMapping("/author/{id}")
	public Optional<Author> updateBook(@RequestBody Author author) {
		return useRegistry.updateAuthor(author);
	}
	
	@DeleteMapping("/author/{id}")
	public String deleteAuthor(@PathVariable Long id) {
		useRegistry.deleteAuthor(id);
		return "Author Deleted";
	}
	
}
