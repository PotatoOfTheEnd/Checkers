package com.example.arielle.checkers;

/**
 * Created by arielle on 13/04/2017.
 */

public class MinMax {
    public static Move getVal(GameBoard currentBoard, int player, int depth, int alpha, int beta) {
        Move finalMove = currentBoard.getMove();
        int bestScore, opponent;
        double rnd = -1;
        GameBoard tpBoard;
        if (player == 1) {
            opponent = 2;
            bestScore = -100000000;
        } else {
            opponent = 1;
            bestScore = 100000000;
        }
        if (depth == 0) {
            if (!currentBoard.hasWon(opponent)) {
                bestScore = currentBoard.evaluateBoard(player);
            }
        } else {
            for (Move j : currentBoard.getPlayerMoves(player)) {
                tpBoard = currentBoard.copy();
                tpBoard.makeMove(j);
                if (tpBoard.hasWon(player)) {
                    bestScore = (player == 1) ? 100000000 : -100000000;
                    finalMove = j;
                    break;
                }
                int tmp = getVal(tpBoard, opponent, depth - 1, alpha, beta).getScore();

                if (player == 1) {
                    if (tmp == bestScore) {
                        double tprand = Math.random();
                        if (tprand > rnd) {
                            finalMove = j;
                            bestScore = tmp;
                            rnd = tprand;
                        }
                    } else if (tmp > bestScore) {
                        finalMove = j;
                        bestScore = tmp;
                        rnd = Math.random();
                    }
                    alpha = Math.max(alpha, bestScore);
                } else {
                    if (tmp == bestScore) {
                        double tprand = Math.random();
                        if (tprand > rnd) {
                            finalMove = j;
                            bestScore = tmp;
                            rnd = tprand;
                        }
                    } else if (tmp < bestScore) {
                        finalMove = j;
                        bestScore = tmp;
                        rnd = Math.random();
                    }
                    beta = Math.min(beta, bestScore);
                }
                if (alpha > beta) {
                    break;
                }
            }
        }
        finalMove.setScore(bestScore);
        return finalMove;
    }
}