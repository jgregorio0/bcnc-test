# BCNC JAVA DEVELOPER TEST
This is a test for BCNC for the interview of Java Developer position done by Jesus Gregorio Perez.

# Description
It is an SpringBoot microservice 
that expose 3 endpoints
//TODO fill info 
1. ejecute un algoritmophotos que enriquezca unos datos obtenidos a través de un API (2 endpoints), para posteriormente guardarlos en una base de
datos en memoria H2.
2. ejecute un algoritmo que enriquezca unos datos obtenidos a través de un API (2 endpoints) sin posibilidad de utilizar base de datos y los
devuelva en la petición.
3. GET de la base de datos en memoria H2

# Requirements
For building and running the application you need:
- Maven 3
- JDK 21

# Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the de.codecentric.springbootsample.Application class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

mvn spring-boot:run

# Deploying the application to Docker
// TODO

# Technologies
This project has been generated using Spring Initializr. 

It uses Java 21, Spring Boot 3.2.2 and Maven 3.

Se usa OpenJDK 21.0.2 para el desarrollo, sin embargo para un proyecto de mayor embergadura se podrian considerar otras distribuciones. 

Se utiliza Lombok para auto generar codigo mediante anotaciones y facilitar la lectura del codigo.

Se incluye Spring Web para el desarrollo de controladores y mapeo de endpoints.

Se incluye Spring Data JPA para el desarrollo de la capa de persistencia.

Se incluye MapStruct para el mapeo entre objetos.

Se usa RestTemplate para el consumo de APIs externas.

# Architecture
Se va a seguir una arquitectura API REST basada en el protocolo HTTP. El diseño de la APIs REST se justifica por ser una arquitectura robusta que permite:
- el desacoplamiento entre cliente y servidor 
- la escalabilidad al ser stateless
- favorece la simplicidad y el mantenimiento por seguir los estandares web 
- eficiente permitiendo el cacheado 
- flexible en el formato de los datos 
- facilita la interoperabilidad al ser compatibles con una amplia variedad de plataformas y tecnologías
- seguras mediante el uso de HTTPS, CORS o metodos de autenticacion.

# Design
Voy a diseñar una APIs REST basada en Java, usando el framework Spring Boot y que exponga los endpoints solicitados.

No se va a considerar la implementacion de:
- seguridad mediante el paquete Spring Security por ser un test, sin embargo, en un proyecto real seria imprescindible para exponer estos endpoints a internet.
- // TODO documentacion openapi?
- cache para mejorar el rendimiento, que igualmente seria muy recomendable en un proyecto real.
- paginacion al devolver recursos para mejorar el rendimiento

## Overview
El uso del lenguaje Java para el desarrollo, ademas de por razones obvias relacionadas con el puesto, esta justificado por:
- ser un lenguaje maduro y estable, ampliamenta adoptado y una gran comunidad de desarrolladores
- tiene buen soporte para APIs REST con Spring Boot, permitiendo crear aplicaciones robustas y escalables
- dispone de muchas herramientas, librerías y recursos disponibles

Esta API es stateless porque:
- al evitar dependencias de estado, mejora la escalabilidad y el rendimiento

Los endpoints seran expuestos mediante protocolos HTTP porque:
- HTTP es un estandar ampliamente adoptado que favorece la interoperabilidad 
- incluye metodos predefinidos GET, POST, PUT, DELETE que diferencian claramente las operaciones CRUD (Create, Read, Update, Delete).

Para el manejo de datos sera usado el formato JSON en las peticiones y respuestas porque: 
- es un estandar ampliamente adoptado, soportado y compatible con distintas tecnologías
- fácil de leer y manejar
- eficiente en la serialización y deserialiacion.
- es flexible y puede representar estructuras de datos complejas
- existen multiples librerias que facilitan el manejo de estructuras en JSON
- las APIs de albums y photos que vamos a consumir usan el formato JSON en sus respuestas

