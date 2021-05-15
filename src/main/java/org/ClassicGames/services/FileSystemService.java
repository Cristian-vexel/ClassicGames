package org.ClassicGames.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ClassicGames.games.snake.*;

public class FileSystemService {
    private static final String APPLICATION_FOLDER = ".ClassicGames";
    private static final String USER_FOLDER = System.getProperty("user.home");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }

    public static void loadSettingsFromFile(){
        BufferedReader reader;
        File f = FileSystemService.getPathToFile("settings.txt").toFile();

        if(!f.exists()){
            System.out.println("Settings file do not exist");
            return;
        }

        try{
            reader = new BufferedReader(new FileReader(f));

            SnakeConfiguration.setSpeed(Float.parseFloat(reader.readLine()));
            SnakeConfiguration.setInitialLength(Integer.parseInt(reader.readLine()));
            SnakeConfiguration.setGreedSize(Integer.parseInt(reader.readLine()));

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSettingsToFile() {
        BufferedWriter writer;

        try{
            File f = FileSystemService.getPathToFile("settings.txt").toFile();
            if(!f.exists()){
                System.out.println("Settings file do not exist");
                f.createNewFile();
            }

            writer = new BufferedWriter(new FileWriter(f));

            writer.write(String.valueOf(SnakeConfiguration.getSpeed()) + "\n");
            writer.write(String.valueOf(SnakeConfiguration.getInitialLength()) + "\n");
            writer.write(String.valueOf(SnakeConfiguration.getGreedSize()));

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
