package com.klindziuk.personmanagement;


public class CurrentUser {
    public static int id = 0;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        CurrentUser.id = id;
    }

    
}
