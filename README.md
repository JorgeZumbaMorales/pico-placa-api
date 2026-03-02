# Pico y Placa API

API REST desarrollada en **Spring Boot 3.5.1** para validar la circulación vehicular según la normativa de Pico y Placa.

---

## Información del Proyecto

- **Grupo:** com.jorgezumba
- **Artefacto:** pico-placa-api
- **Nombre:** pico-placa-api
- **Packaging:** JAR
- **Java:** 17
- **Spring Boot:** 3.5.1

---

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.5.1
- Spring Web
- Spring Data JPA
- H2 Database (modo archivo)
- Maven (Maven Wrapper)
- SpringDoc OpenAPI (Swagger)
- Docker
- Render (Deploy)

---

## 🌐 Deploy en Producción

API disponible en:

    https://pico-placa-api-ipgk.onrender.com

Swagger UI:

    https://pico-placa-api-ipgk.onrender.com/swagger-ui.html

---

## Endpoints Disponibles

### Validar circulación

POST `/api/pico-placa/validar`

Body ejemplo:

```json
{
  "placa": "ABC-1234",
  "fechaHora": "2026-03-02T14:00:00"
}
```
## ⚙️ Ejecutar Localmente (Backend)

1. Clonar repositorio:

git clone https://github.com/JorgeZumbaMorales/pico-placa-api.git

2. Ingresar al proyecto:

cd pico-placa-api

3. Ejecutar la aplicación con Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Disponible en:

http://localhost:8080

Swagger UI:

http://localhost:8080/swagger-ui.html