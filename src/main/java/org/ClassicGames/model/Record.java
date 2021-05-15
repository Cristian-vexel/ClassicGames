package org.ClassicGames.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Record {
    private String username;
    private int record;

    @Override
    public String toString() {
        return "Record{" +
                "username='" + username + '\'' +
                '}';
    }

    public Record(String username, int record) {
        this.username = username;
        this.record = record;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record1 = (Record) o;
        return record == record1.record && Objects.equals(username, record1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, record);
    }
}
