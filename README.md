# Gestion reservas de un restaurante
Es un sistema que permite la gestión de reservaciones para un restaurante.

## BBDD

Para poder ejecutar la Base de Datos, es obligatorio tener instalado MySQL en la máquina. 
Link de descarga: https://dev.mysql.com/downloads/installer/

## Herramientas de desarrollo
- Java Development Kit(JDK) 11
- En este desarrollo se utilizo la herramienta DBeaver para la gestión de la Base de Datos.
Link de descarga: https://dbeaver.io/download/ 
- Entonro de desarrollo integrado(IDE) IntelliJ IDEA https://www.jetbrains.com/es-es/idea/

### Otras herramientas
* Postman - Peticiones HTTP https://www.postman.com/


##### Pasos para la ejecución:
1. Se debe abrir MySQL 8.0 Command Line Client y loguearse con la contraseña de MySQL que haya implementado
en la instalación.
2. Create la BBDD con la Query CREATE DATABASE RISERVI;
3. En la herramienta de gestión de Bases de Datos, se realiza la conexión a la nueva Base de Datos, con las credenciales de acceso. (jdbc:mysql://localhost:3306/riservi, username y password)
4. Seguidamente, se ejecuta el Script que va adjunto en la entrega, con la creación de tablas e inserción de Datos.
5. Se descarga el codigo fuente del repositorio de git, se importa en el IDE de su preferencia.
6. Se configuran las credenciales de la BBDD en el archivo application.properties
7. Se compila y se ejecuta.
8. Para las pruebas, la URL base local es http://localhost:8080/riservi
9. En la entrega va un archivo postman de pruebas, solo es descargarlo e importarlo en la herrmienta Postman, tener en cuenta los datos que les pasen por parametro basados en los de la bbdd que crearon.
10. Para acceder al front, el link es http://localhost:8080/index.html, dan click en Reservas y los lleva a la tabla de Reservas y sus diferentes acciones a realizar.



### Detalles de cualquier complicación o decisión importante tomada durante el desarrollo.
La parte de actualizar la reserva, no alcance a hacerla debido a un incoveniente con la transaccionalidad a la BBDD, me falto un poco más de tiempo para haberlo terminado. Crear todo desde cero requiere un buen análisis de los requisitos para que el diseño y la implementación de la solución cumplan con la petición del cliente.
