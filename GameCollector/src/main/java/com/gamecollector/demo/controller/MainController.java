package com.gamecollector.demo.controller;

import com.gamecollector.demo.model.Game;
import com.gamecollector.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;

@Controller
public class MainController {
    @Autowired
    private GameService gameService;

    @RequestMapping("/getViewersByName")
    public ModelAndView getViewersByName(@RequestParam("gameName") String gameName) {
        HashMap<String, Long> dateToViewer = gameService.selectGameByName(gameName);
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    @RequestMapping("/getRankByTimeRange")
    public @ResponseBody
    HashMap<String,Long> getRankByTimeRange(@RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam("startTime") String startTime,
                                  @RequestParam("endTime") String endTime) {
        NavigableMap<String,Long> gameToViewers = gameService.selectGamesRankByTimeRange(startDate,endDate,startTime,endTime);
        HashMap<String,Long> res = new HashMap<>();
        for (String game : gameToViewers.keySet())
            res.put(game,gameToViewers.get(game));
        return res;
    }

    @RequestMapping("/test")
    public String test() {
        Game game = gameService.selectById(1001);
        System.out.println(game.getGame());
        HashMap<String, Long> dateToViewer = gameService.selectGameByName("Fortnite");
        for (String dt : dateToViewer.keySet())
            System.out.println("Date: " + dt + ", Viewers: " + dateToViewer.get(dt));
        return "index";
    }


}
