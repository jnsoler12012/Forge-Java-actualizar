package com.nicolas.actualizar.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nicolas.actualizar.models.Book;
import com.nicolas.actualizar.services.BookService;

@Controller
public class HomeController {
    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/api/books")
    public List<Book> index() {
        return bookService.allBooks();
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.POST)
    public Book create(@RequestParam(value = "title") String title, @RequestParam(value = "description") String desc,
            @RequestParam(value = "language") String lang, @RequestParam(value = "pages") Integer numOfPages) {
        Book book = new Book(title, desc, lang, numOfPages);
        return bookService.createBook(book);
    }

    @RequestMapping("/books/{id}")
    public String showBook(@PathVariable("id") Long id, Model model) {

        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(value = "/api/books/{id}", method = RequestMethod.PUT)
    public Book edit(@PathVariable(value = "id") Long id, @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String desc, @RequestParam(value = "language") String lang,
            @RequestParam(value = "pages") Integer numOfPages) {
        Book book = new Book(title, desc, lang, numOfPages);
        return bookService.editBook(id, book);
    }

    @RequestMapping(value = "/api/books/{id}", method = RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

}
