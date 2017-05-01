package com.example.arielle.checkers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arielle on 13/04/2017.
 */

public class MinMax {
    private static ArrayList<Map<Long, Move>> p1turn;
    private static ArrayList<Map<Long, Move>> p2turn;
    MinMax(int maxDepth){
        p1turn = new ArrayList<>();
        p2turn = new ArrayList<>();
        for(int i=0; i<=maxDepth; i++){
            p1turn.add(new HashMap<Long, Move>());
            p2turn.add(new HashMap<Long, Move>());
        }
    }
    public Move getVal(boardState currentBoard, int player, int depth, int alpha, int beta){
        //player 1 maximizes, player 2 minimizes
        long hashval = 0, seed=1;
        for(int i=0; i<32; i++){
            hashval+=currentBoard.getVal(i)*seed;
            seed*=13;
        }
        int bestScore;
        boardState tpBoard;
        Move finalMove = new Move(0, 0, 1);
        if (player == 1){
            if (p1turn.get(depth).containsKey(hashval)){
                return (Move) p1turn.get(depth).get(hashval);
            }
            bestScore = -100000000;
            for(int i=0; i<32; i++){
                if (currentBoard.getCell(i).getPlayer()!=1){ continue; }
                if (depth == 0){
                    if (currentBoard.getMoves(i).size()>0){
                        bestScore = currentBoard.evaluateBoard();
                        break;
                    }
                    continue;
                }
                for(Move j: currentBoard.getMoves(i)){
                    tpBoard = new boardState(currentBoard.getCurrentBoard());
                    tpBoard.makeMove(j);
                    int tmp = getVal(tpBoard, 2, depth-1, alpha, beta).getScore();
                    if (tmp >= bestScore){
                        finalMove = j;
                        bestScore = tmp;
                    }
                    alpha = Math.max(alpha, bestScore);
                    if (alpha>=beta){ break; }
                }
            }

            finalMove.setScore(bestScore);
            p1turn.get(depth).put(hashval, finalMove);
            return finalMove;
        }
        else{
           if (p2turn.get(depth).containsKey(hashval)){
                return (Move) p2turn.get(depth).get(hashval);
            }
            bestScore = 100000000;
            for(int i=0; i<32; i++){
                if (currentBoard.getCell(i).getPlayer()!=2){ continue; }
                if (depth == 0){
                    if (currentBoard.getMoves(i).size()>0){
                        bestScore = currentBoard.evaluateBoard();
                        break;
                    }
                    continue;
                }
                for(Move j: currentBoard.getMoves(i)){
                    tpBoard = new boardState(currentBoard.getCurrentBoard());
                    tpBoard.makeMove(j);
                    int tmp = getVal(tpBoard, 1, depth-1, alpha, beta).getScore();
                    if (tmp <= bestScore){
                        finalMove = j;
                        bestScore = tmp;
                    }
                    beta = Math.min(beta, bestScore);
                    if (alpha>=beta){ break; }
                }
            }
            finalMove.setScore(bestScore);
            p2turn.get(depth).put(hashval, finalMove);
            return finalMove;

        }

    }
}