/*
This class deals with all file io for the game
 */
package com.gmail.shawnpkeene.project1;

import java.io.*;

public class FileUtilities {

    private static File getDataFolder() {
        return Project1.instance().getDataFolder();
    }

    public static void saveToKillsFile(String kills) {

        File directory = getDataFolder();

        if (!directory.exists() && !directory.mkdirs()) {
            return;
        }

        File testFile = new File(directory, "Kills.txt");

        try {
            if (!testFile.exists() && !testFile.createNewFile()) {
                return;
            }
            try(FileWriter writer = new FileWriter(testFile)) {
                writer.append(kills);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int searchFileForKills(String key) throws IOException, NumberFormatException {

        File directory = getDataFolder();
        String temp;
        int kills = 0;
        boolean gotKills = false;

        if (!directory.exists()) {
            throw new FileNotFoundException("Plugin directory does not exist!");
        }
        File testFile = new File(directory, "Kills.txt");
        if (!testFile.exists()) {
            throw new FileNotFoundException("File does not exist");
        }

        try(Reader reader = new FileReader(testFile);
            BufferedReader bufferedReader = new BufferedReader(reader)) {
            while (bufferedReader.ready() && !gotKills) {
               temp = bufferedReader.readLine();

               if (temp.startsWith(key)) {
                   kills = Integer.parseInt(temp.substring(temp.indexOf(" ") + 1));
                   gotKills = true;
               }
            }
        }

        return kills;
    }
}
