package com.example.arielle.checkers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arielle on 13/04/2017.
 */

public class MinMax {
    public static Move getVal(gameBoard currentBoard, int player, int depth, int alpha, int beta){
        Move finalMove = currentBoard.getMove();
        int bestScore, opponent;
        gameBoard tpBoard;
        if (player == 1) {
            opponent = 2;
            bestScore = -100000000;
        }
        else {
            opponent = 1;
            bestScore = 100000000;
        }
        if (depth ==0 ){
            if (!currentBoard.hasWon(opponent)){
                bestScore = currentBoard.evaluateBoard();
            }
        }
        else{
            for(Move j: currentBoard.getPlayerMoves(player)){
                tpBoard = currentBoard.copy();
                tpBoard.makeMove(j);
                if (tpBoard.hasWon(player)){
                    bestScore = (player==1)?100000000:-100000000;
                    finalMove = j;
                    break;
                }
                int tmp = getVal(tpBoard, opponent, depth-1, alpha, beta).getScore();

                if (player == 1) {
                    if (tmp >= bestScore){
                        finalMove = j;
                        bestScore = tmp;
                    }
                    alpha = Math.max(alpha, bestScore);
                }
                else {
                    if (tmp <= bestScore){
                        finalMove = j;
                        bestScore = tmp;
                    }
                    beta = Math.min(beta, bestScore);
                }
                if (alpha>beta){ break; }
            }
        }
        finalMove.setScore(bestScore);
        return finalMove;
    }
}