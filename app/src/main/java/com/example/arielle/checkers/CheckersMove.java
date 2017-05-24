package com.example.arielle.checkers;

/**
 * Created by arielle on 13/04/2017.
 */

public class CheckersMove implements Move {
    private int a, b, c, type, score;
    private boolean defaultMove;
    private CheckersMove next;

    CheckersMove(int a, int b, int type) {
        this.a = a;
        this.b = b;
        c = -1;
        this.type = type;
        score = 0;
        defaultMove = false;
    }

    CheckersMove(int a, int b, int c, int type) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.type = type;
        score = 0;
        defaultMove = false;
    }

    CheckersMove(CheckersMove nm, CheckersMove next) {
        a = nm.getA();
        b = nm.getB();
        c = nm.getC();
        type = 3;
        score = 0;
        this.next = next;
        defaultMove = false;
    }

    CheckersMove(CheckersMove nm) {
        a = nm.getA();
        b = nm.getB();
        c = nm.getC();
        type = nm.getType();
        score = nm.getScore();
        defaultMove = false;
    }

    CheckersMove() {
        score = 0;
        defaultMove = true;
    }

    public boolean isDefault() {
        return defaultMove;
    }

    public void setScore(int _score) {
        score = _score;
    }

    public int getScore() {
        return score;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public int getType() {
        return type;
    }

    public CheckersMove getNext() {
        return next;
    }

}
