package com.game.score.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserGameScoreController {

    @RequestMapping("/{levelid}/highscorelist")
    public String findHighScoreList(@PathVariable int levelid){
        return "Hello";
    }

    @RequestMapping(method = RequestMethod.POST,value="/{levelid}/{sessionkey}")
    public String maintainUserScore(@PathVariable int levelid,@PathVariable String sessionkey){
        return "Hello sessionkey"; // store userid with levelid and score
    }

}
