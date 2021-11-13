package com.anaramada.SI.controller;

import com.anaramada.SI.model.Arena;

public class GameController {
    protected final Arena arena;

    public GameController(Arena arena){
        this.arena = arena;
    }

    public Arena getArena(){
        return arena;
    }


}
