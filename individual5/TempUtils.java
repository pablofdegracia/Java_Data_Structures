package aed.individual5;

import es.upm.aedlib.Pair; 
import es.upm.aedlib.map.*;


public class TempUtils {
  
	
  public static Map<String,Integer> maxTemperatures(long startTime,
                                                    long endTime,
                                                    TempData[] tempData) {
	
	  Map<String, Integer> result= new HashTableMap<String, Integer>(tempData.length);

	for(int i=0; i< tempData.length; i++) {
		//comprobamos que el dato se ha medido dentro del limite
		if(tempData[i].getTime()>= startTime&&tempData[i].getTime()<= endTime) { 
			int tMax= tempData[i].getTemperature(); String ciudad= tempData[i].getLocation();
			if(result.containsKey(ciudad)) {
				if(result.get(ciudad) < tMax) //compara el valor actual de la clave
					result.put(ciudad, tMax);
				}
			else result.put(ciudad, tMax); 
			//si no está la ciudad la mete con la primera temperatura valida
			
			}
	}
    return result;
  }
  
  public static Pair<String,Integer> maxTemperatureInComunidad(long startTime,
                                                               long endTime,
                                                               String region,
                                                               TempData[] tempData,
                                                               Map<String,String> comunidadMap) {
	  
	  if (tempData.length == 0 || tempData == null)
			return null;
	  Pair<String, Integer> res = new Pair<String, Integer>("a", 0);
		String maxLeft = "no hay maximo"; int maxRight = -1;
		
		for (int i = 0; i < tempData.length; i++) {
			//comprobamos que el dato se ha medido dentro del limite
			if (tempData[i].getTime() <= endTime && tempData[i].getTime() >= startTime) {
			int tMax= tempData[i].getTemperature(); String ciudad= tempData[i].getLocation();
				//Comprobamos que las ciudades se encuentran en la comunidad especificada por
				//comunidadMap
				if (region.equals(comunidadMap.get(ciudad))) {
					//Sabiendo que tMax y ciudad son legales, busca recursivamente un maximo y minimo
					if(maxRight<tMax) {
						maxLeft = ciudad;
						maxRight = tMax;
						}
					}
				}
			}
		//coloco el par de maxima temperatura
		res.setRight(maxRight);
		res.setLeft(maxLeft);
		if (res.getLeft() == "no hay maximo") // devuelve null si no hay maximo
			return null;
		return res;
  }

}
