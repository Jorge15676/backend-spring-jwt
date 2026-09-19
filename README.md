# API REST con JWT

API REST hecha con Spring Boot, PostgreSQL y JWT. Permite registrar usuarios, iniciar sesión y acceder a rutas protegidas mediante un token.

## Tecnologías

Java 17 · Spring Boot 4.1.1 · Spring Security · JWT · BCrypt · JPA/Hibernate · PostgreSQL · Docker · Swagger

La aplicación está separada en controladores, servicios, repositorios, DTOs y entidades.

## Ejecutar el proyecto

Necesario Docker Desktop y Docker Compose.

```bash
git clone https://github.com/Jorge15676/backend-spring-jwt.git
cd backend-spring-jwt
cp .env.example .env
docker compose up -d --build
```

La API quedará disponible en `http://localhost:8080`.

Swagger:

`http://localhost:8080/swagger-ui/index.html`


## Endpoints

- `GET /` — Comprueba que la API funciona.
- `POST /api/v1/auth/register` — Registra un usuario.
- `POST /api/v1/auth/login` — Devuelve un token JWT.
- `GET /api/v1/resources` — Ruta protegida. Requiere `Bearer <token>`.

## Pruebas

Las pruebas usan H2 y se ejecutan con:

```bash
./mvnw clean test
```

Las contraseñas se guardan usando BCrypt y la autenticación funciona mediante JWT sin sesiones en el servidor.
