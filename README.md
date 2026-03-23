# springEduManager 🎓

**springEduManager** es una aplicación robusta desarrollada con **Spring Boot** diseñada para la gestión integral de entornos educativos. Permite administrar de manera eficiente estudiantes, cursos, profesores y calificaciones, ofreciendo una API REST clara y escalable.

---

## 🚀 Características Principales

*   **Gestión de Usuarios:** Registro y administración de alumnos, profesores y personal administrativo.
*   **Control Académico:** Creación y seguimiento de cursos, matrículas y horarios.
*   **Calificaciones:** Sistema dinámico para el registro de notas y reportes de rendimiento.
*   **Seguridad:** Implementación de autenticación y autorización (opcionalmente con Spring Security).
*   **API RESTful:** Endpoints documentados para facilitar la integración con frontends modernos.

## 🛠️ Tecnologías Utilizadas

*   **Backend:** [Java](https://oracle.com) / [Spring Boot](https://spring.io)
*   **Persistencia:** Spring Data JPA / Hibernate
*   **Base de Datos:** MariaDB (Gestionada con HeidiSQL)
*   **Gestión de Dependencias:** Maven o Gradle
*   **Documentación API:** [Swagger / OpenAPI](https://swagger.io) (recomendado)

## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:
- **JDK 21** o superior.
- **Maven** 3.6+.
- Una base de datos configurada (o usar H2 por defecto).

## ⚙️ Instalación y Configuración

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com
    cd springedumanager
    ```

2.  **Configura el entorno:**
    Edita el archivo `src/main/resources/application.properties` con tus credenciales de base de datos.

3.  **Compila el proyecto:**
    ```bash
    mvn clean install
    ```

4.  **Ejecuta la aplicación:**
    ```bash
    mvn spring-boot:run
    ```

## 📂 Estructura del Proyecto

```text
src/main/java/com/tu/paquete/
├── controller/    # Endpoints de la API
├── model/         # Entidades de base de datos
├── repository/    # Interfaces de acceso a datos
├── service/       # Lógica de negocio
└── config/        # Configuraciones de seguridad/app
 ```
 
## 🤝 Contribuciones
¡Las contribuciones son bienvenidas!
Haz un Fork del proyecto.
Crea una Rama para tu función (git checkout -b feature/NuevaFuncionalidad).
Haz un Commit de tus cambios (git commit -m 'Añade nueva funcionalidad').
Haz un Push a la rama (git push origin feature/NuevaFuncionalidad).
Abre un Pull Request.
Desarrollado por Daniel Fuentes
```
## 🗄️ Estructura de Base de Datos (SQL)
-- 1. Crear y usar la base de datos
CREATE DATABASE IF NOT EXISTS springedumanager;
USE springedumanager;

-- 2. Estructura de la tabla 'cursos'
CREATE TABLE IF NOT EXISTS cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    activo TINYINT(1) DEFAULT 1
);

-- 3. Estructura de la tabla 'estudiantes'
CREATE TABLE IF NOT EXISTS estudiantes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    activo TINYINT(1) DEFAULT 1,
    UNIQUE (email)
);

-- 4. Estructura de la tabla de unión 'estudiante_curso'
CREATE TABLE IF NOT EXISTS estudiante_curso (
    estudiante_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    PRIMARY KEY (estudiante_id, curso_id),
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE CASCADE
);

-- 5. Carga de Datos de Prueba (Inserts)
INSERT INTO cursos (nombre, descripcion, activo) VALUES 
('Programación Java', 'Curso básico de Java con Spring Boot', 1),
('Bases de Datos SQL', 'Fundamentos de MySQL y MariaDB', 1),
('Desarrollo Frontend', 'HTML, CSS y JavaScript moderno', 1);

INSERT INTO estudiantes (nombre, email, activo) VALUES 
('Juan Pérez', 'juan.perez@example.com', 1),
('María García', 'm.garcia@example.com', 1),
('Carlos López', 'carlos.lopez@example.com', 0),
('Ana Martínez', 'ana.mtz@example.com', 1);

-- Inscripciones de prueba (Relacionando estudiantes con cursos)
INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES 
(1, 1), (1, 2), -- Juan en Java y SQL
(2, 1),         -- María en Java
(4, 3);         -- Ana en Frontend

