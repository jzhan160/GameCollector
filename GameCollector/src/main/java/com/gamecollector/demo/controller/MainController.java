package com.gamecollector.demo.controller;

import com.gamecollector.demo.model.Game;
import com.gamecollector.demo.model.GameList;
import com.gamecollector.demo.model.ViewerResult;
import com.gamecollector.demo.model.ViewersUtilNow;
import com.gamecollector.demo.service.GameService;
import com.gamecollector.demo.service.PythonService;
import org.python.antlr.ast.Str;
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
    @Autowired
    private PythonService pythonService;

    //get total viewers of a given game name  from the beginning date unitl now
    @RequestMapping(value = "/getViewersByName",method = RequestMethod.POST)
    public @ResponseBody  List<GameList> getViewersByName(@RequestParam("gameNames") List<String> gameNames) {
        List<List<ViewerResult>> res = gameService.selectGameByName(gameNames);
        List<GameList> gameLists = new LinkedList<>();
        for(List<ViewerResult> curR : res){
            GameList g = new GameList();
            List<Long> total = new LinkedList<>();
            List<Integer> dates = new LinkedList<>();

            for(int i = 0; i < curR.size(); i++ ){
                total.add(curR.get(i).getTotal());
                dates.add(i);
            }
            g.setGameName(curR.get(0).getGame());
            g.setDates(dates);
            g.setTotals(total);
            gameLists.add(g);
        }
        return gameLists;
    }


    //get top 15 games with the most viewers in a time range

    @RequestMapping(value = "/getRankByDateTimeRange",method = RequestMethod.POST)
    public @ResponseBody List<ViewerResult> getRankByDateTimeRange(@RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam("startTime") String startTime,
                                  @RequestParam("endTime") String endTime) {

        System.out.println(parseDateTime(startTime,startDate));
        System.out.println(parseDateTime(endTime,endDate));
        String[] start = parseDateTime(startTime,startDate).split(" ");
        String[] end = parseDateTime(endTime,endDate).split(" ");
        List<ViewerResult> viewersRank = gameService.selectGamesRankByDateTimeRange(start[0],end[0],start[1],end[1]);
        return viewersRank;
    }

    private String parseDateTime(String time, String date){
        String res = "";
        Map<String, String> t = new HashMap<>();
        t.put("Dec","12");
        t.put("Nov","11");
        t.put("Oct","10");
        t.put("Sep", "9");
        t.put("Aug", "8");
        t.put("Jul","7");
        t.put("Jun","6");
        t.put("May","5");
        t.put("Apr","4");
        t.put("Mar","3");
        t.put("Feb","2");
        t.put("Jan","1");
        String[] d = date.split(" ");
        //"yyyy-MM-dd HH:mm:ss"
        res += d[3] + "-";
        res += t.get(d[1]) + "-";
        res += d[2] + " ";

        String[] ti = time.split(" ");
        for(int i = 0; i < time.length(); i++){
            if(time.charAt(i) == ':'){
                if(ti[1].equals("AM")){
                    res += time.substring(0,i) + ":";
                    res += ti[0].substring(i+1) + ":00";
                    return res;
                }
                else{
                    res += String.valueOf(Integer.valueOf(time.substring(0,i)) + 12) + ":";
                    res += ti[0].substring(i+1) + ":00";
                    return res;
                }
            }
        }


        return "";
    }



    @RequestMapping("/search")
    public String test() {
        return "search";
    }

    @RequestMapping("/tab2")
    public String toName(){
        return "tab2";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/getScores",method = RequestMethod.POST)
    public @ResponseBody String getScores(@RequestParam("review") String review) {
        System.out.println(review+"...............");
        String score = pythonService.getScoreByReview(review);
        return score;
    }

}
