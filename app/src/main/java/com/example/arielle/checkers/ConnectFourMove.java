package com.example.arielle.checkers;


/**
 * Created by arielle on 08/05/2017.
 */

public class ConnectFourMove implements Move {
    private int score, player, column;
    private boolean defaultMove;

    ConnectFourMove(int player, int column) {
        this.player = player;
        this.column = column;
        score = 0;
        defaultMove = false;
    }

    ConnectFourMove() {
        player = 0;
        column = 0;
        score = 0;
        defaultMove = true;
    }

    public int getPlayer() {
        return player;
    }

    public int getColumn() {
        return column;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int _score) {
        score = _score;
    }

    public boolean isDefault() {
        return defaultMove;
    }
}
