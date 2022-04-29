package com.moon.java.shareStudioReservation;

public class TimeTable {

    private final String[] timeTable;


    public TimeTable() {
        this.timeTable = new String[24];
    }

    public void add(int index, String customoer) {
        this.timeTable[index] = customoer;
    }

    public void remove(int index, String customoer) {

        if (this.timeTable[index] == customoer)
        this.timeTable[index] = null;
    }

    public int length() {
        return timeTable.length;
    }
    public String get(int index){

        return timeTable[index];
    }

}
