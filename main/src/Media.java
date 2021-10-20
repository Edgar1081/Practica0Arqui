package main.src;

import java.lang.Math;

/**
*Clase con métodos estáticos para calcular las
*medias de tendencia central.
*/
public class Media{
    
    /**
     * Regresa el valor de la media aritmetica
     * @param datos Arreglo con los valores.
     * @return double de la media aritmética.
     */
    public static double arit(double [] datos){
        double s = 0;
        for(double i : datos)
            s += i;
        double n = datos.length;
        return s/n;
    }

     /**
     * Regresa el valor de la media armónica.
     * @param datos Arreglo con los valores.
     * @return double de la media armónica.
     */
    public static double arm(double [] datos){
        double s = 0.0;
        for(double i : datos)
            s += 1.0/i;
  
        double n = (double) datos.length;
        return n/s;
    }

    /**
     * Regresa el valor de la media armónica.
     * @param datos Arreglo con los valores.
     * @return double de la media geométrica.
     */
    public static double geo(double [] datos){
        double p = 1.0;
        for(double i : datos){
            p *= i;
        }
        double n = (double) datos.length;
        double r = Math.pow(p, 1.0/n);   
        return r;
    }

    /**
     * Regresa el valor de la media armónica.
     * @param datos Arreglo con los valores.
     * @param pesos Pesos respectivos a cada valor.
     * @return double de la media ponderado.
     */
    public static double pond(double [] datos, double [] pesos){
        if(pesos == null)
            return 0;
        double d = 0;
        for(double w : pesos)
            d += w;
        double n = 0;
        for(int i = 0; i < datos.length; i++)
            n += datos[i] * pesos[i];
        return n/d;
    }
}