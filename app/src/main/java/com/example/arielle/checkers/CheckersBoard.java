package com.example.arielle.checkers;

/**
 * Created by arielle on 13/04/2017.
 */

import java.util.ArrayList;
public class CheckersBoard implements gameBoard{
    private cellState[] currentBoard;
    CheckersBoard(){
        currentBoard = new cellState[32];
        for(int i=0; i<32; i++){
            currentBoard[i] = new cellState(i);

        }
    }
    CheckersBoard(cellState[] _currentBoard){
        currentBoard = new cellState[32];
        for(int i=0; i<32; i++){
            currentBoard[i] = new cellState(_currentBoard[i]);
        }
    }
    public CheckersBoard copy(){
        return new CheckersBoard(currentBoard);
    }

    public boolean isValidIndex(int row, int col){
        if (row<0 || row>7 || col<0 || col>7){return false; }
        if (row%2 == 0){
            if (col%2 == 0){ return true; }
            else{ return false; }
        }
        else{
            if (col%2 != 0){ return true; }
            else{ return false; }
        }
    }

    public int getPlayerAt(int row, int col){
        return currentBoard[row*4+col/2].getPlayer();
    }

    public int getTypeOfPieceAt(int row, int col){
        return currentBoard[row*4+col/2].getType();
    }

    public boolean isLegalPlay(int startRow, int startCol, int destRow, int destCol, int player){
        if (!isValidIndex(startRow, startCol) || !isValidIndex(destRow,destCol)){ return false; }
        if (getPlayerAt(startRow, startCol)!=player){ return false; }
        int inda = getIndexOfCell(startRow, startCol), indb = getIndexOfCell(destRow, destCol);
        if (legalMove(inda, indb)){ return true;}
        for(int i=0; i<4; i++){
            if (legalJump(inda, currentBoard[inda].getMove(i), indb)){ return true; }
        }
        return false;
    }

    public boolean isLegalJump(int startRow, int startCol, int destRow, int destCol, int player){
        if (!isValidIndex(startRow, startCol) || !isValidIndex(destRow,destCol)){ return false; }
        if (getPlayerAt(startRow, startCol)!=player){ return false; }
        int inda = getIndexOfCell(startRow, startCol), indb = getIndexOfCell(destRow, destCol);
        for(int i=0; i<4; i++){
            if (legalJump(inda, currentBoard[inda].getMove(i), indb)){ return true; }
        }
        return false;
    }

    public boolean movePiece(int startRow, int startCol, int destRow, int destCol){
        int inda = getIndexOfCell(startRow, startCol), indb = getIndexOfCell(destRow, destCol);
        if (legalMove(inda, indb)){ move(inda, indb); return false;}
        else{
            for(int i=0; i<4; i++){
                if (legalJump(inda, currentBoard[inda].getMove(i), indb)){ jump(inda, currentBoard[inda].getMove(i), indb); return true;}
            }
        }
        return false;
    }

    public int getIndexOfCell(int row, int col){
        if (row%2 == 0){
            if (col%2 == 0){ return row*4 + col/2; }
            else{ return -1; }
        }
        else{
            if (col%2 != 0){ return row *4 + col/2; }
            else{ return -1; }
        }
    }

    public boolean hasJumps(int row, int col){
        return getJumps(getIndexOfCell(row, col)).size()>0;
    }

    public void makeMove(Move tpMove){
        CheckersMove toMove = (CheckersMove) tpMove;
        if (toMove.getType() == 1){
            move(toMove.getA(), toMove.getB());
        }
        else if (toMove.getType()==3){
            while(toMove.getType()==3) {
                jump(toMove.getA(), toMove.getB(), toMove.getC());
                toMove = toMove.getNext();
            }
            jump(toMove.getA(), toMove.getB(), toMove.getC());
        }
        else{
            jump(toMove.getA(), toMove.getB(), toMove.getC());
        }
    }

    public Move getMove(){
        return new CheckersMove();
    }

    public boolean hasWon(int winner){
        int loser = (winner==1)?2:1;
        for(int i=0; i<32; i++){
            if (currentBoard[i].getPlayer()==loser && getMoves(i).size()>0){
                return false;
            }
        }
        return true;
    }

