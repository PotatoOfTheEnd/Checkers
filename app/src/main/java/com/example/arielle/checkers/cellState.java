package com.example.arielle.checkers;

/**
 * Created by arielle on 13/04/2017.
 */

public class cellState {
    private int id, player, type;
    private int[] moves;
    cellState(int _id, int _player, int _type, int[] _moves){
        id=_id; player = _player; type = _type;
        moves = new int[4];
        for(int i=0; i<4; i++){
            moves[i] = _moves[i];
        }
    }
    cellState(cellState a){
        moves = new int[4];
        id = a.getId(); player = a.getPlayer(); type = a.getType();
        for(int i=0; i<4; i++){ moves[i] = a.getMove(i); }
    }
    cellState(int ind){
        id = ind;
        moves = new int[4];
        if ((ind/4)%2 == 0){
            moves[0] = ind-5; moves[1] = ind-4;
            moves[2] = ind+3; moves[3] = ind+4;
            if (ind%4== 0){
                moves[0]=-1; moves[2]=-1;
            }
        }
        else{
            moves[0] = ind-4; moves[1] = ind-3;
            moves[2] = ind+4; moves[3] = ind+5;
            if (ind%4 == 3){
                moves[1] = -1; moves[3] = -1;
            }
        }
        for(int i=0; i<4; i++){if (moves[i]<0 || moves[i]>31){moves[i]=-1; }}

        if (ind/4 < 3){ player = 1; type = 1; }
        else if (ind/4 > 4 ){ player = 2; type = 1; }
        else{ player = 0; type = 0; }
    }
    public String toString(){
        return String.format("Id: %d, Player: %d, Type: %d, Moves: %d %d %d %d\n", id, player, type, moves[0], moves[1], moves[2], moves[3]);
    }
    public void setPlayer(int _player){ player = _player; }
    public void setType(int _type) { type = _type; }
    public void setCell(cellState a){
        player = a.getPlayer(); type = a.getType();
    }
    public void clear(){
        player = 0; type = 0;
    }
    public int getPlayer(){ return player; }
    public int getType(){ return type; }
    public int getId(){ return id; }
    public boolean isNeighbor(int vl){
        for (int i: moves){ if(i == vl) return true; }
        return false;
    }
    public int getMove(int ind){
        return moves[ind];
    }
}

