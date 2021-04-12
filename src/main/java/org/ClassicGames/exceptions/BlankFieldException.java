package org.ClassicGames.exceptions;

public class BlankFieldException extends Exception {

    public BlankFieldException() {
        super(String.format("Username, password and role must not be blank"));
    }

}
