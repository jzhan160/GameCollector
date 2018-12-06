package com.gamecollector.demo.model;

import java.util.List;

public class GameList {
    private List<Long> totals;
    private List<Integer> dates;

    public List<Long> getTotals() {
        return totals;
    }

    public void setTotals(List<Long> gameNames) {
        this.totals = gameNames;
    }

    public List<Integer> getDates() {
        return dates;
    }

    public void setDates(List<Integer> dates) {
        this.dates = dates;
    }
}
