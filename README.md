TPE PROGRAMACION 3

Facundo Sanchez - TUDAI Olavarria


#Correcciones de la parte 1

  El obtenerCaminos podía repetir vértices, no podía repetir arcos. Esto genera que no encuentre caminos válidos en los casos de prueba. 
  Estás visitando los arcos pero también estás limitando los caminos a no repetir vértices.
    #Solucionado
    
  cantidadArcos se podría beneficiar bastante de la utilización de un atributo de clase para llevar 
  registro de la cantidad de arcos sin necesidad de calcularlo cada vez (sugerencia).
    #Solucionado
