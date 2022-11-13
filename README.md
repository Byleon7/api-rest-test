# NOTAS

Proyecto API REST

## Tecnologia
Java 11
Spring Boot 2.7.5
Maven 3

Para construir y ejecutar necesita:

- [java jdk 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Ejecucion local

Ejecutar método `main` en la clase `ApiRestTestApplication.java` desde su IDE.

## Invocar a la API

Mediante Swagger UI ingresar a URL: http://localhost:8080/swagger-ui/index.html

 - Método: **GET (/api/consulta)**
 - Request URL: http://localhost:8080/api/consulta?q=capiata&f=false
    - **q:** texto de busqueda (parámetro requerido). En el ejemplo el valor es 'Capiata'.
    - **f:** parámetro que indica si debe retornar la imágen de la publicacón codificada en Base64, valores posibles true/false. (parámetro opcional).
