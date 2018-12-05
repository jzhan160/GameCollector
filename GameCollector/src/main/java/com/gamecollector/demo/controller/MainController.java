package com.gamecollector.demo.controller;

import com.gamecollector.demo.model.Game;
import com.gamecollector.demo.model.ViewerResult;
import com.gamecollector.demo.model.ViewersUtilNow;
import com.gamecollector.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private GameService gameService;

    //get total viewers of a given game name  from the beginning date unitl now
    @RequestMapping(value = "/getViewersByName",method = RequestMethod.POST)
    public ModelAndView getViewersByName(@RequestParam("gameNames") List<String> gameNames) {
        List<List<ViewerResult>> viewersUtilNows = gameService.selectGameByName(gameNames);
        ModelAndView mv = new ModelAndView("getViewersByName");
        mv.addObject("viewersUtilNows",viewersUtilNows);
        return mv;
    }


    //get top 15 games with the most viewers in a time range
    @RequestMapping(value = "/getRankByDateTimeRange",method = RequestMethod.POST)
    public ModelAndView getRankByDateTimeRange(@RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam("startTime") String startTime,
                                  @RequestParam("endTime") String endTime) {
        List<ViewerResult> viewersRank = gameService.selectGamesRankByDateTimeRange(startDate,endDate,startTime,endTime);
        ModelAndView mv = new ModelAndView("viewersRank");
        mv.addObject("viewersRank",viewersRank);
        return mv;
    }



    @RequestMapping("/tab1")
    public String test() {
        return "tab1";
    }

    @RequestMapping("/tab2")
    public String toName(){
        return "tab2";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
