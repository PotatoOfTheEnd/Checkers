package com.example.arielle.checkers;

/**
 * Created by arielle on 04/05/2017.
 */

public interface Move {
    int getScore();

    void setScore(int _score);

    boolean isDefault();
}
