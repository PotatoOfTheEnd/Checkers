package com.example.arielle.checkers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielle on 08/05/2017.
 */

public class ConnectFourBoard implements GameBoard {
    private List<ArrayList<Integer>> boardState;

    ConnectFourBoard() {
        boardState = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            boardState.add(new ArrayList<Integer>());
        }
    }

    private ConnectFourBoard(List<ArrayList<Integer>> boardState) {
        this.boardState = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            this.boardState.add(new ArrayList<Integer>());
            this.boardState.get(i).addAll(boardState.get(i));
        }
    }

    private boolean isFree(int row, int col) {
        return (col >= 0 && col < 7 && row < 6 && row >= boardState.get(col).size());
    }

    private boolean isPlayer(int row, int col, int player) {
        return (col >= 0 && col < 7 && row >= 0 && row < boardState.get(col).size() && boardState.get(col).get(row) == player);
    }

    public boolean isLegalMove(int column) {
        return boardState.get(column).size() < 6;
    }

    private boolean isStreak(int stCol, int[] rows, int player) {
        for (int i = 0; i < rows.length; i++) {
            if (!isPlayer(rows[i], stCol + i, player)) {
                return false;
            }
        }
        return true;
    }

    private boolean verticalStreak(int stCol, int player, int length) {
        if (boardState.get(stCol).size() < length) {
            return false;
        }
        for (int i = boardState.get(stCol).size() - 1; i >= boardState.get(stCol).size() - length; i--) {
            if (boardState.get(stCol).get(i) != player) {
                return false;
            }
        }
        return true;
    }

    private int[] getLine(int row, int length, int dir) {
        int[] tp = new int[length];
        for (int k = 0; k < length; k++) {
            tp[k] = row + k * dir;
        }
        return tp;
    }

    public boolean hasWon(int player) {
        for (int i = 0; i < 7; i++) {
            if (verticalStreak(i, player, 4)) {
                return true;
            }
            for (int j = 0; j < 6; j++) {
                int[] a, b, c;
                a = getLine(j, 4, 0);
                b = getLine(j, 4, -1);
                c = getLine(j, 4, 1);
                if (isStreak(i, a, player) || isStreak(i, b, player) || isStreak(i, c, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void makeMove(int col, int player) {
        boardState.get(col).add(player);
    }

    public void makeMove(Move m) {
        ConnectFourMove cmove = (ConnectFourMove) m;
        boardState.get(cmove.getColumn()).add(cmove.getPlayer());
        return;
    }

    public Move getMove() {
        return new ConnectFourMove();
    }

    public ArrayList<Move> getPlayerMoves(int player) {
        ArrayList<Move> tmp = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (isLegalMove(i)) {
                tmp.add(new ConnectFourMove(player, i));
            }
        }
        return tmp;
    }

    public boolean isTie() {
        for (int i = 0; i < 7; i++) {
            if (boardState.get(i).size() != 6) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreat(int row, int col, int player) {
        for (int i = -1; i <= 1; i++) {
            for (int j = 0; j <= 3; j++) {
                if (isStreak(col - j, getLine(row - i * j, j, i), player) && isStreak(col + 1, getLine(row + i, 3 - j, i), player)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int evaluateBoard(int player) {
        //    if (hasWon(1)){ return 100000000; }
        //    else if (hasWon(2)){ return -100000000; }
        int sm = 0;
        int p1threats = 0, p2threats = 0;
        for (int i = 0; i < 7; i++) {
            if (boardState.get(i).size() >= 6) continue;
            int lowestThreat = 0, lowPlayer = 0;
            //lowest threat in the column determines who plays at which index
            //if a spot is a threat for both parties, no spots above it are relevant
            //because the game ends as soon as it is played
            if (verticalStreak(i, 1, 3) || isThreat(boardState.get(i).size(), i, 1)) {
                if (player == 1) { return 100000000; }
                p1threats++;
                if (p1threats == 2) {
                    sm += 1000000;
                } else if (p1threats > 1) {
                    sm += 500000;
                } else {
                    sm += 5000;
                }
                lowPlayer = 2; lowestThreat = boardState.get(i).size()%2;
            }
            if (verticalStreak(i, 2, 3) || isThreat(boardState.get(i).size(), i, 2)) {
                if (player == 2) { return -100000000; }
                p2threats++;
                if (p2threats == 2) {
                    sm -= 1000000;
                } else if (p1threats > 1) {
                    sm -= 500000;
                } else {
                    sm -= 5000;
                }
                if (lowPlayer == 2){
                    continue;
                } else {
                    lowPlayer = 1; lowestThreat = boardState.get(i).size()%2;
                }
            }
            for (int j = boardState.get(i).size()+1; j < 6; j++) {
                //5000 for first threat
                //1000 for probably useless threat
                //4000 for useful threat

                if (isThreat(j, i, 1)) {
                    if (isThreat(j, i, 2)) {
                        if (lowPlayer == 0) {
                            break;
                        } else if (lowPlayer == 1) {
                            if (j%2 == lowestThreat) {
                                sm+= 3000;
                            } else {
                                sm-=3000;
                            }
                        } else {
                            if (j%2 == lowestThreat) {
                                sm-= 3000;
                            } else {
                                sm+=3000;
                            }
                        }
                        break;
                    } else {
                        if (lowPlayer == 0) {
                            lowPlayer = 2;
                            sm+=5000;
                            lowestThreat=j%2;
                        } else if (lowPlayer == 1){
                            if (j%2 == lowestThreat) {
                                sm+=4000;
                            } else {
                                sm+=1000;
                            }
                        } else {
                            if (j%2 == lowestThreat) {
                                sm+=1000;
                            } else {
                                sm+=4000;
                            }
                        }
                    }
                } else if (isThreat(j, i, 2)) {
                    if (lowPlayer == 0) {
                        lowPlayer = 1;
                        sm-=5000;
                        lowestThreat=j%2;
                    } else if (lowPlayer == 2){
                        if (j%2 == lowestThreat) {
                            sm-=4000;
                        } else {
                            sm-=1000;
                        }
                    } else {
                        if (j%2 == lowestThreat) {
                            sm-=1000;
                        } else {
                            sm-=4000;
                        }
                    }
                }
            }
        }
        return sm;
    }

    public Integer getImage(int row, int column) {
        if (boardState.get(column).size() <= row) {
            return R.drawable.blacksquare;
        } else if (boardState.get(column).get(row) == 1) {
            return R.drawable.blackpiece;
        } else if (boardState.get(column).get(row) == 2) {
            return R.drawable.redpiece;
        } else {
            return R.drawable.whitesquare;
        }
    }

    public GameBoard copy() {
        return new ConnectFourBoard(boardState);
    }
}
