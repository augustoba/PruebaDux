
# Api para gestion de equipos de futbol

Con esta api se pueden crear equipos, buscar, editar, eliminar y listarlos.



## Documentation

## Estructura de la Aplicación

La aplicación consta de cuatro entidades principales:

1. Equipo
2. Liga
3. País
4. Usuario

Estas entidades están diseñadas para representar distintos aspectos de la información dentro del sistema.

### Entidades

#### Equipo

- Contiene el id(autoincremental), nombre del equipo y posee relacion con la liga (relacion muchos a uno) y con país(relacion muchos a uno).

#### Liga

- Contiene id(autoincremental) y nombre de la liga.

#### País

- Contiene el id id(autoincremental) y el nombre del país. 

#### Usuario

- Representa un usuario del sistema.
- Contiene información sobre el nombre de usuario, contraseña y rol.


### Relaciones entre las Entidades

Las entidades están relacionadas de la siguiente manera:

- La entidad Equipo está relacionada con las entidades Liga y País para evitar inconsistencias y repeticiones en la base de datos. Esto permite que las ligas y los países sean parametrizables.

## DTOs (Data Transfer Objects)

Además de las entidades principales, la aplicación utiliza DTOs para transferir datos entre las capas de la aplicación. Estos incluyen:

- EquipoRequestDTO: Utilizado para recibir datos al crear o actualizar un equipo, tambien posee la validacion de los atributos con @valid.
- EquipoResponseDTO: Utilizado para enviar datos de un equipo como respuesta a las solicitudes.

Estos DTOs facilitan la transferencia de datos y ayudan a mantener una separación clara entre las capas de la aplicación.

Ademas trabaje con mapper para el mapeo de las entidades a request y response.

Cuenta con un script para cargar los equipos automaticamente apenas la aplicacion se ejecuta, el mismo se encuentra en la carpeta resource

Tambien cuenta con un Dockerfile que se encuentra en la carpeta src.

Se documentaron los endpoints con swagger 

http://localhost:8080/doc-swagger-ui.html


## SOLICITUDES

#### Buscar un equipo por ID.


```http
  GET /equipos/${id}
  
```

| Parámetro	 | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| 'id' | 'integer' | **Requerido. ID del equipo a buscar** |                                                                                 
              
```http
{
  "id": 1,
  "nombre": "Nuevo Nombre",  
  "pais": "Nuevo País",  
  "liga": "Nueva Liga"  
}
```

#### Actualizar equipo.

```http
   PUT /equipos/${id}
```

| Parámetro	 | Tipo     | Descripción                |
| :-------- | :------- | :-------------------------------- |
| `id`      | `integer	` | **Requerido. ID del equipo a actualizar** |

#### Request:
```http
{
  "nombre": "Nuevo Nombre", (minimo 4 caracteres, maximo 50)   
  "pais": "Nuevo País",(minimo 4 caracteres, maximo 50)  
  "liga": "Nueva Liga"(minimo 4 caracteres, maximo 50)   
}
```
#### Reponse:

```http
{
  "id": 1,
  "nombre": "Nuevo Nombre",  
  "pais": "Nuevo País",  
  "liga": "Nueva Liga"  
}
```
#### Listar todos los equipos..

```http
   GET /equipos
```

#### Reponse:
```http
[
  {
    "id": 1,             
    "nombre": "Real Madrid",         
    "pais": "España",       
    "liga": "La Liga"     
  },     
  {
    "id": 2,            
    "nombre": "FC Barcelona",         
    "pais": "España",          
    "liga": "La Liga"         
  },        
  {
    "id": 3,           
    "nombre": "Manchester United",          
    "pais": "Inglaterra",         
    "liga": "Premier League"        
  }     
]
```


#### Buscar equipos por nombre.

```http
        GET /equipos/buscar?nombre=${nombre}
```

| Parámetro	 | Tipo     | Descripción                |
| :-------- | :------- | :-------------------------------- |
| `id`      | `integer	` | **Requerido. ID del equipo a actualizar** |

#### Request:
```http
{
  "nombre": "Nuevo Nombre", (minimo 4 caracteres, maximo 50)   
  "pais": "Nuevo País",(minimo 4 caracteres, maximo 50)  
  "liga": "Nueva Liga"(minimo 4 caracteres, maximo 50)   
}
```
#### Reponse:
```http
{
  "id": 1,
  "nombre": "Nuevo Nombre",  
  "pais": "Nuevo País",  
  "liga": "Nueva Liga"  
}
```

#### Eliminar equipo.

```http
        DELETE /equipos/${id}
```

| Parámetro	 | Tipo     | Descripción                |
| :-------- | :------- | :-------------------------------- |
| `id`      | `integer	` | **Requerido. ID del equipo a eliminar** |

#### Reponse:
```http
{
}
```
## Tech Stack

**Java 17**

**Spring 3.3**

**Swagger**

**Jwt**

**Junit**

**Mockito**

**DB H2**




## Autor
Cesar Augusto Basaury Marquesto

- [@augustoba](https://github.com/augustoba)
- basauryaugusto@gmail.com
