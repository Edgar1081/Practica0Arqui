package main.src;
import java.util.HashMap;
import java.util.Map;
import javax.sound.midi.SysexMessage;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Practica1{
    
    /**
     * Procesa cada archivo recibido
     * @param archivo Ruta del archivo a leer.
     * @param banderas Arreglo con las banderas de entrada.
    */
    private static void procesa(String archivo, String [] banderas){
        ArrayList<String[]> ar = lee(archivo);
        if(ar == null)
            return;

        String [] nm = ar.get(0);
        int npcs = Integer.valueOf(nm[0]);
        int np = Integer.valueOf(nm[1]);

        String [] pesos = null;

        int i = 0;
        if(Arrays.asList(banderas).contains("-p")){
            i = 1;
            pesos = ar.get(1);
        }

        Map<String, Double> medias = new HashMap<>();

        String tr = "";

        for(int x = 0; x < npcs; x++){
            for(String b : banderas){
                if(b.equals("-t")){
                    tr = "-t";
                    continue;
                }
                if(b.equals("-r")){
                    tr = "-r";
                    continue;
                }
                medias.put(x + ":" + b, evalua(b, ar.get(x+i+1), pesos));
            }
        }
        maneja(tr, medias, npcs, banderas);
    }

    /**
     * Maneja los casos cuando se ingresaron valores de tiempo
     * o de rendimiento. Aplica el cálculo de cada media recibida
     * en las banderas e imprime el resultado de la comparación
     * dada por -t o -r
     * @param b bandera -t p -r.
     * @param medias diccionario con todas medias solicitadas
     * en las banderas por cada una de las computadoras recibidas.
     * @param n número de computadoras de la entrada.
     * @param banderas Arreglo con las banderas de la entrada.
    */
    private static void maneja(String b, Map<String, Double> medias, int n, String [] banderas){
        double [] v = new double [n];

        for(String c : banderas){
            if(c.equals("-t") || c.equals("-r"))
                continue;
            for(int i = 0; i < n; i++)
                v[i] = medias.get(i + ":" + c);
            String resultado = Comparador.compara(v, b, c);
            System.out.println(resultado);
        }
    }

    /**
     * Calcula la media dada por la bandera b, usando
     * el arreglo de datos y los pesos en caso de ser
     * necesario.
     * @param b bandera con la media solicitada.
     * @param valores Arrglo con los valores de una
     * computadora.
     * @param pesos Arreglo con los pesos para
     * calcular la media ponderada en caso de que
     * se requiera.
     * @return double con el calculo de la media que pide la bandera.
    */
    private static double evalua(String b, String [] valores, String [] pesos){
        double [] v = Arrays.stream(valores).mapToDouble(Double::parseDouble).toArray();
        double [] w = null;
        if(pesos != null)
            w = Arrays.stream(pesos).mapToDouble(Double::parseDouble).toArray();

        switch(b){
            case "-a": return Media.arit(v);   
            case "-m": return Media.arm(v);
            case "-g": return Media.geo(v);
            case "-p": return Media.pond(v, w);
            default : return 0;
        }
    }

    /**
     * Regresa un arreglo unicamente con las banderas
     * @param args Arreglo con los argumentos de main
     * @return String [] Arreglo de banderas
     */
    private static String [] banderas(String args []){
        String c = "";
        for(String s : args)
            if(s.contains("-"))
                c += s + ",";
        return c.trim().split(",");
    }

    /**
     * Regresa las rutas de los archivos en un
     * arreglo.
     * @param args Arreglo con los argumentos de main
     * @return double de la media aritmética.
     */
    private static String [] archivos(String args []){
        String c = "";
        for(String s : args)
            if(!s.contains("-"))
                c += s + ",";
        return c.trim().split(",");
    }

    /**
     * Regresa el valor de la media aritmetica
     * @param archivo ruta del archivo a leer.
     * @return ArrayList<String[]> Lista con arreglos
     * correspondientes a cada linea del archivo.
    */
    private static ArrayList<String[]> lee(String archivo){
        try{
            FileInputStream fileIn = new FileInputStream(archivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            String line = "";
            String [] r;
            ArrayList<String[]> l = new ArrayList<>();
            while((line = in.readLine()) != null){
                r = line.split(" ");
                l.add(r);
            }
            in.close();
            return l;
        }catch(IOException ioe){
            System.out.println("Archivo inválido");
        }
        return null;
    }

    private static void uso(){
        System.out.println("Uso: [-a | -m | -g | -p] [-t | -r] [files]");
        System.exit(0);
    }

    public static void main(String args []){
        String [] banderas = banderas(args);
        String [] archivos = archivos(args);

        if(args.length < 2)
            uso();

        if(Arrays.asList(banderas).contains("-r") && Arrays.asList(banderas).contains("-t")){ 
            System.out.print("Banderas invalidas");
            System.exit(0);
        }
        for(String ar : archivos)
            procesa(ar, banderas);
    }

}