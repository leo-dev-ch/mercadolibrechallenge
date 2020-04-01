# MercadoLibre Challenge
### Documentacion
Aplicación desarrollada con Spring Boot y PostgreSQL.

La Aplicación Permite:

* Acceder  a una cuenta de correo electrónico de Gmail, mediante el protocolo IMAP.
* Leer y  Buscar todos los correos electrónicos que contengan en el asunto y/o cuerpo, la palabra; **DevOps**.
* Almacenar en una base de datos  los siguientes: campos: fecha, from, subject.

## Prerequisitos
Para ejecutar Ejecutar la aplicacion es necesario tener los siguientes requerimientos:

 * Docker 19.x.x 
 * Docker-compose V1.24.x
 * Git
 * Cuenta Gmail.(Se debe tener habilitado)
    * **Activar Acceso  IMAP** Documentacion: https://support.google.com/a/answer/105694?hl=es
    * **Verificacion de dos pasos** Documentacion: https://support.google.com/accounts/answer/185839
    * **Contraseña de aplicaciones** Documentacion:  https://support.google.com/mail/answer/185833?hl=es-419

## Descarga y Configuración del proyecto.
Descargar este proyecto como zip o clonar el proyecto. En caso de descargar como Zip extraer los archivos dentro de una carpeta.

Dentro de los archivos extraídos;
En el archivo `application.properties` (`emailApi/src/main/resources/application.properties`) se encuentran las configuraciones del proyecto y las configuraciones del correo electrónico por defecto. 
En este archivo modifique las propiedades del correo electrónico y reemplace con lo que corresponda

    mail.username = XXXXX@gmail.com
    mail.password = XXXXXXXX (contraseña de Aplicacion)
    
## Ejecución del proyecto
Para  desplegar el proyecto, se deben ejecutar desde la terminal o consola de windows los siguientes comandos

    $ cd proyecto
    $ docker-compose up -d

De ser necesario hay que generar el archivo .jar, para ello se debe ejecutar el siguiente comando, ubicado en la raiz del proyecto.
    
    $ mvn package -Dmaven.test.skip=true
    $ docker-compose up -d

## Trabajando
El proyecto expone 2 API REST, que permiten ejecutar las funcionalidades de los requerimientos. Las api se pueden consumir mediante CURL o utilizando  Swagger UI. 

Al Swagger se accede desde la siguiente url: http://localhost:8080/swagger-ui.html.

### Apis

`/api/mail/searchDevOps`
Permite acceder al correo electrónico por defecto, buscar entre los email, todos aquellos que contengan en el asunto y/o cuerpo, la palabra **DevOps**  y almacenarlos en la base de Datos. Finalmente retorna una lista con todos los correos que se recuperaron y almacenaron.

###### **Return**

    [
       {
         "date": "string",
         "from": "string",
         "id": 0,
         "subject": "string"
       }
     ]


`/api/mail/user` Permite acceder al correo electrónico por defecto, buscar entre los email, todos aquellos que contengan en el asunto y/o cuerpo, la palabra **DevOps**  y almacenarlos en la base de Datos. Finalmente retorna una lista con todos los correos que se recuperaron y almacenaron. A diferencia de la api anterior, es que en esta se solicita como parámetro el correo electrónico y la contraseña de aplicación.
 
###### **Parametros**
 
    {
        "password": "string",
        "username": "string"
     }

###### **Return**

    [
       {
         "date": "string",
         "from": "string",
         "id": 0,
         "subject": "string"
       }
     ]

## Base de Datos

Al desplegar el proyecto, se crean los contendores de PostgreSql y PgAdmin4.
En postgreSql se crea automáticamente la Base de Dato `mercadolibre_challenge` y la tabla `public.messages`.

Para acceder a la base de datos, puede hacerlo mediante PgAdmin4, a través de la siguiente URL http://localhost:81/browser/

###### Datos de Acceso
    Email= leo@leo.com
    Password= PgAdmin2020!

###### Agregar Server
    Object -> Create -> Server
    
    Name= Postgres
    host = dbpostgres
    Username= postgres
    Password= Postgres2020!
