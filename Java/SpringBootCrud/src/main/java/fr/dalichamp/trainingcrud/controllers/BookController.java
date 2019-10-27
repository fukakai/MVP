package fr.dalichamp.trainingcrud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.dalichamp.trainingcrud.entities.Book;
import fr.dalichamp.trainingcrud.repositories.BookRepository;

@Component
public class BookController {
	
	@Autowired
	BookRepository library;
	
	public Optional<Book> getBook(Long id) {
		return library.findById(id);
	}
	
	public List<Book> getAllBooks(){
		return library.findAll();
	}
	
	public Optional<Book> addBook(Book book) {
		return this.createOrUpdateBook(book);
	}
	
	public void deleteBook(Long id) {
		library.deleteById(id);
	}
	
	public Optional<Book> updateBook(Book book) {
		return this.createOrUpdateBook(book);
	}
	
	private Optional<Book> createOrUpdateBook(Book book) {
		library.save(book);
		return library.findById(book.getId());
	}
}
