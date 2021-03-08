package com.amansiol.notes;

public class Task
{   int id;
    String Title;
    String Message;
    String Date;
    String Week;
    String time;
    int    smile;


    public Task(int id, String title, String message, String date, String week, String time, int smile) {
        this.id = id;
        Title = title;
        Message = message;
        Date = date;
        Week = week;
        this.time = time;
        this.smile = smile;
    }

    public Task(String title, String message, String date, String week, String time, int smile) {
        Title = title;
        Message = message;
        Date = date;
        Week = week;
        this.time = time;
        this.smile = smile;
    }

    public Task()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getWeek() {
        return Week;
    }

    public void setWeek(String week) {
        Week = week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSmile() {
        return smile;
    }

    public void setSmile(int smile) {
        this.smile = smile;
    }
}
