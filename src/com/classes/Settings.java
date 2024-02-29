package com.classes;

public class Settings {
    private static final String FILENAME = "file/kvetiny.txt";//pridat polohu souboru
    private static final String DELIMITER = "; ";

    public static String getFilename(){
        return FILENAME;
    }
    public static String getDelimiter(){
        return DELIMITER;
    }
}
