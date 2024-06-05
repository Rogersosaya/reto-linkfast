package com.linkfast.RetoLinkfast.controller;

import com.linkfast.RetoLinkfast.entity.Book;
import com.linkfast.RetoLinkfast.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Obtiene todos los libros disponibles.
     *
     * @return Lista de todos los libros disponibles.
     */
    @GetMapping
    public List<Book> getAll() {
        return bookService.getBooks();
    }

    /**
     * Obtiene un libro por su ID.
     *
     * @param bookId ID del libro a buscar.
     * @return El libro correspondiente al ID proporcionado, si existe.
     */
    @GetMapping("/{bookId}")
    public Optional<Book> getById(@PathVariable("bookId") Long bookId) {
        return bookService.getBook(bookId);
    }

    /**
     * Crea o actualiza un libro.
     *
     * @param book Los datos del libro a crear o actualizar.
     */
    @PostMapping
    public void saveUpdate(@RequestBody Book book) {
        bookService.saveOrUpdate(book);
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param bookId ID del libro a eliminar.
     */
    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable("bookId") Long bookId) {
        bookService.delete(bookId);
    }

    /**
     * Busca libros por título y/o autor.
     *
     * @param query Término de búsqueda que puede ser el título, el autor o una combinación de ambos.
     * @return Lista de libros que coinciden con el término de búsqueda.
     */
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("query") String query) {
        return bookService.searchBooksByTitleAndAuthor(query);
    }

}
