package com.gmail.shawnpkeene.project1;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class ArenaCoordinates {
    private String worldName;
    private int x1;
    private int x2;
    private int z1;
    private int z2;
    private int blueX;
    private int blueY;
    private int blueZ;
    private int redX;
    private int redY;
    private int redZ;
    private int lobX;
    private int lobY;
    private int lobZ;
    private int gameLobX;
    private int gameLobY;
    private int gameLobZ;

    public void toJson() {
        File directory = Project1.instance().getDataFolder();

        if (!directory.exists() && !directory.mkdirs()) {
            return;
        }

        File file = new File(directory, "ArenaCoordinates.json");

        try {
            if (!file.exists() && !file.createNewFile()) {
                return;
            }
            JSONObject json = new JSONObject();
            json.put("worldName", worldName);
            json.put("x1", x1);
            json.put("x2", x2);
            json.put("z1", z1);
            json.put("z2", z2);
            json.put("blueX", blueX);
            json.put("blueY", blueY);
            json.put("blueZ", blueZ);
            json.put("redX", redX);
            json.put("redY", redY);
            json.put("redZ", redZ);
            json.put("lobX", lobX);
            json.put("lobY", lobY);
            json.put("lobZ", lobZ);
            json.put("gameLobX", gameLobX);
            json.put("gameLobY", gameLobY);
            json.put("gameLobZ", gameLobZ);
            FileUtils.write(file, json.toJSONString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromJson() {
        File directory = Project1.instance().getDataFolder();

        if (!directory.exists() && !directory.mkdirs()) {
            return;
        }

        File file = new File(directory, "ArenaCoordinates.json");

        try {
            if (!file.exists() && !file.createNewFile()) {
                return;
            }
            String jsonText = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject)parser.parse(jsonText);
            worldName = (String)json.get("worldName");
            x1 = (int)json.get("x1");
            x2 = (int)json.get("x2");
            z1 = (int)json.get("z1");
            z2 = (int)json.get("z2");
            blueX = (int)json.get(blueX);
            blueY = (int)json.get(blueY);
            blueZ = (int)json.get(blueZ);
            redX = (int)json.get(redX);
            redY = (int)json.get(redY);
            redZ = (int)json.get(redZ);
            lobX = (int)json.get(lobX);
            lobY = (int)json.get(lobY);
            lobZ = (int)json.get(lobZ);
            gameLobX = (int)json.get(gameLobX);
            gameLobY = (int)json.get(gameLobY);
            gameLobZ = (int)json.get(gameLobZ);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public String getWorldName() {
        return worldName;
    }

    public int[] getArenaCoordinates() {
        int[] arrayCoordinates = {x1, x2, z1, z2};
        return arrayCoordinates;
    }

}
