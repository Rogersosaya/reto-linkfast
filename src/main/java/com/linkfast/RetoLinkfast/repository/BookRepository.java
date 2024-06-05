package com.linkfast.RetoLinkfast.repository;

import com.linkfast.RetoLinkfast.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Busca libros por título y/o autor.
     *
     * @param text Término de búsqueda
     * @return Lista de libros que coinciden con el término de búsqueda.
     */
    @Query("SELECT b FROM Book b WHERE b.titulo LIKE %:text% OR b.autor LIKE %:text%")
    List<Book> searchBooksByTitleAndAuthor(@Param("text") String text);
}
