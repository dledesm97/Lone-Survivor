package com.lonesurvivor;

import com.lonesurvivor.Views.MasterGui;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {

            MasterGui masterGui = new MasterGui();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ParseException pe) {
            pe.printStackTrace();
            pe.getCause();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            System.out.println(ie.getMessage());
        }
    }
}
