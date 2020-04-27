package com.gmail.shawnpkeene.project1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtilities {

    private static File getDataFolder() {
        return Project1.instance().getDataFolder();
    }

    public static void test() {
        File directory = getDataFolder();

        if (!directory.exists() && !directory.mkdirs()) {
            return;
        }

        File testFile = new File(directory, "Test.txt");

        try {
            if (!testFile.exists() && !testFile.createNewFile()) {
                return;
            }
            try(FileWriter writer = new FileWriter(testFile)) {
                writer.append("dsdkajd");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
