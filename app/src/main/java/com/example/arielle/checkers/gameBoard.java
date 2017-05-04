package com.example.arielle.checkers;

import java.util.ArrayList;

/**
 * Created by arielle on 03/05/2017.
 */

public interface gameBoard {
    boolean hasWon(int player);
    void makeMove(Move m);
    Move getMove();
    ArrayList<Move> getPlayerMoves(int player);
    int evaluateBoard();
    gameBoard copy();
}
