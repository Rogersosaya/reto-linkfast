Este reto es una aplicación de Spring Boot que utiliza MySQL como base de datos.

## Requisitos

- Java 11 o superior
- Maven 3.6.0 o superior

## Programas
- MySQL Server
- Postman
- IDE para Java (por ejemplo, IntelliJ IDEA, Eclipse, o NetBeans)
- Gestor de base de datos (por ejemplo, MySQL Workbench, DBeaver, o HeidiSQL)

## Configuración de la Base de Datos

1. **Abrir Gestor de MySQL:**
   - Asegurarse de que el servidor MySQL esté en funcionamiento.

2. **Crear la base de datos:**
   - Conectar a MySQL y crear una base de datos para el proyecto:
     ```sql
     CREATE DATABASE retolinkfast
     ```

## Clonar el repositorio:
   - ```bash
     git clone https://github.com/usuario/repositorio.git](https://github.com/Rogersosaya/reto-linkfast.git
     
## Configurar credenciales de application.properties o application.yml
   - Si usas `application.properties`:
     ```server.port=8081
     spring.datasource.url=jdbc:mysql://localhost:3306/retolinkfast
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     server.servlet.contextPath=/api
     ```

   - Si usas `application.yml`:
     ```yaml
     spring:
       datasource:
         url: jdbc:mysql://localhost:3306/retolinkfast
         username: tu_usuario
         password: tu_contraseña
       jpa:
         hibernate:
           ddl-auto: update
         show-sql: true
         properties:
           hibernate:
             dialect: org.hibernate.dialect.MySQL5Dialect
     ```

## Compilar y Ejecutar el Proyecto
  Click derecho en RetoLinkfastApplication y darle a "Run Retolinkfast..."
## Poblar tabla book
  En tu gestor de Base de datos agrega estos registros de prueba
   ```INSERT INTO book (titulo, autor, fecha_publicacion, isbn) VALUES ('Java Programming', 'John Doe', '2020-01-01', '1234567890');
     INSERT INTO book (titulo, autor, fecha_publicacion, isbn) VALUES ('Spring Framework', 'Jane Smith', '2021-01-01', '0987654321');
     INSERT INTO book (titulo, autor, fecha_publicacion, isbn) VALUES ('"El Señor de los Anillos"', 'J.R.R. Tolkien', '1954-07-29', '123456789012');
     INSERT INTO book (titulo, autor, fecha_publicacion, isbn) VALUES ('C++ para principiantes', 'Peter Jones', '01-03-2022', '9876543210');
```

## Probar APIs 
1. **Abrir Postman**

2. **Crear una Nueva Colección**
   - Clic en "Collections" > "New Collection"
   - Nombre: `Books API`

3. **Configurar las Solicitudes**

   Crear un Libro (POST)
   - **Método:** `POST`
   - **URL:** `http://localhost:8081/books`
   - **Headers:** `Content-Type: application/json`
   - **Body:**
     ```json
     "titulo": "El nombre del agua",
      "autor": "Patrick Sanchez",
      "fechaPublicacion": "2007-03-27",
      "isbn": "9788401352799"
     ```

   Obtener Todos los Libros (GET)
   - **Método:** `GET`
   - **URL:** `http://localhost:8081/books`

   Obtener un Libro por ID (GET)
   - **Método:** `GET`
   - **URL:** `http://localhost:8081/books/{id}`
   - Nota: Reemplazar `{id}` con el ID real del libro.

   Actualizar un Libro (POST)
   - **Método:** `POST`
   - **URL:** `http://localhost:8081/books`
   - **Headers:** `Content-Type: application/json`
   - **Body:**
     ```json
     {
      "bookId": 2,
      "titulo": "El nombre del agua2",
      "autor": "Patrick Sanchez",
      "fechaPublicacion": "2007-03-27",
      "isbn": "9788401352799"
    }
     ```
   - Nota: Reemplazar `{id}` con el ID del libro.

   Eliminar un Libro (DELETE)
   - **Método:** `DELETE`
   - **URL:** `http://localhost:8081/books/{id}`
   - Nota: Reemplazar `{id}` con el ID del libro.

   Buscar Libros (GET)
   - **Método:** `GET`
   - **URL:** `http://localhost:8081/search`
   - **Params:** `query` - Consulta de búsqueda (ej. `?query=Harry Potter`)
   - **Descripción:** Buscar libros por título o autor.

## Pruebas Unitarias

Este proyecto incluye pruebas unitarias para garantizar el correcto funcionamiento de las funciones y métodos implementados. Sigue estos pasos para ejecutar las pruebas unitarias:

1. **Navegar hasta las Pruebas Unitarias** 
   - En el explorador de archivos del IDE, busca el directorio `src/test` que contiene las pruebas unitarias.

2. **Seleccionar y Ejecutar las Pruebas**
   - Dentro del directorio de pruebas, localiza el archivo que contiene las pruebas que deseas ejecutar.
   - Haz clic derecho en el archivo de prueba.
   - Selecciona la opción "Run" o "Run <nombre del archivo>" para ejecutar todas las pruebas en el archivo.
3. **Verificar los Resultados**
   - Observa la ventana de resultados de pruebas para verificar que todas las pruebas se ejecuten correctamente.
   
## Observaciones
- Se puede utilizar la dependencia Lombok para generar automáticamente los métodos getters y setters, entre otros, mediante anotaciones. Sin embargo, esta dependencia no ha sido incluida en el proyecto actual debido a que no está dentro de las dependencias aprobadas para su uso.

- Aunque es posible crear manualmente los métodos CRUD en el repository, se ha optado por utilizar la interfaz JpaRepository proporcionada por Spring Data JPA. Esto permite que los métodos CRUD se generen automáticamente, logrando un código más limpio.
