package com.gamecollector.demo.model;

import java.util.Date;

public class ViewersUtilNow {
    private String gameName;
    private String date;
    private int viewers;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    @Override
    public String toString() {
        return "ViewersUtilNow{" +
                "gameName='" + gameName + '\'' +
                ", date=" + date +
                ", viewers=" + viewers +
                '}';
    }
}
