package fr.dalichamp.trainingcrud.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import fr.dalichamp.trainingcrud.entities.Book;
import fr.dalichamp.trainingcrud.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	BookRepository library;
	
	public Optional<Book> getBook(Long id) {
		return library.findById(id);
	}
	
	public List<Book> getAllBooks(){
		return library.findAll();
	}
	
	public Optional<Book> addBook(@Valid Book book) {
		return this.createOrUpdateBook(book);
	}
	
	public void deleteBook(Long id) {
		library.deleteById(id);
	}
	
	public Optional<Book> updateBook(@Valid Book book) {
		return this.createOrUpdateBook(book);
	}
	
	private Optional<Book> createOrUpdateBook(@Valid Book book) {
		library.save(book);
		return library.findById(book.getId());
	}
}
