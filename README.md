
# Proyecto parqueadero backend

Aplicativo para la gestión básica de un parqueadero


## Construido con

- Java 17
- SpringBoot 3.1.5
- PostgresSQL

## Variables de entorno

Para ejecutar el proyecto se debe modificar el archivo `application.properties`, para agregar los datos de conexión a una base de datos y la 'key' para el JWT(después del `=`) o usar las respectivas variables de entorno:
```
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
JWT_KEY=${JWT_KEY}
```


## Ejecución

Se ejecuta por defecto en el puerto 8080

Para ejecutar el aplicativo como springboot:
`gradlew bootRun`

O generar el jar y ejecutar con java
```
gradlew bootJar
java -jar build/libs/*.jar
```
## Peticiones Api

Dentro del archivo [Parqueadero Api requests.http](src/api_requests.http)