package com.gmail.shawnpkeene.project1;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class KillsFile {
    private int kills;
    public void toJson(String playerID, boolean containsKey) {
        File directory = Project1.instance().getDataFolder();

        if (!directory.exists() && !directory.mkdirs()) {
            return;
        }

        File file = new File(directory, "Kills.json");

        try {
            if (!file.exists() && !file.createNewFile()) {
                return;
            }

            String jsonText = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);

            if (containsKey) {
                kills = ((Long)json.get(playerID)).intValue();
                kills += 1;
                json.put(playerID, kills);
            } else {
                json.put(playerID, kills);
            }
            FileUtils.write(file, json.toJSONString(), StandardCharsets.UTF_8);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void toJson() {
        File directory = Project1.instance().getDataFolder();

        if (!directory.exists() && !directory.mkdirs()) {
            return;
        }

        File file = new File(directory, "Kills.json");

        try {
            if (!file.exists() && !file.createNewFile()) {
                return;
            }
            JSONObject json = new JSONObject();
            json.put("0", 0);
            FileUtils.write(file, json.toJSONString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int fromJson(String uuid) {
        File directory = Project1.instance().getDataFolder();

        try {
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IOException("Failed creating directory");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(directory, "Kills.json");

        try {
            if (!file.exists() && !file.createNewFile()) {
                throw new IOException("Failed creating file");
            }

            String jsonText = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);

            if (json.containsKey(uuid)) {
                kills = ((Long) json.get(uuid)).intValue();
            } else {
               toJson(uuid, false);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return kills;
    }
}
