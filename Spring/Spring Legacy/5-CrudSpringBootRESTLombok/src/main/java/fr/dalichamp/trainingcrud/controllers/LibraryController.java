package fr.dalichamp.trainingcrud.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fr.dalichamp.trainingcrud.service.BookService;
import fr.dalichamp.trainingcrud.entities.Book;

@Controller
@RestController
public class LibraryController {

	@Autowired
	BookService useLibrary;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome To My Library";
	}
	
	@GetMapping("/book/{id}")
	public Optional<Book> getBook(@PathVariable Long id) { 
		return useLibrary.getBook(id);
	}
	
	@GetMapping("/books")
	public List<Book> books(){
		return useLibrary.getAllBooks();
	}
	
	@PostMapping("/book/add")
	public Optional<Book> addBook(@RequestBody Book book) {
		return useLibrary.addBook(book);
	}
	
	@PutMapping("/book/{id}")
	public Optional<Book> updateBook(@RequestBody Book book) {
		return useLibrary.updateBook(book);
	}
	
	@DeleteMapping("/book/{id}")
	public String deleteBook(@PathVariable Long id) {
		useLibrary.deleteBook(id);
		return "Book Deleted";
	}
	
}
