package com.gamecollector.demo.service;

import com.gamecollector.demo.model.Game;
import com.gamecollector.demo.model.ViewerResult;
import com.gamecollector.demo.model.ViewersUtilNow;

import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public interface GameService {
    List<List<ViewerResult>> selectGameByName(List<String> gameName);

    Game selectById(int Id);

    List<ViewerResult> selectGamesRankByDateTimeRange(String startDate, String endDate, String startTime, String endTime);


}
