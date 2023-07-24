# Manual Técnico

## Descripción generarl del sistema

El sistema CRUD desarrollado con Spring y Vue tiene como objetivo proporcionar una solución para el manejo de clientes y sus cuentas bancarias, específicamente para los tipos de cuentas "Ahorro" y "Monetarias", así como la administración de chequeras y cheques asociados a estas cuentas.

## Características principales del sistema:

- Gestión de Clientes:
        El sistema permite crear, leer, actualizar y eliminar (CRUD) información de los clientes. Cada cliente tiene atributos como nombre, dirección, número de teléfono, entre otros.

- Tipos de Cuentas Bancarias:
        Los clientes pueden tener cuentas de tipo "Ahorro" y "Monetarias". Estas cuentas tienen atributos distintivos, como tasas de interés, saldo actual, historial de transacciones, etc.

- Administración de Cuentas Bancarias:
        El sistema facilita la creación, consulta, actualización y eliminación de cuentas bancarias para cada cliente. Además, se puede realizar transferencias entre cuentas del mismo cliente o de diferentes clientes.

- Chequeras y Cheques:
        Para las cuentas de tipo "Monetarias", el sistema permite la asignación y seguimiento de chequeras. Cada chequera contiene una serie de cheques con información única, como número de cheque, fecha de emisión, beneficiario, monto, etc. Los cheques pueden ser emitidos, cobrados y cancelados.

- Interfaz de Usuario Amigable:
        La interfaz de usuario desarrollada con Vue ofrece una experiencia amigable y fluida para los usuarios. Se prioriza la usabilidad y la accesibilidad.

## Tecnologías Utilizadas

El desarrollo de este sistema CRUD combina tecnologías tanto en el lado del servidor como en el cliente para brindar una solución completa y eficiente. A continuación, se enumeran las principales tecnologías utilizadas:

### Backend (Spring):

1. Spring Framework: Plataforma de desarrollo de aplicaciones Java queproporciona soporte para la construcción de aplicaciones empresariales.
2. Spring Boot: Facilita la configuración y el despliegue rápido deaplicaciones basadas en Spring.
3. Spring Data JPA: Permite el acceso y manejo de la capa de persistenciaa través de JPA (Java Persistence API).
4. SQL Server: Sistema de gestión de bases de datos relacional utilizadopara almacenar la información de clientes, cuentas y cheques.

### Frontend (Vue):

1. Vue.js: Framework progresivo de JavaScript para construir interfaces de usuario interactivas y reactivas en el lado del cliente.
2. Vue Router: Permite la configuración y navegación de las rutas del frontend para habilitar la navegación dentro de la aplicación.
3. Axios: Biblioteca de JavaScript utilizada para realizar solicitudes HTTP desde el cliente al servidor backend.
4. Primevue: Frameworks CSS utilizado para mejorar el diseño y la apariencia del frontend.
5. HTML5 y CSS3: Lenguajes estándar para estructurar y diseñar la interfaz de usuario del sistema.
6. JavaScript (ES6+): Utilizado para agregar interactividad y funcionalidad adicional a la aplicación.

### Herramientas y Otras Tecnologías:

1. Maven o Gradle: Herramientas de gestión de proyectos utilizadas para la compilación, dependencias y construcción del proyecto.
2. Git: Sistema de control de versiones para gestionar el código fuente y el historial de cambios del proyecto.
3.Postman: Utilizado para probar y depurar los servicios RESTful durante el desarrollo.

## Backend

### Descripción de la estructura:

1. Controllers: En esta carpeta se encuentran los controladores que manejan las solicitudes HTTP y mapean los endpoints de la API. Cada controlador se encarga de recibir las peticiones y llamar a los servicios correspondientes para procesar las operaciones CRUD.

2. Entities: Aquí se almacenan las entidades JPA que representan las tablas de la base de datos. Cada entidad se anota con @Entity y contiene las definiciones de los campos y relaciones.

3. Repositories: Esta carpeta contiene las interfaces de los repositorios JPA que permiten interactuar con la base de datos. Cada repositorio se encarga de proporcionar métodos para realizar consultas y operaciones de CRUD en la tabla correspondiente.

4. Services: En esta carpeta se encuentran las clases de servicio que contienen la lógica de negocio de la aplicación. Cada servicio se encarga de gestionar las operaciones CRUD y aplicar reglas de negocio si es necesario.

5. Request: Aquí se guardan los modelos utilizados para recibir las peticiones HTTP en los controladores. Estos modelos se utilizan para mapear los datos enviados desde el frontend a objetos Java que puedan ser procesados por los controladores y servicios.


## Frontend 

### Descripción de la estructura:

1. src: Esta carpeta contiene todos los archivos relacionados con el código fuente del frontend.

2. assets: En esta carpeta se guardan recursos estáticos como hojas de estilo (CSS) e imágenes utilizadas en la aplicación.

3. components: Aquí se almacenan los componentes reutilizables que forman parte de la interfaz de usuario. Cada componente puede tener su propio archivo .vue que incluye la plantilla HTML, el código JavaScript y los estilos CSS asociados.

4. services: Esta carpeta contiene los servicios que se comunican con el backend a través de solicitudes HTTP utilizando Axios o Fetch. Cada servicio se encarga de consumir la API RESTful correspondiente para gestionar los datos.

5. views: Aquí se encuentran las vistas principales de la aplicación, que representan las diferentes páginas o secciones que los usuarios pueden navegar. Cada vista puede incluir componentes para mostrar contenido específico.

6. App.vue: El archivo App.vue es el componente principal de la aplicación. Contiene el diseño general y la estructura base que se carga en todas las páginas. Aquí se incluyen el encabezado, pie de página o navegación común a todas las vistas.

7. main.js: El archivo main.js es el punto de entrada del proyecto Vue. Aquí se configura Vue y se importa el componente App.vue para montarlo en el elemento HTML de la página principal.
