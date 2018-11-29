package com.gamecollector.demo.service;

import com.gamecollector.demo.model.Game;

import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public interface GameService {
    HashMap<String,Long> selectGameByName(String gameName);

    Game selectById(int Id);

    NavigableMap<String,Long> selectGamesRankByTimeRange(String startDate, String endDate, String startTime, String endTime);
}
