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

## Estructura de respuesta

```
[
  {
    "date": "2022-09-12T05:00:01.586Z",
    "link": "[\"/deportes/futbol/2022/09/12/atlantida-sigue-en-la-pelea-en-la-primera-b/\"]",
    "photoLink": "https://cloudfront-us-east-1.images.arcpublishing.com/abccolor/7ZXZKUUWXZE7DNWV77UOM7GZ6M.jpg",
    "title": "Atlántida sigue en la pelea en la Primera B",
    "summary": "Atlántida se impuso de visitante ayer por 2-0 frente a Cristóbal Colón de J. Augusto Saldívar, en el duelo que cerró la disputa de la 27ª ronda del torneo de la Primera División B.",
    "photoContent": "",
    "contentTypePhoto": ""
  },
  {
    "date": "2022-04-20T15:01:19.422Z",
    "link": "[\"/nacionales/2022/04/20/detienen-a-madre-que-hizo-desaparecer-a-su-hija-para-que-no-fuera-vacunada-ni-vaya-a-la-escuela/\"]",
    "photoLink": "https://cloudfront-us-east-1.images.arcpublishing.com/abccolor/ZM2MIT6MTJDLNL42WN42VLLY74.jpg",
    "title": "Detienen a madre que hizo “desaparecer” a su hija para que no fuera vacunada ni vaya a la escuela",
    "summary": "Tras una investigación, la Policía Nacional dio con la casa alquilada en la que se refugiaban una madre y una niña que fue reportada como desaparecida. Los familiares anteriormente denunciaron que la madre no quería que la menor fuera vacunada ni que vaya a la escuela porque la iban a “lavar el cerebro”.",
    "photoContent": "",
    "contentTypePhoto": ""
  },
  {
    "date": "2022-03-10T13:59:06.207Z",
    "link": "[\"/espacio-reservado/2022/03/10/comunicado-de-la-cooperativa-multiactiva-capiata-limitada/\"]",
    "photoLink": "https://cloudfront-us-east-1.images.arcpublishing.com/abccolor/K3UWBBY3MRH67BCAVYVVDV5YHY.jpg",
    "title": "Comunicado de la Cooperativa Multiactiva Capiatá Limitada ",
    "summary": "El Consejo de Administración de la Cooperativa Mutiactiva Capiatá Limitada comunica a la opinión pública en general y, en particular a sus asociados:",
    "photoContent": "",
    "contentTypePhoto": ""
  }
]
```