## Estructura de directorios
La estructura de directorios sigue la arquitectura ddd hexagonal incluyendo los paquetes:
- application
- domain
- infrastructure 

## Application Layer
Disponemos de un adaptador AlbumRestInputAdapter que expone los 3 endpoints.

### Endpoints
Los endpoints seran definidos por un metodo HTTP y una ruta. 

El primer nivel de la ruta es un prefio /api porque:
- identificamos que pertenecen a una API
- convencion ampliamente adoptada
- facilita la agrupacion de los endpoints para configuraciones comunes como las politicas de seguridad

El segundo nivel de la ruta es /v1 porque:
- facilita el versionado
- identifica claramente la version para relacionar la documentacion

Para el primer endpoint usaremos el método HTTP POST y la ruta /api/v1/albums porque: 
- el metodo POST denota que vamos a crear un recurso en nuestro sistema al insertarlo en base de datos
- se mantiene la idempotencia ya que si los datos identificativos de albums y fotos son identicos no se crearan nuevos recursos
- se crean recursos de tipo album

Para segundo endpoint y tercer endpoint usaremos el método HTTP GET y la ruta /api/v1/:source/albums porque:
- el metodo GET denota que no vamos a crear ningun recurso en nuestro sistema y que se devuelve informacion
- /:source es un parametro que indica la fuente de datos de la que provienen los recursos
- /albums informa de que se devuelve un listado de objetos album
- Se podrian crear 2 endpoints GET /api/v1/internal/albums y GET /api/v1/external/albums para diferenciar la fuente de datos. Sin embargo, de esta forma se facilita extender la funcionalidad inicial.

## Domain Layer
Definimos los objetos Album y Photo. Ambos sobrescriben los metodos equals y hashcode para comparar objetos por el identificador.

El servicio AlbumService contiene la logica de negocio para obtener Album y Photo de las APIs y de base de datos; enriquecer cada Album añadiendo cada Photo al album correspondiente y persistir Album y Photo.

La estructura de datos elegida para manipular los objetos de tipo Album es un par clave-valor Map<Long, Album> usando el identificador del Album como clave y el Album como Objeto. De esta forma podremos asociar las photos al album posteriormente. 

La estructura de datos elegida para manipular los objetos de tipo Photo es un conjunto Set<Photo> para evitar duplicados que podrian generarse en un List.

## Infraestructure Layer

### External APIs
Las APIs externas de Album y Photo se consumen usando RestTemplate y mapeando el resultado en Map<Long, Album> y Set<Photo> respectivamente.

Al consumir la API photos se devuelve Set para evitar duplicados que podrian generarse en un List. 

### Data Layer
Se usa hibernate para la capa de persistencia //TODO JG justificar

AlbumEntity y PhotoEntity son entidades. AlbumEntity contiene una relacion @OneToMany hacia PhotoEntity ya que cada PhotoEntity contiene el identificador del AlbumEntity al que pertenece.

La base de datos H2 se define en el fichero de configuración application.properties.

# Patterns
DDD: se usa en conjunto con la arquitectura hexagonal para poner el acento en el dominio del negocio y en su modelo como herramienta de comunicación entre negocio y tecnología
// TODO ejemplo
Patron Builder: se usa el patron Builder para la creacion de objetos Album y Photo. // TODO justificar
// TODO ejemplo

# Tests
Domain layer is tested in AlbumServiceTest where unit tests are performed.
// TODO show results

Infrastructure layer is tested in AlbumPersistenceOutputAdapterTest where integration tests are performed.
// TODO show results

// TODO Continuous Integration and Deployment (CI/CD):
// TODO Logging
// TODO Uso de las herramientas que proporciona Github o similares para la gestión del código (utilizar nomenclatura estándar de commits, ramas, etc):
// TODO Uso de controllerAdvice y gestión de excepciones:
// TODO Uso de jacoco u otra herramienta para monitorizar la cobertura de código de test:
