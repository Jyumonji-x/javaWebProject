package com.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class time {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            this.date = f.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
