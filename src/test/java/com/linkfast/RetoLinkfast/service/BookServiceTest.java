package com.linkfast.RetoLinkfast.service;

import com.linkfast.RetoLinkfast.entity.Book;
import com.linkfast.RetoLinkfast.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BookServiceTest {
    // Mock del repositorio de libros
    @Mock
    private BookRepository bookRepository;

    // Inyecta el servicio a probar con el mock del repositorio
    @InjectMocks
    private BookServiceImpl bookService;

    // Libros de ejemplo
    private Book book1;
    private Book book2;

    // Configuración inicial antes de cada prueba
    @BeforeEach
    public void setUp() {
        // Inicializa los mocks
        MockitoAnnotations.openMocks(this);

        // Crea instancias de libros de ejemplo
        book1 = new Book("Java Programming","John Doe",LocalDate.of(2020, 1, 1),"1234567890");
        book1.setBookId(1L);

        book2 = new Book("Spring Framework","Jane Smith",LocalDate.of(2021, 1, 1),"0987654321");
        book2.setBookId(2L);
    }

    // Prueba para verificar que se obtienen todos los libros correctamente
    @Test
    public void testGetBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getBooks();

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    // Prueba para verificar que se obtiene un libro por su ID correctamente
    @Test
    public void testGetBook() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book1));

        Optional<Book> book = bookService.getBook(1L);

        assertTrue(book.isPresent());
        assertEquals("Java Programming", book.get().getTitulo());
        verify(bookRepository, times(1)).findById(1L);
    }

    // Prueba para verificar que se guarda o actualiza un libro correctamente
    @Test
    public void testSaveOrUpdate() {
        bookService.saveOrUpdate(book1);

        verify(bookRepository, times(1)).save(book1);
    }

    // Prueba para verificar que se elimina un libro correctamente
    @Test
    public void testDelete() {
        bookService.delete(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }

    // Prueba para verificar que se realizan búsquedas de libros por título y autor correctamente
    @Test
    public void testSearchBooksByTitleAndAuthor() {
        when(bookRepository.searchBooksByTitleAndAuthor(anyString())).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.searchBooksByTitleAndAuthor("Java");

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).searchBooksByTitleAndAuthor("Java");
    }
}
