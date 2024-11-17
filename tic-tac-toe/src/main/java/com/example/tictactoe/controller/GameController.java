package com.example.tictactoe.controller;

import com.example.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:3000") //"Shreesha localhost is 3000 remeber by default you can change it in vscode if cant host on this"
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/move")
    public Map<String, Object> makeMove(@RequestBody Move move) {
        String username = move.getUsername();
        String player = move.getPlayer();
        int row = move.getRow();
        int col = move.getCol();

        
        gameService.makeMove(row, col, player);

        // Check for winner
        String winner = gameService.checkWin();
        boolean draw = gameService.checkDraw();

        Map<String, Object> response = new HashMap<>();
        response.put("board", gameService.getBoard());
        response.put("winner", winner);
        response.put("draw", draw);     

        return response;
    }

    @PostMapping("/register")
    public Map<String, Object> registerUser(@RequestBody User user) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true); 
        return response;
    }

    
}

