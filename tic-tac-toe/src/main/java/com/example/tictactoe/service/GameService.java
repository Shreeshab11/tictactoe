package com.example.tictactoe.service;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    private String[][] board = new String[3][3];

    public GameService() {
        resetBoard();
    }

    public String[][] getBoard() {
        return board;
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null; 
            }
        }
    }

    public void makeMove(int row, int col, String player) {
        if (board[row][col] == null) {
            board[row][col] = player;
        }
    }

    public String checkWin() {
        
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != null && board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])) {
                return board[row][0]; 
            }
        }

        
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != null && board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])) {
                return board[0][col]; 
            }
        }

        
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0]; 
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }

        return null; 
    }

    public boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == null) { 
                    return false;
                }
            }
        }
        return true; 
    }
}
