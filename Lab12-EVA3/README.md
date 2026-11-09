# Sistema de reservas hoteleras

Laboratorio 05 de la Evaluación 03 de Desarrollo de Aplicaciones Web. El sistema separa la gestión de huéspedes, habitaciones y reservas en tres microservicios, cada uno con su propio puerto y su propia base de datos.

**Estudiante:** Chavez Lazo, Karla Estefany

![Diagrama de microservicios y bases de datos](docs/diagrama-microservicios.png)

## Microservicios

| Servicio | Puerto | Base de datos | Responsabilidad |
|---|---|---|---|
| `huesped-service` | 8081 | `bd_huesped` | CRUD de huéspedes |
| `habitacion-service` | 8082 | `bd_habitacion` | CRUD de habitaciones |
| `reserva-service` | 8083 | `bd_reserva` | Registro de reservas, fechas y estados |

Los servicios no comparten tablas. Una reserva guarda solo `idHuesped` e `idHabitacion`, y antes de guardar consulta por REST a los otros dos servicios para comprobar que esos IDs existen.

## Requisitos

- Java 21
- Maven 3.9 o superior
- MySQL o MariaDB (probado con MariaDB 10.4 de XAMPP) en el puerto 3306
- Postman, para las pruebas

## Bases de datos

En el perfil `dev`, cada servicio crea su base y sus tablas al arrancar. Para crearlas de forma manual, ejecuta [database/script-bases-datos.sql](database/script-bases-datos.sql) en SQLyog. Es necesario para el perfil `prod`, que no crea ni modifica tablas.

## Ejecución

1. Inicia MySQL desde XAMPP.
2. Inicia los servicios en este orden:
   - `huesped-service`
   - `habitacion-service`
   - `reserva-service`

Reserva necesita a los otros dos para registrar o actualizar reservas.

**Desde IntelliJ:** agrega cada `pom.xml` como proyecto Maven (clic derecho y **Add as Maven Project**) y ejecuta la clase `*ServiceApplication` de cada servicio.

**Desde la terminal:** en la carpeta de cada servicio, en una terminal distinta para cada uno:

```bash
mvn -DskipTests package
```

```bash
java -jar target/huesped-service-0.0.1-SNAPSHOT.jar
```

Cambia el nombre del `.jar` según el servicio.

## Perfiles

El perfil activo por defecto es `dev`. Estos valores cambian entre ambientes:

| Valor | `dev` | `prod` |
|---|---|---|
| URL de la base de datos | Local, con valor por defecto; la crea si no existe | Variable `DB_URL` (obligatoria) |
| Usuario y contraseña | `root` sin contraseña por defecto (XAMPP) | Variables `DB_USER` y `DB_PASSWORD` (obligatorias) |
| `ddl-auto` | `update`: crea o actualiza las tablas | `validate`: solo comprueba que las tablas coincidan |
| `show-sql` | `true`: muestra el SQL en consola | `false` |
| URLs de Huésped y Habitación (solo Reserva) | `localhost:8081` y `localhost:8082` | Variables `HUESPED_URL` y `HABITACION_URL` (obligatorias) |

El puerto se puede cambiar en cualquier perfil con la variable `SERVER_PORT`.

## Endpoints

Los tres servicios exponen las mismas cinco operaciones:

| Método | Ruta | Acción | Respuesta correcta |
|---|---|---|---|
| GET | `/` | Listar | 200 |
| GET | `/{id}` | Buscar por ID | 200 |
| POST | `/` | Crear | 201 |
| PUT | `/{id}` | Actualizar | 200 |
| DELETE | `/{id}` | Eliminar | 204 |

Las rutas base son:

- `http://localhost:8081/api/huespedes`
- `http://localhost:8082/api/habitaciones`
- `http://localhost:8083/api/reservas`

### Cuerpos de ejemplo

**Huésped.** El teléfono es opcional y el documento no se puede repetir.

```json
{
  "nombres": "Ana",
  "apellidos": "Rojas",
  "documento": "12345678",
  "correo": "ana@example.com",
  "telefono": "987654321"
}
```

**Habitación.** El tipo admite `SIMPLE`, `DOBLE`, `MATRIMONIAL` o `SUITE`, y el número no se puede repetir.

```json
{
  "numero": "101",
  "tipo": "DOBLE",
  "precioPorNoche": 150.50,
  "capacidad": 2
}
```

**Reserva.** El estado admite `PENDIENTE`, `CONFIRMADA` o `CANCELADA`. Es opcional: una reserva nueva sin estado queda `PENDIENTE`, y al actualizar sin estado se conserva el actual.

```json
{
  "idHuesped": 1,
  "idHabitacion": 1,
  "fechaEntrada": "2026-12-10",
  "fechaSalida": "2026-12-15"
}
```

## Respuestas de error

| Código | Cuándo ocurre |
|---|---|
| 400 | Datos inválidos, JSON mal escrito, ID no numérico o fecha de salida no posterior a la de entrada |
| 404 | No existe el huésped, la habitación o la reserva |
| 409 | Documento o número de habitación repetido |
| 503 | Reserva no pudo consultar a Huésped o Habitación |

Ejemplo:

```json
{
  "estado": 400,
  "mensaje": "La fecha de salida debe ser posterior a la fecha de entrada"
}
```

## Limitaciones conocidas

- No se impide reservar la misma habitación dos veces en las mismas fechas.
- Si se elimina un huésped o una habitación que tiene reservas, esas reservas conservan el ID, porque no hay llaves foráneas entre bases de datos distintas.

