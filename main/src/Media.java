package main.src;

import java.lang.Math;

public class Media{
    
    public static double arit(double [] datos){
        double s = 0;
        for(double i : datos)
            s += i;
        double n = datos.length;
        return s/n;
    }

    public static double arm(double [] datos){
        double s = 0.0;
        for(double i : datos)
            s += 1.0/i;
  
        double n = (double) datos.length;
        return n/s;

    }

    public static double geo(double [] datos){
        double p = 1.0;
        for(double i : datos){
            p *= i;
        }
        double n = (double) datos.length;
        double r = Math.pow(p, 1.0/n);   
        return r;
    }

   

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