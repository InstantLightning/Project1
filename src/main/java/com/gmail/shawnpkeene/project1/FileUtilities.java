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
            try (FileWriter writer = new FileWriter(testFile, true);
                 Reader reader = new FileReader(testFile);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                StringBuffer bufferString = new StringBuffer();
                //Reads the file into a buffer string
                while (bufferedReader.ready()) {
                    bufferString.append(bufferedReader.readLine());
                }

                temp = bufferString.toString();
                int index = temp.indexOf(UUID);

                if (index > -1) {
                    //Brings index to the end of the UUID plus the space
                    index += UUID.length() + 1;
                    int i = 0;
                    //Sets the
                    while (index < bufferString.length() && bufferString.charAt(index) != '\n' && i < kills.length()) {
                        bufferString.setCharAt(index, kills.charAt(i));
                        index++;
                        i++;
                    }

                    if (i == kills.length()) {
                        bufferString.insert(index, '\n');
                    }

                    if (i < kills.length()) {
                        bufferString.insert(index, kills.substring(i));
                    }

                    temp = bufferString.toString();
                    foundKills = true;
                    writer.append(temp).append('\n');
                    writer.flush();
                }
                if (!foundKills) {
                    writer.append(UUID)
                            .append(" ")
                            .append(kills).append('\n');
                    writer.flush();
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
            while (bufferedReader.ready() && !gotKills) {
               temp = bufferedReader.readLine();

               if (temp.startsWith(key)) {
                   kills = Integer.parseInt(temp.substring(temp.indexOf(" ") + 1));
                   gotKills = true;
               }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return kills;
    }
}
