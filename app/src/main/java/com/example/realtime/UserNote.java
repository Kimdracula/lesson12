package com.example.realtime;

import java.util.Date;

public class UserNote {

     private static int counter;
     String note;
     Date date;
     String type;

    public UserNote() {
    }

    public UserNote(String note, Date date, String type) {
        this.note = note;
        this.date = date;
        this.type = type;
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static int getNumOfInstances() {
        return counter;
    }

    public static void setCounter(int counter) {
        UserNote.counter = counter;
    }
}
