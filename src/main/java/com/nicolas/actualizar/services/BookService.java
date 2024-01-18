package com.nicolas.actualizar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nicolas.actualizar.models.Book;
import com.nicolas.actualizar.repositories.BookRepository;

@Service
public class BookService {
     private final BookRepository bookRepository;
    
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    //Devolviendo todos los libros.
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    //Creando un libro.
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    //Obteniendo la información de un libro
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }

    //Obteniendo la información de un libro
    public Book editBook(Long id, Book b) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            Book returnBook = optionalBook.get();

            returnBook.setDescription(b.getDescription());
            returnBook.setLanguage(b.getLanguage());
            returnBook.setNumberOfPages(b.getNumberOfPages());
            returnBook.setTitle(b.getTitle());

            return bookRepository.save(returnBook);

        } else {
            return null;
        }
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
