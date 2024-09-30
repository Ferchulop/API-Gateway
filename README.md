# API-Gateway
Esta API Gateway permite gestionar un servicio de juegos con operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los juegos. Además, implementa la funcionalidad de autenticación de usuarios mediante JWT (JSON Web Token), protegiendo las rutas mediante un sistema de seguridad basado en Spring Security.

## Características:
- Autenticación y autorización: Uso de JWT para autenticar las solicitudes y proteger las rutas de la API.
- Operaciones CRUD: Permite crear, obtener, actualizar y eliminar juegos mediante las rutas correspondientes.
- Gestión de errores: Implementación de manejo de excepciones con respuestas personalizadas.
- Logging: Uso de SLF4J para registrar los errores y el comportamiento de la API.
  
## Tecnologías utilizadas:
- Spring Boot: Framework principal para el desarrollo de la API.
- Spring Security: Gestión de la seguridad de la API con autenticación JWT.
- Lombok: Para reducir líneas de código mediante anotaciones (getters, setters...).
- PostgreSQL: Base de datos relacional utilizada para almacenar información de usuarios y juegos.
- Hibernate y JPA: Para el manejo de la persistencia de datos en la base de datos.
- SLF4J: Para el logging y manejo de mensajes de error.
- JWT (Json Web Token): Para la generación y verificación de tokens de autenticación.
- Postman/Swagger: Documentación y verificación de la API.

![API-Gateway](https://github.com/user-attachments/assets/9f19485d-0951-4281-b7de-e73013336c55)
