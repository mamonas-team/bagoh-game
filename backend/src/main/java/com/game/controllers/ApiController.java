package com.game.controllers;

@RestController
public class ApiController {

    @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
    public String metodoDoNairon( ) {
        return "Hello WOrld";
    }

}
