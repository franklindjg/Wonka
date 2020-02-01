# Wonka
Prueba técnica ​Napptilus​ ​Tech​ ​Labs

Aplicación que muestra una lista de items y detalle de un item seleccionado.

## Librerías utilizadas:
- gson para la serialización y deserialización de objectos a json y viceversa.
  
  He seleccionado esta librería porque se puede configurar con retrofit
  para las serialización del json que se obtiene de servidor a objectos (entidad|modelo), 
  olvidandome de tener que hacer el parseo de forma manualmente.

  https://github.com/google/gson
 
- retrofit cliente HTTP para la el consumo de una API REST
  
  Se pueden hacer peticiones con callback asincronas olvidandome de usar AsyncTask u otra clase para
  la getión de tareas en segundo plano.
  
  Es altamente configurable
  
  Para hacer llamadas al servidor es tan facil como crear una interface donde podemos definir
  todo los métodos posibles que se pueden hacer.
 
  https://square.github.io/retrofit/

  EndlessRecyclerViewScrollListener helper class para el manego de petición de datos a medida que el usuario se deslice 
  hacia abajo en la pantalla de la lista de items
  https://github.com/ardok/codepath/blob/master/TwitterClient/app/src/main/java/com/codepath/twitterclient/listeners/EndlessRecyclerViewScrollListener.java
