package com.linkfast.RetoLinkfast.controller;

import com.linkfast.RetoLinkfast.entity.Book;
import com.linkfast.RetoLinkfast.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    // Inyecta el objeto MockMvc para simular solicitudes HTTP
    @Autowired
    private MockMvc mockMvc;

    // Crea un mock del servicio BookService
    @MockBean
    private BookService bookService;

    // Declara instancias de libros de ejemplo
    private Book book1;
    private Book book2;

    // Método de configuración que se ejecuta antes de cada prueba
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
    public void testGetAll() throws Exception {
        when(bookService.getBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Java Programming"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].titulo").value("Spring Framework"));
    }

    // Prueba para verificar que se obtiene un libro por su ID correctamente
    @Test
    public void testGetById() throws Exception {
        when(bookService.getBook(anyLong())).thenReturn(Optional.of(book1));

        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Java Programming"));
    }

    // Prueba para verificar que se guarda o actualiza un libro correctamente
    @Test
    public void testSaveUpdate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content("{ \"titulo\": \"Test Book\", \"autor\": \"Test Author\", \"isbn\": \"1234567890\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(bookService, times(1)).saveOrUpdate(any(Book.class));
    }

    // Prueba para verificar que se elimina un libro correctamente
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(bookService, times(1)).delete(1L);
    }

    // Prueba para verificar que se realizan búsquedas de libros correctamente
    @Test
    public void testSearchBooks() throws Exception {
        when(bookService.searchBooksByTitleAndAuthor(anyString())).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(MockMvcRequestBuilders.get("/books/search?query=Java"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Java Programming"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].titulo").value("Spring Framework"));
    }
}
