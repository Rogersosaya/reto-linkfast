package com.linkfast.RetoLinkfast.service;

import com.linkfast.RetoLinkfast.entity.Book;
import com.linkfast.RetoLinkfast.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookService {

    /**
     * Obtiene todos los libros disponibles.
     *
     * @return Lista de todos los libros disponibles.
     */
    List<Book> getBooks();

    /**
     * Obtiene un libro por su ID.
     *
     * @param id ID del libro a buscar.
     * @return El libro correspondiente al ID proporcionado, si existe.
     */
    Optional<Book> getBook(Long id);

    /**
     * Guarda o actualiza un libro.
     *
     * @param book El libro a guardar o actualizar.
     */
    void saveOrUpdate(Book book);

    /**
     * Elimina un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     */
    void delete(Long id);

    /**
     * Busca libros por título y/o autor.
     *
     * @param query Término de búsqueda que puede ser el título, el autor o una combinación de ambos.
     * @return Lista de libros que coinciden con el término de búsqueda.
     */
    List<Book> searchBooksByTitleAndAuthor(String query);
}
