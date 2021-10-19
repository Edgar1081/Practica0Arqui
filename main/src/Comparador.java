package main.src;

public class Comparador{

    public static String compara(double [] pcs, String bandera, String caso){
        String c;
        switch(caso){
            case "-a": c = " Media aritmética";  
            break;
            case "-m": c = " Media armónica"; 
            break;
            case "-g": c = " Media geometrica";
            break;
            case "-p": c = " Media ponderada";
            break;
            default : c = "";
        }
        if(bandera.equals("-r"))
            return "Rendimineto: " + max(pcs) + c;
        else
            return "Tiempo: " + min(pcs) + c;
    }

    private static String max(double [] pcs){
        double max = 0;
        int i = 0;
        for(double pc : pcs){
            if(pc > max)
                max = pc;
            i++;
        }
        return "PC" + i + ": " + max;
    }

    private static String min(double [] pcs){
        double min = pcs[0];
        int m = 0;
        int i = 0;
        for(double pc : pcs){
            if(pc < min){
                min = pc;
                m = i;
            }
            i++;
        }
        return "PC" + m + ": " + min;
    }
}