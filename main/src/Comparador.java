package main.src;

public class Comparador{

    public static String compara(double [] pcs, String bandera){
        if(bandera.equals("-r"))
            return max(pcs);
        else
            return min(pcs);
    }

    private static String max(double [] pcs){
        double max = 0;
        int i = 0;
        for(double pc : pcs){
            if(pc > max){
                max = pc;
                i++;
            }
        }
        return "PC" + i + ": " + max;
    }

    private static String min(double [] pcs){
        double min = pcs[0];
        int i = 0;
        for(double pc : pcs){
            if(pc < min){
                min = pc;
                i++;
            }
        }
        return "PC" + i + ": " + min;
    }
}