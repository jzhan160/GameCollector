package com.gamecollector.demo.service;

import com.gamecollector.demo.mapper.GameMapper;
import com.gamecollector.demo.model.Game;
import com.gamecollector.demo.model.GameExample;
import com.gamecollector.demo.model.ViewersUtilNow;
import com.gamecollector.demo.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("gameService")
public class GameServiceImpl implements GameService {
    @Resource
    private GameMapper gameMapper;

    //get total viewers of a given game name  from the beginning date unitl now
    @Override
    public List<ViewersUtilNow> selectGameByName(String gameName) {
        GameExample gameExample = new GameExample();
        gameExample.createCriteria().andGameEqualTo(gameName); //select by game name
        List<Game> games = gameMapper.selectByExample(gameExample);
        TreeMap<String,Integer> dateToViewer = new TreeMap<>();
        for (Game game : games){ //sort by date
            String dt = DateUtil.date2Str(game.getDate());
            dateToViewer.put(dt,dateToViewer.getOrDefault(dt,0)+game.getViewers());
        }

        //put into a list to keep the order
        List<ViewersUtilNow> viewersUtilNows = new ArrayList<>();
        for (String dt : dateToViewer.keySet()){
            ViewersUtilNow viewersUtilNow = new ViewersUtilNow();
            viewersUtilNow.setGameName(gameName);
            viewersUtilNow.setViewers(dateToViewer.get(dt)/288);
            viewersUtilNow.setDate(dt);
            viewersUtilNows.add(viewersUtilNow);
        }
         return viewersUtilNows;
    }

    @Override
    public Game selectById(int id) {
        return gameMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ViewersUtilNow> selectGamesRankByTimeRange(String startDate, String endDate, String startTime, String endTime) {
        Date start_date = DateUtil.fomatDate(startDate);
        Date end_date = DateUtil.fomatDate(endDate);
        Date start_time = DateUtil.formatTime(startTime);
        Date end_time = DateUtil.formatTime(endTime);
        GameExample gameExample = new GameExample();
        gameExample.createCriteria().andDateBetween(start_date,end_date).andTimeBetween(start_time,end_time);
        List<Game> games = gameMapper.selectByExample(gameExample);
        HashMap<String,Integer> gameToViewers = new HashMap<>();
        for (Game game : games){
            gameToViewers.put(game.getGame(),gameToViewers.getOrDefault(game,0)+game.getViewers());
        }

        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        };

         List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(gameToViewers.entrySet());

         Collections.sort(list,valueComparator);
         List<ViewersUtilNow> viewersUtilNows = new ArrayList<>();

        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
           if (count==15)
               break;
           else {
               ViewersUtilNow viewersUtilNow = new ViewersUtilNow();
               viewersUtilNow.setGameName(entry.getKey());
               viewersUtilNow.setViewers(entry.getValue());
               viewersUtilNows.add(viewersUtilNow);
               count++;
           }
        }
        return viewersUtilNows;
    }
}
