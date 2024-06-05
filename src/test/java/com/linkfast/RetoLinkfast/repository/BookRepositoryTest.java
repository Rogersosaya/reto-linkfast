package com.linkfast.RetoLinkfast.repository;

import com.linkfast.RetoLinkfast.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @MockBean
    private BookRepository bookRepository;

    private List<Book> mockBooks;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear algunos libros de ejemplo
        Book book1 = new Book("Java Programming","John Doe", LocalDate.of(2020, 1, 1),"1234567890");

        Book book2 = new Book("Spring Framework","Jane Smith",LocalDate.of(2021, 1, 1),"0987654321");


        mockBooks = Arrays.asList(book1, book2);
    }

    // Prueba para verificar la búsqueda de libros por título y autor correctamente.
    @Test
    public void testSearchBooksByTitleAndAuthor() {
        when(bookRepository.searchBooksByTitleAndAuthor(anyString())).thenReturn(mockBooks);

        List<Book> books = bookRepository.searchBooksByTitleAndAuthor("Java");

        assertEquals(2, books.size());
        assertEquals("Java Programming", books.get(0).getTitulo());
        assertEquals("John Doe", books.get(0).getAutor());
    }
}
