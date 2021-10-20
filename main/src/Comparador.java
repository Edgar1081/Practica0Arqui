package main.src;

public class Comparador{
    
    /**
     * Regresa una cadena con el resultado de comparar las medias
     * de las computadoras y qué media fue solicitada.
     * @param pcs Arreglo con los valores de las medias ya
     * calculadas de cada computadora.
     * @param bandera Bandera que decide si se evalúa a tiempo
     * o rendimiento.
     * @param caso media solicitada.
     * las computadoras.
     * @return double de la media aritmética.
     */
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
    /**
     * Regresa una cadena con el resultado de comparar
     * los valores de las medias dadas en rendimiento,
     * @param pcs Arreglo con los valores a comparar.
     * @return String cadena con el valor más grande.
     */
    private static String max(double [] pcs){
        double max = 0;
        int m = 0;
        int i = 0;
        for(double pc : pcs){
            if(pc > max){
                max = pc;
                m = i;
            }
            i++;
        }
        return "PC" + m + ": " + max;
    }

    /**
     * Regresa una cadena con el resultado de comparar
     * los valores de las medias dadas en tiempo,
     * @param pcs Arreglo con los valores a comparar.
     * @return String cadena con el valor más pequeño.
     */
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