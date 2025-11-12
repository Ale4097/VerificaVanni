package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws IOException{
        ServerSocket mioServerSocket = new ServerSocket(3000);
        Socket mioSocket = mioServerSocket.accept();
        try {
        BufferedReader in = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        PrintWriter out = new PrintWriter(mioSocket.getOutputStream(), true);
    
        int min = 1;
        int max = 100;
        int tries = 0;
        int segreto = ThreadLocalRandom.current().nextInt(min, max); // 1 incluso, 101 escluso

        out.println("WELCOME INDOVINA v1 RANGE " + min + " " + max);
        String line = in.readLine();
        String[] parts = line.split(" "); 
     
        boolean input = true;
        
        while (input == true) {
            
            if (parts[0].equals("GUESS") || parts[0].equals("RANGE") || parts[0].equals("STATS") || parts[0].equals("NEW") || parts[0].equals("QUIT")) {
               //comando GUESS
                if (parts[0].equals("GUESS")) {
                    if(parts.length > 1){
                        int n = Integer.parseInt(parts[1]);
                        tries++;
                        if (n >= min && n <= max) {
                            if (n == segreto) {
                                out.println("OK CORRECT in T=" + tries);
                                
                            }
                            if (n < segreto) {
                                out.println("HINT LOWER");
                                
                            }
                            if (n > segreto) {
                                out.println("HINT HIGHER");
                                
                            }
                        }else{
                            out.println("ERR OUTOFRANGE a b");
                            
                        }
                    }else{
                        out.println("ERR SYNTAX");
                        
                    }
                }

                //comando RANGE
                if (parts[0].equals("RANGE")){
                    if (parts.length > 2) {

                        int a = Integer.parseInt(parts[1]);
                        int b = Integer.parseInt(parts[2]);

                        if (a > b && tries == 0) {
                            min = a;
                            max = b;
                        }else{
                            out.println("ERR NOTALLOWED");
                            
                        }   
                    }
                }

                //comando STATS
                if (parts[0].equals("STATS")) {
                    out.println("OK INFO RANGE " + min + " " + max + "; " + " TRIES " + tries);
                    
                }

                //comando NEW
                if(parts[0].equals("NEW")){
                    tries = 0;
                    out.println("OK NEW");
                    out.println("WELCOME INDOVINA v1 RANGE " + min + " " + max);
                   
                }

                //comando QUIT
                if(parts[0].equals("QUIT")){
                    out.println("BYE");
                    mioServerSocket.close();
                    
                } 
                }else{
                    //Errore per comando inesistente
                    out.println("ERR UNKONOWNCMD");
                    
                }
                input = false;
        }
    }catch(
    Exception e)
    {
        // TODO: handle exception
    }

    }
    }
