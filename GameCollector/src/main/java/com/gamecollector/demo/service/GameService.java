package com.gamecollector.demo.service;

import com.gamecollector.demo.model.Game;
import com.gamecollector.demo.model.ViewersUtilNow;

import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public interface GameService {
    List<ViewersUtilNow> selectGameByName(String gameName);

    Game selectById(int Id);

    List<ViewersUtilNow> selectGamesRankByTimeRange(String startDate, String endDate, String startTime, String endTime);
}
