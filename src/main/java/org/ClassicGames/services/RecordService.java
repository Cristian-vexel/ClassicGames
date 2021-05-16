package org.ClassicGames.services;

import org.ClassicGames.model.Record;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.ClassicGames.exceptions.*;
import org.ClassicGames.model.User;
import org.dizitart.no2.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.ClassicGames.services.FileSystemService.getPathToFile;

public class RecordService {
    private static ObjectRepository<Record> recordRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("ClassicGamesRecord.db").toFile())
                .openOrCreate("admin", "admin");

                recordRepository = database.getRepository(Record.class);
    }

    public static void addUser(String username, int score){
        recordRepository.insert(new Record(username, score));
    }
}
