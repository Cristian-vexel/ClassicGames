package org.ClassicGames.service;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.ClassicGames.exceptions.BlankFieldException;
import org.ClassicGames.exceptions.UsernameAlreadyExistsException;
import org.ClassicGames.model.User;
import org.ClassicGames.services.FileSystemService;
import org.ClassicGames.services.UserService;
import org.ClassicGames.services.RecordService;
import org.ClassicGames.model.Record;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

public class RecordServiceTest {

    public static final String USER = "user";
    public static final int SCORE = 5;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".ClassicGames";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        RecordService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }


    @Test
    @DisplayName("Database is initialized, and there are no users")
    void testDatabaseIsInitializedAndNoRecordIsPersisted() {
        assertThat(RecordService.getAllRecords()).isNotNull();
        assertThat(RecordService.getAllRecords()).isEmpty();
    }

    @Test
    @DisplayName("Record is successfully persisted to Database")
    void testUserIsAddedToDatabase() {
        RecordService.addRecord(USER, SCORE);
        assertThat(RecordService.getAllRecords()).isNotEmpty();
        assertThat(RecordService.getAllRecords()).size().isEqualTo(1);
        Record user = RecordService.getAllRecords().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(USER);
        assertThat(user.getRecord()).isEqualTo(5);
    }

}
