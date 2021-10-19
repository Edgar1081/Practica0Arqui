package main.src;

import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class Practica1{
    
    private static void procesa(String archivo, String [] banderas){
        ArrayList<String[]> ar = lee(archivo);
        if(ar == null)
            return;
        String [] nm = ar.get(0);
        String npcs = nm[0];
        String np = nm[1];
        String [] pesos = null;
        if(Arrays.asList(banderas).contains("-p"))
            pesos = ar.get(1);


        int pcs = Integer.valueOf(npcs);
        int pruebas = Integer.valueOf(np);

        double [] nmp = new double[pruebas];

        for(int i = 0; i < pcs; i++){
            nmp = Arrays.stream(ar.get(i+2)).mapToDouble(Double::parseDouble).toArray();
            
        }

    }




    private static String [] banderas(String args []){
        String c = "";
        for(String s : args)
            if(s.contains("-"))
                c += s + ",";
        return c.trim().split(",");
    }

    private static String [] archivos(String args []){
        String c = "";
        for(String s : args)
            if(!s.contains("-"))
                c += s + ",";
        return c.trim().split(",");
    }

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
            System.out.println("Archivo inválido, tonto");
        }
        return null;
    }

    public static void main(String args []){
        String [] banderas = banderas(args);
        String [] archivos = archivos(args);

        for(String ar : archivos){
            procesa(ar, banderas);
        }
    }

}