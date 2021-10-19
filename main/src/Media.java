package main.src;

import java.lang.Math;

public class Media{
    
    public static double arit(double [] datos){
        double s = 0;
        for(double i : datos)
            s += i;
        return s/datos.length;
    }

    public static double arm(double [] datos){
        double s = 0;
        for(double i : datos)
            s += 1/i;
        return datos.length/s;
    }

    public static double geo(double [] datos){
        double p = 1;
        for(double i : datos)
            p *= i;
        return Math.pow(p, 1/datos.length);   
    }

   

    public static double pond(double [] datos, double [] pesos){
        double d = 0;
        for(double w : pesos)
            d += w;
        double n = 0;
        for(int i = 0; i < datos.length; i++)
            n += datos[i] * pesos[i];
        return n/d;
    }
}