# BCNC Java Developer Test
This is a test for BCNC as applicant for the Java Developer position performed by Jesus Gregorio Perez.

# Description
This project is a Spring Boot application that implement 3 endpoints:
1. POST /api/v1/albums executes an algorithm that enhances data obtained through an API (2 endpoints: Albums and Photos), subsequently saving them in an in-memory H2 database.
2. GET /api/v1/external/albums executes an algorithm that enhances data obtained through an API (2 endpoints: Albums and Photos) and return albums.
3. GET /api/v1/internal/albums return albums stored in memory H2 database.

# Requirements
For building and running the application you need:
- Maven 3
- JDK 21

# Running the application locally
Running the application using Spring Boot Maven plugin:
```
mvn spring-boot:run
```

Test endpoint 1:
```
curl -XPOST -H "Content-type: application/json" 'http://localhost:8080/api/v1/albums'
```

Test endpoint 2:
```
curl -XGET -H "Content-type: application/json" 'http://localhost:8080/api/v1/external/albums'
```

Test endpoint 3:
```
curl -XGET -H "Content-type: application/json" 'http://localhost:8080/api/v1/internal/albums'
```

# Technologies
This project employs Java 21, Spring Boot 3.2.2, and Maven 3. 

While OpenJDK 21.0.2 is used for development, other distributions might be considered for larger projects. 

Lombok is utilized for automatic code generation via annotations, enhancing code readability. 

Spring Web is included for controller development and endpoint mapping.

Spring Data JPA is used for the persistence layer. 

MapStruct is integrated for object mapping.

RestTemplate is employed for external API consumption. 

JaCoCo is used for test coverage to ensure thorough testing and maintain high software quality.

# Architecture
The choosen architecture is a Spring Boot application implementing a REST API following Domain-Driven Design (DDD) and the hexagonal pattern. The main components and layers involved are:
- Domain Layer: Contains the business logic and models. The services [AlbumService.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Fdomain%2Fservice%2FAlbumService.java) and [PhotoService.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fphoto%2Fdomain%2Fservice%2FPhotoService.java) defines the core business rules and is independent of any specific technology.
- Application Layer: Orchestrates the flow of data in and out the domain objects through ports. It acts as a bridge between the domain and the infrastructure layers.
- Infrastructure Layer: Implements the interfaces defined by the application layer to interact with external systems through adapters. This includes API rest and persistence management.

By following this structure:
- the application becomes loosely coupled and highly cohesive which makes it easier to change individual parts without affecting others
- ensures that the core business logic remains independent of any external influences

# Design
I will design a REST API based on Java, using the Spring Boot framework to expose the requested endpoints.

Key aspects of the implementation will not be considered for this project:
- Security measures using Spring Security, although crucial for real-world internet-exposed endpoints, are omitted as this is a test scenario.
- OpenAPI documentation which is recommended for proper API documentation.
- Caching and Pagination mechanisms to enhance performance, which is highly advisable in production environments when data length increase.
- Develop Dockerfile to deploy isolated development environment and make easier reproducibility.
- Logging for Debugging and Troubleshooting.

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

The choice of Java for development is justified for
- its maturity, stability, widespread adoption, and large community of developers. 
- Additionally, Java has excellent support for building RESTful APIs with Spring Boot, allowing for the creation of robust and scalable applications. 
- There are numerous tools, libraries, and resources available for Java, which contributes to its popularity and utility in development.

The API design is stateless because avoiding state dependencies enhances scalability and performance allowing servers to be easily replicated and load balanced.

Endpoints will be exposed using the HTTP protocol for the following reasons:
- HTTP is a widely adopted standard that promotes interoperability.
- It includes predefined methods like GET, POST, PUT, DELETE, which clearly differentiate CRUD operations.

For handling data, the JSON format will be used in requests and responses because:
- JSON is a widely adopted, supported, and compatible standard.
- It is easy to read and manage.
- JSON is efficient for serialization and deserialization.
- JSON is flexible and can represent complex data structures.
- There are many libraries that simplify working with JSON structures.
- The photo album and photo APIs we are going to consume use JSON in their responses.

