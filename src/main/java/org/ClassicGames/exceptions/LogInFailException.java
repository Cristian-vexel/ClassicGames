package org.ClassicGames.exceptions;

public class LogInFailException extends Exception {

    public LogInFailException() {
        super(String.format("Incorrect username, password or role"));
    }

}
