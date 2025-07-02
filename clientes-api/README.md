# API de Clientes - Perfunlandia

Esta es la API de clientes para el proyecto Perfunlandia. Maneja toda la información de los clientes del sistema.

## 🚀 Tecnologías

- **Spring Boot 3.4.0** - Framework principal
- **HATEOAS** - Para enlaces hipermedia
- **Swagger/OpenAPI** - Documentación de la API
- **JPA/Hibernate** - Persistencia de datos
- **MariaDB** - Base de datos
- **Lombok** - Reducción de código boilerplate

## 📋 Endpoints

### Endpoints Normales
- `POST /api/clientes` - Crear cliente
- `GET /api/clientes` - Listar todos los clientes
- `GET /api/clientes/{id}` - Obtener cliente por ID
- `PUT /api/clientes/{id}` - Actualizar cliente
- `DELETE /api/clientes/{id}` - Eliminar cliente

### Endpoints con HATEOAS
- `POST /api/clientes/hateoas` - Crear cliente con enlaces
- `GET /api/clientes/hateoas` - Listar clientes con enlaces
- `GET /api/clientes/hateoas/{id}` - Obtener cliente con enlaces
- `PUT /api/clientes/hateoas/{id}` - Actualizar cliente con enlaces
- `DELETE /api/clientes/hateoas/{id}` - Eliminar cliente

## 🔗 Enlaces HATEOAS

Los enlaces HATEOAS apuntan al API Gateway en `http://localhost:8888/api-gateway/clientes`

### Tipos de Enlaces:
- **self** - Enlace al recurso actual
- **collection** - Enlace a la colección de clientes
- **create** - Enlace para crear nuevo cliente
- **update** - Enlace para actualizar cliente
- **delete** - Enlace para eliminar cliente

## 📖 Documentación Swagger

Una vez que ejecutes la aplicación, puedes ver la documentación en:
```
http://localhost:8087/swagger-ui.html
```

**Nota:** Esta API se ejecuta en el puerto 8087.

## 🛠️ Cómo ejecutar

1. **Clonar el proyecto**
2. **Configurar la base de datos** en `application.properties`
3. **Ejecutar con Maven:**
   ```bash
   mvn spring-boot:run
   ```

## 📊 Estructura del Cliente

```json
{
  "idCliente": 1,
  "idUsuario": 123,
  "idDireccion": 456,
  "telefono": "123456789"
}
```

## 🔧 Configuración

Asegúrate de tener configurada tu base de datos MariaDB en el archivo `application.properties`.

---

**Nota:** Esta API está diseñada para trabajar con un API Gateway. Los enlaces HATEOAS apuntan al gateway para mantener la arquitectura de microservicios. 