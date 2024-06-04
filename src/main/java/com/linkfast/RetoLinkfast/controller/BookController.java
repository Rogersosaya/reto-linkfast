package com.linkfast.RetoLinkfast.controller;

import com.linkfast.RetoLinkfast.entity.Book;
import com.linkfast.RetoLinkfast.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "books")
public class BookController {

    @Autowired
    private BookService bookService;
    // Obtener todos los libros
    @GetMapping
    public List<Book> getAll(){
        return bookService.getBooks();
    }
    // Obtener un libro por su Id
    @GetMapping("/{bookId}")
    public Optional<Book> getById(@PathVariable("bookId") Long bookId){
        return bookService.getBook(bookId);
    }
    // Crear y Actualizar un libro
    @PostMapping
    public void saveUpdate(@RequestBody Book book){
        System.out.println("mensaje :"+book);
        bookService.saveOrUpdate(book);
    }
    // Eliminar un libro
    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable("bookId")  Long bookId){
        bookService.delete(bookId);
    }
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("query") String query) {
        return bookService.searchBooksByTitleAndAuthor(query);
    }

}
