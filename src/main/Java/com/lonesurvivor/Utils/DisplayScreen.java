package com.lonesurvivor.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//NOTE: utility that reads txt files and displays them to the user
public class DisplayScreen {

    //converted to util so we dont need to create instance just call from anywhere to get the job done
    private DisplayScreen(){

    }

    //displays a screen from text file
    public static String showScreen(String filename) throws IOException{

        String msg = "";

        String fileName = "txt/" + filename;
        InputStream is = getFileFromResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                msg = line + "\n";
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    //private helper method that tells our jar file to get external files from resource stream
    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = DisplayScreen.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}

