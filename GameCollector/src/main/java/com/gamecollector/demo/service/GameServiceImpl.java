package com.gamecollector.demo.service;

import com.gamecollector.demo.mapper.GameMapper;
import com.gamecollector.demo.model.Game;
import com.gamecollector.demo.model.GameExample;
import com.gamecollector.demo.model.ViewerResult;
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
    public  List<List<ViewerResult>> selectGameByName(List<String> gameNames) {
        List<List<ViewerResult>> res = new ArrayList<>();
        for (String gameName : gameNames){
            GameExample gameExample = new GameExample();
            gameExample.createCriteria().andGameEqualTo(gameName); //select by game name
            List<ViewerResult> viewerResult = gameMapper.selectViewersOfOneGameByExample(gameExample);
            res.add(viewerResult);
        }
         return res;
    }

    @Override
    public Game selectById(int id) {
        return gameMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ViewerResult> selectGamesRankByDateTimeRange(String startDate, String endDate, String startTime, String endTime) {
        Date start_date = DateUtil.fomatDate(startDate);
        Date end_date = DateUtil.fomatDate(endDate);
        Date start_time = DateUtil.formatTime(startTime);
        Date end_time = DateUtil.formatTime(endTime);
        GameExample gameExample = new GameExample();
        List<ViewerResult> games = null;
        if (start_date==null&&end_date==null)
            gameExample.createCriteria().andTimeBetween(start_time,end_time);
        else if (start_time==null&&end_time==null)
            gameExample.createCriteria().andDateBetween(start_date,end_date);
        else
            gameExample.createCriteria().andDateBetween(start_date,end_date).andTimeBetween(start_time,end_time);

        games = gameMapper.selectViewersInATimeRangeByExample(gameExample);
        return games;
    }


}
