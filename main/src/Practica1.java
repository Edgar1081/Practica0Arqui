package main.src;

import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class Practica1{
    
    private static void procesa(String a, String [] b){

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

    private static String [][] lee(String archivo){
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
        }catch(IOException ioe){
            System.out.println("Archivo inválido, tonto");
        }
    }

    public static void main(String args []){
        String [] banderas = banderas(args);
        String [] archivos = archivos(args);

        for(String ar : archivos){
            procesa(ar, banderas);
        }
    }

}