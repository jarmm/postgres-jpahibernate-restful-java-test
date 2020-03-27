# Restuful test

## Getting Started

```
Ingresar a terminal : sudo su - postgres
acceder a plsql y ejecutar lo siguiente:
CREATE DATABASE costumers;
CREATE USER myuser WITH ENCRYPTED PASSWORD 'mypass';
GRANT ALL PRIVILEGES ON DATABASE costumers TO myuser;
```

### Run

```
Correr el aplicativo con servidor embebido, click derecho a SpringbootTestApplication y Run as Java Application.
```

## EndPoints

* (http://localhost:8080/api/creditos) - Obtiene todos los créditos
* (http://localhost:8080/api/creditos/?) - Obtiene un crédito por folio
* (http://localhost:8080/api/credito) - Inserta un crédito cliente
	```
	{
    	"rut": "17994335-2",
    	"montoCredito": "300000",
    	"cuotas": "40"
	}	
	```
* (http://localhost:8080/api/credito/?) - Actualiza un crédito cliente por folio
	```
	{
    	"rut": "17994335-2",
    	"montoCredito": "300000",
    	"cuotas": "10"
	}	
	```
* (http://localhost:8080/api/credito/?) - Elimina un crédito pro folio

## Authors

* **Jorge Martínez** - *Initial work* - 