## Directory structure
Directory structure follows a Maven standard directory layout including src/main/ java and resources for source code and src/test/ java and resources for Testing.

Package name follow Java convention starting by the company.

The directory structure is defined by domain (album, photo or common) and layer (application, domain or infrastructure) and aligns with the DDD Hexagonal Architecture pattern.
- application: Contains the application ports
- domain: Holds the business logic and models.
- infrastructure: Implements the interfaces defined by the application layer ports through the adapters to interact with external systems, such as database and API rest.
 
## Application Layer
The input ports are defined as follows:
- [EnhanceAlbumsInputPort.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Fapplication%2Fports%2Finput%2FEnhanceAlbumsInputPort.java): This port handles the client's request to the application to enhance Albums with Photos and store the result.
- [GetExternalAlbumsInputPort.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Fapplication%2Fports%2Finput%2FGetExternalAlbumsInputPort.java): This port handles the client's request to the application to retrieve Albums from an external service, specifically a RESTful API in our case.
- [GetInternalAlbumsInputPort.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Fapplication%2Fports%2Finput%2FGetInternalAlbumsInputPort.java): This port handles the client's request to the application to retrieve Albums from an internal service, specifically a Database in our case.
- [GetPhotosInputPort.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fphoto%2Fapplication%2Fports%2Finput%2FGetPhotosInputPort.java): This port handles the client's request to the application to retrieve Photos, again from a RESTful API.

And the output ports are defined as:
- [GetAlbumsPersistenceOutputPort.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Fapplication%2Fports%2Foutput%2FGetAlbumsPersistenceOutputPort.java): Used by the application to fetch Albums from the database.
- [GetAlbumsRestOutputPort.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Fapplication%2Fports%2Foutput%2FGetAlbumsRestOutputPort.java): Used by the application to fetch Albums from a RESTful API.
- [SaveAlbumPersistenceOutputPort.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Fapplication%2Fports%2Foutput%2FSaveAlbumPersistenceOutputPort.java): Used by the application to save Albums to the database.
- [PhotoRestOutputPort.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fphoto%2Fapplication%2Fports%2Foutput%2FPhotoRestOutputPort.java): Used by the application to fetch Photos from a RESTful API.

## Domain Layer
Domain models are defined as:
- [Album.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Fdomain%2Fmodel%2FAlbum.java): An Album model based on the response from the Albums API.
- [Photo.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fphoto%2Fdomain%2Fmodel%2FPhoto.java): A Photo model based on the response from the Photos API.
- The Album and Photo objects are defined, with both overriding the equals() and hashCode() methods to compare objects by their identifier.

Services implements input Ports definition. They contain the business logic:
- [AlbumService.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Fdomain%2Fservice%2FAlbumService.java): Implementation of business logic related to Albums, such as enhancing Albums with Photos and transforming data structures.
- [PhotoService.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fphoto%2Fdomain%2Fservice%2FPhotoService.java): Establishment of a layer between application ports and infrastructure adapters to interface with the Photos API.
- The chosen data structure to manipulate Album objects is a Map<Long, Album> using the Album identifier as the key and the Album object itself as the value. This allows associating photos with the correct album later on.
- The chosen data structure to manipulate Photo objects is a Set<Photo> to prevent duplicates that could occur in a List.

## Infrastructure Layer
The Output Adapters implement the definition of output Ports. 

Persistence operations involve:
- [AlbumPersistenceOutputAdapter.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Finfrastructure%2Falbum%2Fadapters%2Fpersistence%2Foutput%2FAlbumPersistenceOutputAdapter.java): Retrieving and saving albums from the database. 
- [AlbumEntity.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Finfrastructure%2Falbum%2Fadapters%2Fpersistence%2Foutput%2FAlbumEntity.java): Representing Album in the database.
- [AlbumRepository.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Finfrastructure%2Falbum%2Fadapters%2Fpersistence%2Foutput%2FAlbumRepository.java): Album JPA repository to access the database.
- [AlbumMapper.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Finfrastructure%2Falbum%2Fadapters%2Fpersistence%2Foutput%2FAlbumMapper.java): Mapper from Album domain to AlbumEntity database representation.
- [PhotoEntity.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fphoto%2Finfrastructure%2Fadapters%2Foutput%2Fpersistence%2FPhotoEntity.java): Representing Photo in the database.
- This project uses Hibernate for the persistence layer.
- AlbumEntity contains a @OneToMany relationship towards PhotoEntity because each PhotoEntity contains the identifier of the AlbumEntity to which it belongs.
- The H2 database is defined in the application.properties configuration file

