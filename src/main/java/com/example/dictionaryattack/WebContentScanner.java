package com.example.dictionaryattack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
* @author Samarth Narula
* 
* Important Note:- 
* I have created this code only for education purpose,
* I will not be responsible if this code will be used for any hacking purpose by anyone
*/
// Example Input: https://www.kali.org/

public class WebContentScanner {

    public static void main(String[] args) throws FileNotFoundException {
        String url = null;
        HttpURLConnection con = null;
        String wordlist = "dictionary.txt";
        //IMPLEMENT FOR LOOP TO ITERATE OVER DIRBLIST
        String exty = "";

        try ( BufferedReader br = new BufferedReader(new FileReader(wordlist))) {
            System.out.println("[*] Wordlist read. Starting the Web Content Scanner process... [*]\n");
            String line;
            
            try {
                url = JOptionPane.showInputDialog("Enter the host you want to scan - eg. https://www.kali.org/) :");
            } catch (Exception ex) {
                System.out.println("Could not set the URL variable");
            }
            
            
            while ((line = br.readLine()) != null) {

            	line = line.replace("\n", "");
                exty = line;
                System.out.println(exty);

                try {
                    url = url + exty;
                    URL urly = new URL(url);
                    con = (HttpURLConnection) urly.openConnection();
                } catch (IOException ex) {
                    System.out.println("[-]" + ex);
                }

                try {
                    con.setRequestMethod("GET");
                    // Removing the appended value in url for the next line to be appended from dictionary, just some basic logic
                   
                } catch (ProtocolException ex) {
                    System.out.println("Cant Set method (GET) - " + ex);
                }
                
                try {
                    if (con.getResponseCode() == 200) {
                        System.out.println("Found 200 on EXT : " + exty);
                        url = url.replace(exty, "");
                        //System.out.println(con.getResponseMessage());
                    } else {
                        System.out.println("Found " + con.getResponseCode() + " on EXT : " + exty);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(WebContentScanner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(WebContentScanner.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}