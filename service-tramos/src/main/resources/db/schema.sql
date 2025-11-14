mi-proyecto/
│
├── microservicio-1/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── ejemplo/
│   │   │   │           └── microservicio1/
│   │   │   │               ├── controller/
│   │   │   │               ├── service/
│   │   │   │               ├── repository/
│   │   │   │               ├── model/
│   │   │   │               └── Microservicio1Application.java
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── db/
│   │   │           └── schema.sql
│   │   └── test/
│   │       └── java/
│   │           └── com/
│   │               └── ejemplo/
│   │                   └── microservicio1/
│   ├── pom.xml
│   └── Dockerfile
│
├── microservicio-2/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── ejemplo/
│   │   │   │           └── microservicio2/
│   │   │   │               ├── controller/
│   │   │   │               ├── service/
│   │   │   │               ├── repository/
│   │   │   │               ├── model/
│   │   │   │               └── Microservicio2Application.java
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── db/
│   │   │           └── schema.sql
│   │   └── test/
│   │       └── java/
│   │           └── com/
│   │               └── ejemplo/
│   │                   └── microservicio2/
│   ├── pom.xml
│   └── Dockerfile
│
├── docker-compose.yml
├── keycloak/
│   ├── Dockerfile
│   └── realm.json
└── README.md