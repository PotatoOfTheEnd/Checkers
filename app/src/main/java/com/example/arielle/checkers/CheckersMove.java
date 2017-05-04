package com.example.arielle.checkers;

/**
 * Created by arielle on 13/04/2017.
 */

public class CheckersMove {
    private int a, b, c, type, score;
    private CheckersMove next;
    CheckersMove(int _a, int _b, int _type){
        a=_a; b=_b; c=-1; type = _type; score = 0;
    }
    CheckersMove(int _a, int _b, int _c, int _type){
        a=_a; b=_b; c=_c; type = _type; score = 0;
    }
    CheckersMove(CheckersMove nm, CheckersMove _next){
        a = nm.getA(); b=nm.getB(); c=nm.getC(); type = 3; score = 0; next=_next;
    }
    CheckersMove(CheckersMove nm){
        a = nm.getA(); b=nm.getB(); c=nm.getC(); type = nm.getType(); score = nm.getScore();
    }
    public void setScore(int _score){
        score = _score;
    }
    public int getScore(){
        return score;
    }
    public int getA(){
        return a;
    }
    public int getB(){
        return b;
    }
    public int getC(){
        return c;
    }
    public int getType(){
        return type;
    }
    public CheckersMove getNext() {return next; }

}
