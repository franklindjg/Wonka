# Wonka

Aplicación con pantalla lista y pantalla detalle

## Librerías utilizadas:
- ###### gson:
  Serialización y deserialización de objectos a json y viceversa.
  
  He seleccionado esta librería porque se puede configurar con retrofit
  para las serialización de json a objectos (entidad|modelo), 
  olvidandome de tener que hacer el mapping de datos.

  https://github.com/google/gson
 
- ###### retrofit:
  Cliente HTTP para el consumo de una API REST.
  
  Se pueden hacer peticiones con callback asincronos, olvidandome de usar AsyncTask u otra clase para
  la getión de tareas en segundo plano.
  
  Es altamente configurable.
  
  Para hacer llamadas al servidor es tan fácil como crear una interface donde podemos definir
  todo los métodos posibles que se pueden hacer.
 
  https://square.github.io/retrofit/

- ###### EndlessRecyclerViewScrollListener:
  Helper class para la paginación de la lista de ítems, a medida que se desliza hacia abajo.
  
  [EndlessRecyclerViewScrollListener]( https://github.com/ardok/codepath/blob/master/TwitterClient/app/src/main/java/com/codepath/twitterclient/listeners/EndlessRecyclerViewScrollListener.java)

