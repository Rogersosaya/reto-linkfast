package com.linkfast.RetoLinkfast.service;

import com.linkfast.RetoLinkfast.entity.Book;
import com.linkfast.RetoLinkfast.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Obtiene todos los libros disponibles.
     *
     * @return Lista de todos los libros disponibles.
     */
    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * Obtiene un libro por su ID.
     *
     * @param id ID del libro a buscar.
     * @return El libro correspondiente al ID proporcionado, si existe.
     */
    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Guarda o actualiza un libro.
     *
     * @param book El libro a guardar o actualizar.
     */
    @Override
    public void saveOrUpdate(Book book) {
        bookRepository.save(book);
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     */
    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    /**
     * Busca libros por título y/o autor.
     *
     * @param query Término de búsqueda que puede ser el título, el autor o una combinación de ambos.
     * @return Lista de libros que coinciden con el término de búsqueda.
     */
    @Override
    public List<Book> searchBooksByTitleAndAuthor(String query) {
        return bookRepository.searchBooksByTitleAndAuthor(query);
    }
}
