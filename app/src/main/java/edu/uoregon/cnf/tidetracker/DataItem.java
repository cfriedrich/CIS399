package edu.uoregon.cnf.tidetracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Christopher on 7/7/2016.
 */
public class DataItem {

    private SimpleDateFormat dateOutFormat = new SimpleDateFormat("yyyy/MM/d EEEE");
    private SimpleDateFormat dateInFormat = new SimpleDateFormat("yyyy/MM/d");
    private String date = null;
    private String day = null;
    private String highlow = null;
    private String ft = null;
    private String cm = null;
    private String time = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHighlow() {
        return this.highlow;
    }

    public void setHighlow(String highlow) {
        this.highlow = highlow;
    }

    public String getFeet() {
        return ft;
    }

    public void setFeet(String feet) {
        this.ft = feet;
    }

    public String getCentimeters() {
        return cm;
    }

    public void setCentimeters(String centimeters) {
        this.cm = centimeters;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFormattedDate() {
        try {
            Date date = dateInFormat.parse(this.date.trim());
            String formattedDate = dateOutFormat.format(date);
            return formattedDate;
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
