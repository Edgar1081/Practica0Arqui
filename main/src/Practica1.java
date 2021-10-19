package main.src;

import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.SysexMessage;

import main.src.Comparador;

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

        if(Arrays.asList(banderas).contains("-r") && Arrays.asList(banderas).contains("-t")){ 
            System.out.print("Banderas invalidas");
            System.exit(0);
        }
        for(String ar : archivos)
            procesa(ar, banderas);
    }

}