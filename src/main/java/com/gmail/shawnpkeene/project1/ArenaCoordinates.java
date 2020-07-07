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
            x1 = ((Long)json.get("x1")).intValue();
            x2 = ((Long)json.get("x2")).intValue();
            z1 = ((Long)json.get("z1")).intValue();
            z2 = ((Long)json.get("z2")).intValue();
            blueX = ((Long)json.get("blueX")).intValue();
            blueY = ((Long)json.get("blueY")).intValue();
            blueZ = ((Long)json.get("blueZ")).intValue();
            redX = ((Long)json.get("redX")).intValue();
            redY = ((Long)json.get("redY")).intValue();
            redZ = ((Long)json.get("redZ")).intValue();
            lobX = ((Long)json.get("lobX")).intValue();
            lobY = ((Long)json.get("lobY")).intValue();
            lobZ = ((Long)json.get("lobZ")).intValue();
            gameLobX = ((Long)json.get("gameLobX")).intValue();
            gameLobY = ((Long)json.get("gameLobY")).intValue();
            gameLobZ = ((Long)json.get("gameLobZ")).intValue();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public String getWorldName() {
        fromJson();
        return worldName;
    }

    public int[] getArenaCoordinates() {
        fromJson();
        int[] arrayCoordinates = {x1, x2, z1, z2};
        return arrayCoordinates;
    }

    public int[] getBlueTeamCoordinates() {
        fromJson();
        int [] arrayCoordinates = {blueX, blueY, blueZ};
        return arrayCoordinates;
    }

    public int[] getRedTeamCoordinates() {
        fromJson();
        int[] arrayCoordinates = {redX, redY, redZ};
        return arrayCoordinates;
    }

    public int[] getLobbyCoordinates() {
        fromJson();
        int[] arrayCoordinates = {lobX, lobY, lobZ};
        return arrayCoordinates;
    }

    public int[] getGameLobbyCoordinates() {
        fromJson();
        int[] arrayCoordinates = {gameLobX, gameLobY, gameLobZ};
        return arrayCoordinates;
    }
}
