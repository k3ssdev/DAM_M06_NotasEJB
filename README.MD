# [DAM][M06][Actividad 6] - Aplicación web Java de gestión de notas con JPA y EJB

## Descripción
Esta actividad consiste en crear una aplicación para la gestión de notas de alumnos, utilizando JPA y EJB. La aplicación debe permitir realizar las siguientes operaciones:

- Validar el usuario y contraseña de un alumno.
- Mostrar las notas de un alumno.
- Validar el usuario y contraseña de un profesor.
- Mostrar las notas de todos los alumnos.
- Modificar la nota de un alumno.
- Modificar la contraseña de un alumno.
- Modificar la contraseña de un profesor.
- Añadir un alumno.
- Añadir un profesor.
- Eliminar un alumno.
- Eliminar un profesor.
- Añadir una asignatura.
- Eliminar una asignatura.
- Añadir una nota a un alumno.
- Eliminar una nota de un alumno.

## Requisitos
- Java 8
- Maven
- Glassfish 4.1.1
- NetBeans 16.0
- XAMPP 7.4.12 (MySQL 5.7.32)

## Instalación
1. Clonar el repositorio
```bash
git clone
```
2. Importar el proyecto en NetBeans
3. Crear la base de datos en MySQL usando el script `notaslinkia.sql`
4. Configurar el servidor Glassfish en NetBeans, añadiendo el conector JDBC de MySQL y el recurso JNDI `java:app/notaslinkia`
5. Desplegar la aplicación en Glassfish

*Se dispone de un documento con capturas de pantalla de los pasos anteriores en la carpeta `docs`.*

## Uso
La aplicación se puede usar desde la interfaz web o desde la API REST.

### Interfaz web
La interfaz web se encuentra en la ruta `http://localhost:8080/NotasEJB/`. Para acceder a ella, es necesario iniciar sesión con un usuario y contraseña de alumno o profesor. 


Si el usuario y la contraseña son correctos, la API devolverá un token de sesión. Este token debe enviarse en la cabecera `Authorization` de las peticiones HTTP a la API.

## Créditos
Alberto Pérez, 2023.


## Licencia

