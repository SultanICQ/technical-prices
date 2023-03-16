# Price Selection

Se programa un endpoint que dé respuesta al enunciado del problema planteado.

Se ha desarrollado todo el código manteniendo las capas de aplicación y dominio libres de ruido.

En ninguna de estas capas aparecen por ejemplo las anotaciones de base de datos o de spring boot. No estamos ligados a
nada más que al propio código.

Se ha relegado todo este ruido a la capa de infraestructura.

## Lanzar el proyecto desde consola

Antes que nada confirmar que estamos ejecutando la versión 17 de java como mínimo.

``java -version``

Confirmado este punto, ejecutamos:

``./gradlew bootRun``

Y ya podremos acceder a localhost sobre el puerto 8080.

### Ejemplo para lanzar una petición

```
curl --location 'http://localhost:8080/price_at_date?brand_id=1&product_id=35455&date=2020-06-16-13.00.00'
```

He mantenido el formato de fecha presente en los datos, sin espacios y con puntos simples para separar el time y, así evitamos problemas de querystring encode.


## Lanzar la batería de tests unitarios y de aceptación

``./gradlew test``

# Enunciado del problema


En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

PRICES
-------

| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
| -------- | ------------------- | ------------------- | ---------- | ---------- | -------- | ----- |------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  |

Campos:

BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).

START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.

PRICE_LIST: Identificador de la tarifa de precios aplicable.

PRODUCT_ID: Identificador código de producto.

PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).

PRICE: precio final de venta.

CURR: iso de la moneda.

Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).

Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:

-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)


Se valorará:

Diseño y construcción del servicio.

Calidad de Código.

Resultados correctos en los test.