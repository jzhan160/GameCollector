package com.gamecollector.demo.model;

import com.gamecollector.demo.util.DateUtil;

import java.util.Date;

public class ViewerResult {
    private String game;
    private long total;
    private Date date;
    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getDate() {
        return DateUtil.date2Str(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
