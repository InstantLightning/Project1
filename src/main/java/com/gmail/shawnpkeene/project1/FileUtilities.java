/*
This class deals with all file io for the game
 */
package com.gmail.shawnpkeene.project1;

import java.io.*;

public class FileUtilities {

    private static File getDataFolder() {
        return Project1.instance().getDataFolder();
    }

    public static void saveToKillsFile(String UUID, String kills) throws IOException {

        File directory = getDataFolder();
        String temp;
        boolean foundKills = false;

        if (!directory.exists() && !directory.mkdirs()) {
            return;
        }

        File testFile = new File(directory, "Kills.txt");

        try {
            if (!testFile.exists() && !testFile.createNewFile()) {
                return;
            }
            try (FileWriter writer = new FileWriter(testFile)) {
                try (Reader reader = new FileReader(testFile);
                     BufferedReader bufferedReader = new BufferedReader(reader)) {
                    while (bufferedReader.ready() && !foundKills && bufferedReader != null) {
                        temp = bufferedReader.readLine();

                        if (temp.startsWith(UUID)) {
                            bufferedReader.readLine().replaceAll(temp, UUID + " " + kills);
                            foundKills = true;
                        }
                        if (bufferedReader == null) {
                            writer.append(UUID + " " + kills);
                            writer.flush();
                        }
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
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

        try (Reader reader = new FileReader(testFile);
            BufferedReader bufferedReader = new BufferedReader(reader)) {
            while (bufferedReader.ready() && !gotKills && bufferedReader != null) {
               temp = bufferedReader.readLine();

               if (temp.startsWith(key)) {
                   kills = Integer.parseInt(temp.substring(temp.indexOf(" ") + 1));
                   gotKills = true;
               }

               if (bufferedReader == null) {
                   kills = 0;
               }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return kills;
    }

    public static void removeLine(String line, File file) {

    }
}
