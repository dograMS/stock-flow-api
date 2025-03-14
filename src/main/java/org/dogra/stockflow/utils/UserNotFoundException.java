package org.dogra.stockflow.utils;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("specified user not found");
    }

    public UserNotFoundException(String mesg){
        super(mesg);
    }

}
