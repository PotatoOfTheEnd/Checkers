package com.example.arielle.checkers;

/**
 * Created by arielle on 04/05/2017.
 */

public interface Move {
    public int getScore();
    public void setScore(int _score);
    public boolean isDefault();
}
