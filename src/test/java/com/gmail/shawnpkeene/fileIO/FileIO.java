package com.gmail.shawnpkeene.fileIO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class fileio {
    public void main(String[] args) {
        File file = new File("test.txt");
        try {
            PrintWriter output = new PrintWriter(file);
            output.println("Shawn Keene");
            output.println(20);
            output.close();
        } catch(IOException ex) {
            System.out.printf("Error: %s\n", ex);
        }


    }
}