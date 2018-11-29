package com.gamecollector.demo.service;

import com.gamecollector.demo.mapper.GameMapper;
import com.gamecollector.demo.model.Game;
import com.gamecollector.demo.model.GameExample;
import com.gamecollector.demo.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("gameService")
public class GameServiceImpl implements GameService {
    @Resource
    private GameMapper gameMapper;

    @Override
    public HashMap<String,Long> selectGameByName(String gameName) {
        GameExample gameExample = new GameExample();
        gameExample.createCriteria().andGameEqualTo(gameName);
        List<Game> games = gameMapper.selectByExample(gameExample);
        HashMap<String,Long> dateToViewer = new HashMap<>();
        for (Game game : games){
            String dt = game.getDate().toString();
            dateToViewer.put(dt,dateToViewer.getOrDefault(dt,0L)+game.getViewers());
        }
        for (String dt : dateToViewer.keySet())
            dateToViewer.put(dt,dateToViewer.get(dt)/288);
        return dateToViewer;

    }

    @Override
    public Game selectById(int id) {
        return gameMapper.selectByPrimaryKey(id);
    }

    @Override
    public NavigableMap<String,Long> selectGamesRankByTimeRange(String startDate, String endDate, String startTime, String endTime) {
        Date start_date = DateUtil.fomatDate(startDate);
        Date end_date = DateUtil.fomatDate(endDate);
        Date start_time = DateUtil.formatTime(startTime);
        Date end_time = DateUtil.formatTime(endTime);
        GameExample gameExample = new GameExample();
        gameExample.createCriteria().andDateBetween(start_date,end_date).andTimeBetween(start_time,end_time);
        List<Game> games = gameMapper.selectByExample(gameExample);
        TreeMap<String,Long> gameToViewers = new TreeMap<>();
        for (Game game : games){
            gameToViewers.put(game.getGame(),gameToViewers.getOrDefault(game,0L)+game.getViewers());
        }
        return gameToViewers.descendingMap();
    }
}
