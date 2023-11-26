
# Proyecto parqueadero backend

Aplicativo para la gestión básica de un parqueadero


## Construido con

- Java 17
- SpringBoot 3.1.5
- PostgresSQL

## Variables de entorno

Para ejecutar el proyecto se debe modificar el archivo `application.properties`, para agregar los datos de conexión a una base de datos (después del `=`) o usar las respectivas variables de entorno:
```
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
```
En caso de no tener las tablas creadas, agregar también lo siguiente para que hibernate las genere:
`spring.jpa.hibernate.ddl-auto=update`


## Ejecución

Se ejecuta por defecto en el puerto 8080

Construir la aplicación:
`gradlew build`

Para ejecutar el aplicativo como springboot:
`gradlew buildbootRun`

O generar el jar y ejecutar con java
```
gradlew bootJar
java -jar build/libs/*.jar
```
## Peticiones Api

Dentro del archivo [Parqueadero Api requests.http](src/api_requests.http)