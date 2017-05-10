package com.example.arielle.checkers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielle on 08/05/2017.
 */

public class ConnectFourBoard implements gameBoard {
    private List<ArrayList<Integer>> boardState;
    ConnectFourBoard(){
        boardState = new ArrayList<>();
        for(int i=0; i<7; i++){
            boardState.add(new ArrayList<Integer>());
        }
    }
    private ConnectFourBoard(List<ArrayList<Integer>> _boardState){
        boardState = new ArrayList<>();
        for(int i=0; i<7; i++){
            boardState.add(new ArrayList<Integer>());
            boardState.get(i).addAll(_boardState.get(i));
        }
    }
    public boolean isLegalMove(int column){
        return boardState.get(column).size()<6;
    }
    private boolean fourStreak(int stCol, int[] rows, int player){
        if (stCol+3>6){ return false; }
        for(int i=0; i<4; i++){
            if (rows[i]>=boardState.get(stCol+i).size() || rows[i]<0 || boardState.get(stCol+i).get(rows[i])!=player){ return false; }
        }
        return true;
    }
    private boolean verticalStreak(int stCol, int player){
        if (boardState.get(stCol).size()<4){ return false; }
        for(int i=boardState.get(stCol).size()-1; i>boardState.get(stCol).size()-4; i--){
            if (boardState.get(stCol).get(i)!=player){ return false; }
        }
        return true;
    }
    public boolean hasWon(int player){
        for(int i=0; i<7; i++){
            if (verticalStreak(i, player)){ return true; }
            for(int j=0; j<6; j++){
                int [] a, b, c;
                a= new int[4]; b= new int[4]; c=new int[4];
                for(int k=0; k<4; k++) {
                    a[k] = j;
                    b[k] = j-k;
                    c[k] = j+k;
                }
                if (fourStreak(i, a, player) || fourStreak(i, b, player) || fourStreak(i, c, player)){ return true; }
            }
        }
        return false;
    }
    public void makeMove(int col, int player){
        boardState.get(col).add(player);
    }
    public void makeMove(Move m){
        ConnectFourMove cmove = (ConnectFourMove) m;
        boardState.get(cmove.getColumn()).add(cmove.getPlayer());
        return;
    }
    public Move getMove(){ return new ConnectFourMove();}
    public ArrayList<Move> getPlayerMoves(int player){
        ArrayList<Move> tmp = new ArrayList<>();
        for(int i=0; i<7; i++){
            if (isLegalMove(i)){ tmp.add(new ConnectFourMove(player, i)); }
        }
        return tmp;
    }
    public int evaluateBoard(){
        if (hasWon(1)){ return 100000000; }
        else if (hasWon(2)){ return -100000000; }
        return 0;
    }
    public Integer getImage(int row, int column){
        if (boardState.get(column).size()<=row){ return R.drawable.blacksquare; }
        else if (boardState.get(column).get(row)==1){ return R.drawable.blackpiece; }
        else if (boardState.get(column).get(row)==2){ return R.drawable.redpiece;}
        else{ return R.drawable.whitesquare; }
    }
    public gameBoard copy(){return new ConnectFourBoard(boardState);}
}
