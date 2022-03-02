package com.example.realtime;

import java.util.Date;

final class UserNote {


    private static int counter;

    final String note;
    final Date date;
    final String type;

    public UserNote(String note, Date date, String type) {
        this.note = note;
        this.date = date;
        this.type = type;
        counter++;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public static int getNumOfInstances() {
        return counter;
    }

    public static void setCounter(int counter) {
        UserNote.counter = counter;
    }
}
