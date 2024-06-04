package com.linkfast.RetoLinkfast.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;
    private String isbn;

    // Constructor por defecto
    public Book() {
    }

    // Constructor con parámetros
    public Book(String titulo, String autor, LocalDate fechaPublicacion, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.isbn = isbn;
    }

    // Getters y setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Método toString para representación en cadena del objeto
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", isbn='" + isbn + '\'' +
                '}';
    }

}