    public int evaluateBoard(){
        int score = 0;
        // player 1 starts at row 0 and is positive, player 2 starts at row 7 and is negative
        // starting heuristic
        // men-> 30, kings->45, +2 for every row closer man is to being kinged
        //+6 for every man in the back row
        boolean p1=false, p2=false;
        for(int i=0; i<32; i++){
            if (currentBoard[i].getPlayer() == 1){
                p1 = true;
                if (currentBoard[i].getType() == 1){ score += 60 + 2*(i/4); }
                else{ score += 90 + ncenter(i); }
            }
            else if (currentBoard[i].getPlayer() == 2){
                p2 = true;
                if (currentBoard[i].getType() == 1){ score -= 60 + 2*(7 - i/4); }
                else{ score -= 90 + ncenter(i); }
            }
        }
        for(int i=0; i<4; i++){
            if (currentBoard[i].getType() == 1) { score +=6; }
            if (currentBoard[31-i].getType() == 1){ score -= 6; }
        }
        if (p1 && !p2){
            return 100000000;
        }
        else if (!p1 && p2){
            return -100000000;
        }
        return score;
    }

    public ArrayList<Move> getPlayerMoves(int player){
        ArrayList<Move> tmp = new ArrayList<>();
        for(int i=0; i<32; i++){
            if (currentBoard[i].getPlayer()==player){tmp.addAll(getMoves(i));}
        }
        return tmp;
    }

    private boolean legalMove(int a, int b){
        if (b<0 || a<0 || currentBoard[a].getPlayer()==0 || !currentBoard[b].isNeighbor(a)) return false;
        if (currentBoard[a].getType()!=2){
            if (currentBoard[a].getPlayer()==1 && a>b){ return false; }
            else if (currentBoard[a].getPlayer()==2 && a<b){ return false; }
        }
        return (currentBoard[b].getPlayer() == 0);
    }

    private void move(int a, int b){

        currentBoard[b].setCell(currentBoard[a]);
        currentBoard[a].clear();

        if (currentBoard[b].getPlayer() == 1 && b/4 == 7){ currentBoard[b].setType(2); }
        else if (currentBoard[b].getPlayer() == 2 && b/4 == 0) {currentBoard[b].setType(2); }
    }

    private void jump(int a, int b, int c){
        currentBoard[c].setCell(currentBoard[a]);
        currentBoard[b].clear(); currentBoard[a].clear();
        if (currentBoard[c].getPlayer() == 1 && c/4 == 7){ currentBoard[c].setType(2); }
        else if (currentBoard[c].getPlayer() == 2 && c/4 == 0) {currentBoard[c].setType(2); }
    }

    private boolean diagnol(int a, int b, int c){
        for(int i=0; i<4; i++){
            if (currentBoard[a].getMove(i)==b && currentBoard[b].getMove(i)==c){ return true; }
        }
        return false;
    }

    private boolean legalJump(int a, int b, int c){
        if (b<0 || c<0 || a<0 || currentBoard[a].getPlayer()==0) return false;
        if (!diagnol(a, b, c)) return false;
        if (currentBoard[a].getType()!=2){
            if (currentBoard[a].getPlayer()==1 && a>b){ return false; }
            else if (currentBoard[a].getPlayer()==2 && a<b){ return false; }
        }
        return (currentBoard[b].getPlayer()!=currentBoard[a].getPlayer() && currentBoard[b].getPlayer()!=0 && currentBoard[c].getPlayer() == 0);
    }

    private ArrayList<CheckersMove> getJumps(int ind){
        ArrayList<CheckersMove> tmp = new ArrayList<>();
        CheckersMove tpmove;
        CheckersBoard tpBoard;
        for(int i=0; i<4; i++){
            if (currentBoard[ind].getMove(i)>0 && legalJump(ind, currentBoard[ind].getMove(i), currentBoard[currentBoard[ind].getMove(i)].getMove(i))){
                tpmove = new CheckersMove(ind, currentBoard[ind].getMove(i), currentBoard[currentBoard[ind].getMove(i)].getMove(i), 2);
                tpBoard = new CheckersBoard(currentBoard);
                tpBoard.makeMove(tpmove);
                for(CheckersMove j: tpBoard.getJumps(tpmove.getC())){
                    tmp.add(new CheckersMove(tpmove, j));
                }
                tmp.add(tpmove);
            }
        }
        return tmp;
    }

    private ArrayList<CheckersMove> getMoves(int ind){
        ArrayList<CheckersMove> tmp = new ArrayList<>();
        CheckersMove tpmove;

        if (ind<0 || ind>31) {return tmp; }
        for(int i=0; i<4; i++){
            if (legalMove(ind, currentBoard[ind].getMove(i))){
                tpmove = new CheckersMove(ind, currentBoard[ind].getMove(i), 1);
                tmp.add(tpmove);
            }
        }
        tmp.addAll(getJumps(ind));
        return tmp;
    }

    private int getColumnOfCell(int ind){
        return (ind%4*2)+(ind%2==0?0:1);
    }

    private int ncenter(int ind){
        return Math.min(Math.min(ind/4, 7-ind/4), Math.min(getColumnOfCell(ind), 7-getColumnOfCell(ind)));
    }

}
