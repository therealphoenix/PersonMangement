package com.klindziuk.personmanagement;


public class CurrentPerson {
    public static int id = 0;
    public static String editPath = "";

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        CurrentPerson.id = id;
    }

    public static String editPath() { return  editPath; }

    public static void setEditPath(String path) {
        CurrentPerson.editPath = path;}

    
}
