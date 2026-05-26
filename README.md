# Orders API

REST API desarrollada con Spring Boot y MySQL para la gestión de usuarios.

## 🚀 Tecnologías utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Swagger / OpenAPI
- JUnit
- Mockito
- Git & GitHub

---

## 📌 Funcionalidades implementadas

✅ Crear usuarios

✅ Obtener usuarios

✅ Buscar usuario por ID

✅ Buscar usuario por email

✅ Actualizar usuarios

✅ Eliminar usuarios

✅ Validaciones con DTO (`@Valid`, `@NotBlank`, `@Email`)

✅ Manejo global de excepciones (`GlobalExceptionHandler`)

✅ Paginación

✅ Timestamps automáticos (`createdAt`, `updatedAt`)

✅ Tests unitarios con JUnit y Mockito

---

## 🌐 Endpoints disponibles

### Usuarios

GET /users

GET /users/{id}

GET /users/email/{email}

POST /users

PUT /users/{id}

DELETE /users/{id}

---

## ▶️ Cómo ejecutar el proyecto

1. Clonar repositorio

```bash
git clone URL_DEL_REPOSITORIO
```

2. Configurar MySQL y credenciales en:

```properties
application.properties
```

3. Ejecutar:

```bash
mvn spring-boot:run
```

4. Acceder a Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 📌 Estado del proyecto

Proyecto finalizado con funcionalidades CRUD, validaciones, manejo de errores, paginación y tests unitarios.