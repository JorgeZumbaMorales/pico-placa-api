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

## Ejecutar localmente

```bash
./mvnw spring-boot:run
```

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
