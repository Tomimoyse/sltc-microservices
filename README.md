### Estructura del Proyecto

```
mi-proyecto/
│
├── docker-compose.yml
├── README.md
│
├── microservicio1/
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── ejemplo/
│   │   │   │           └── microservicio1/
│   │   │   │               ├── Microservicio1Application.java
│   │   │   │               ├── controller/
│   │   │   │               ├── service/
│   │   │   │               ├── repository/
│   │   │   │               └── model/
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── db/
│   │   │           └── schema.sql
│   │   └── test/
│   │       └── java/
│   │           └── com/
│   │               └── ejemplo/
│   │                   └── microservicio1/
│   │                       └── Microservicio1ApplicationTests.java
│   └── Dockerfile
│
├── microservicio2/
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── ejemplo/
│   │   │   │           └── microservicio2/
│   │   │   │               ├── Microservicio2Application.java
│   │   │   │               ├── controller/
│   │   │   │               ├── service/
│   │   │   │               ├── repository/
│   │   │   │               └── model/
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── db/
│   │   │           └── schema.sql
│   │   └── test/
│   │       └── java/
│   │           └── com/
│   │               └── ejemplo/
│   │                   └── microservicio2/
│   │                       └── Microservicio2ApplicationTests.java
│   └── Dockerfile
│
└── keycloak/
    ├── docker-compose.yml
    └── realm.json
```

### Descripción de Archivos y Carpetas

1. **docker-compose.yml**: Archivo para definir y ejecutar múltiples contenedores de Docker, incluyendo los microservicios y Keycloak.

2. **README.md**: Documentación del proyecto, incluyendo instrucciones de instalación y uso.

3. **microservicio1/** y **microservicio2/**: Cada microservicio tendrá su propia carpeta con la siguiente estructura:
   - **pom.xml**: Archivo de configuración de Maven.
   - **src/**: Contiene el código fuente y los recursos del microservicio.
     - **main/java/com/ejemplo/microservicioX/**: Código fuente del microservicio.
       - **MicroservicioXApplication.java**: Clase principal que inicia la aplicación.
       - **controller/**: Controladores REST.
       - **service/**: Lógica de negocio.
       - **repository/**: Interfaces para acceder a la base de datos.
       - **model/**: Clases de modelo (entidades).
     - **main/resources/**: Recursos de configuración.
       - **application.yml**: Configuración de Spring.
       - **db/schema.sql**: Script para crear la base de datos.
     - **test/**: Pruebas unitarias y de integración.

4. **Dockerfile**: Archivo para construir la imagen Docker del microservicio.

5. **keycloak/**: Carpeta para la configuración de Keycloak.
   - **docker-compose.yml**: Archivo para ejecutar Keycloak en un contenedor Docker.
   - **realm.json**: Archivo de configuración del realm de Keycloak.

### Siguientes Pasos

1. **Crear los archivos y carpetas**: Puedes crear la estructura de carpetas y archivos en tu entorno de desarrollo.

2. **Configurar Maven**: En cada `pom.xml`, asegúrate de incluir las dependencias necesarias para Spring, SQLite, Lombok, etc.

3. **Configurar Docker**: En los `Dockerfile` y `docker-compose.yml`, define cómo se construirán y ejecutarán los microservicios y Keycloak.

4. **Implementar la lógica de negocio**: Comienza a implementar los controladores, servicios y repositorios en cada microservicio.

5. **Configurar Keycloak**: Configura Keycloak para manejar la autenticación de usuarios.

Si necesitas ayuda con algún archivo específico o configuración, ¡no dudes en preguntar!