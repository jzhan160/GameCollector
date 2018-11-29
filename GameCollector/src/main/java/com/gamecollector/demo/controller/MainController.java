package com.gamecollector.demo.controller;

import com.gamecollector.demo.model.Game;
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
    public ModelAndView getViewersByName(@RequestParam("gameName") String gameName) {
        List<ViewersUtilNow> viewersUtilNows = gameService.selectGameByName(gameName);
        ModelAndView mv = new ModelAndView("getViewersByName");
        mv.addObject("viewersUtilNows",viewersUtilNows);
        return mv;
    }


    //get top 15 games with the most viewers in a time range
    @RequestMapping(value = "/getRankByTimeRange",method = RequestMethod.POST)
    public ModelAndView getRankByTimeRange(@RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam("startTime") String startTime,
                                  @RequestParam("endTime") String endTime) {
        List<ViewersUtilNow> viewersRank = gameService.selectGamesRankByTimeRange(startDate,endDate,startTime,endTime);
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
        return "indexTrue";
    }
}