API rest is implemented by:
- [AlbumRestOutputAdapter.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Finfrastructure%2Falbum%2Fadapters%2Frest%2Foutput%2FAlbumRestOutputAdapter.java): Fetching Albums from the API Rest.
- [AlbumRestOutputRepositoryImpl.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Falbum%2Finfrastructure%2Falbum%2Fadapters%2Frest%2Foutput%2FAlbumRestOutputRepositoryImpl.java): Implementing an API REST repository to access the Album API using RestTemplate.
- [PhotoRestOutputAdapter.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fphoto%2Finfrastructure%2Fadapters%2Foutput%2Frest%2FPhotoRestOutputAdapter.java): Accessing Photos from the API Rest.
- [PhotoRestOutputRepositoryImpl.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fphoto%2Finfrastructure%2Fadapters%2Foutput%2Frest%2FPhotoRestOutputRepositoryImpl.java)[PhotoRestOutputRepository.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fphoto%2Finfrastructure%2Fadapters%2Foutput%2Frest%2FPhotoRestOutputRepository.java): API REST repository Implementation to access the Photo API using RestTemplate.   

## Endpoints
Endpoints will be defined by an HTTP method and a route.

The first segment of the route is a prefix /api because:
- It identifies that the endpoints belong to an API.
- It is a convention widely adopted.
- It facilitates grouping of endpoints for common configurations like security policies.

The second segment of the route is /v1 because:
- It facilitates versioning.
- It clearly identifies the version for linking documentation.

For the first endpoint, we will use the HTTP POST method and the route /api/v1/albums because:
- The POST method denotes that we are creating a resource in our system by inserting it into the database.
- Idempotence is maintained since if the identifiers for albums and photos are identical, no new resources will be created.
- Resources of type album are created.

For the second endpoint, we will use the HTTP GET method and the route /api/v1/external/albums because:
- The GET method denotes that we are not creating any resource in our system and that information is returned.
- /external is a parameter indicating that the source of the albums is external.
- /albums indicates that a list of album objects is returned.

And for the third endpoint, we will use the HTTP GET method and the route /api/v1/internal/albums because:
- The GET method denotes that we are not creating any resource in our system and that information is returned.
- /internal is a parameter indicating that the source of the albums is internal.
- /albums indicates that a list of album objects is returned 

## Exception management
Centralized exception handling is hadled by: [AdviceExceptionHandler.java](src%2Fmain%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fcommon%2Finfrastructure%2Fexception%2FAdviceExceptionHandler.java)

@ControllerAdvice is used because:
- allows you to handle exceptions across multiple controllers in a single place. 
- defining a global exception handler, you can ensure that all exceptions result in consistent error responses

# Patterns
- DDD Hexagonal: emphasize the business domain and its model as a communication tool between business and technology.
- Builder Pattern: The Builder pattern is used for the creation of Album and Photo objects.
- AOP (Aspect Oriented Programming) Pattern: This pattern is used for exception handling.

# Tests
Domain layer is tested in AlbumServiceTest[AlbumServiceTest.java](src%2Ftest%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Fdomain%2Fservice%2FAlbumServiceTest.java) where unit tests are performed.

Infrastructure layer is tested in AlbumPersistenceOutputAdapterTest[AlbumRepositoryTest.java](src%2Ftest%2Fjava%2Fcom%2Fbcnc%2Fjava%2Fdeveloper%2Ftest%2Fjgregoriotest%2Finfrastructure%2Fadapters%2Falbum%2Fpersistence%2Foutput%2FAlbumRepositoryTest.java) where integration tests are performed.

## Test coverage
Test coverage results available: [https://ibb.co/KVr1hx6](https://ibb.co/KVr1hx6)

High test coverage is not a consideration for this test project.