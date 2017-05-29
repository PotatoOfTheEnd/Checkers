package com.example.arielle.checkers;

import android.graphics.Bitmap;

/**
 * Created by Connor on 2017-05-24.
 */

public class Card {

    private int suit;
    private int value;
    private Bitmap image;

    Card(int suite, int value, Bitmap image){
        this.suit = suite;
        this.value = value;
        this.image = image;

    }

    public Bitmap getImage(){
        return image;
    }

    public int getSuite(){
        return this.suit;
    }

    public int getValue(){
        return this.value;
    }

    public int compareCards(Card c){
        if ((c.getSuite() == this.suit) && (c.getValue() == this.value)){
            return -1;
        }
        else{
            return 0;
        }
    }

    public void updateTotal(int i){
        if (this.getValue() < 11){
            i+=this.getValue();
        }
        else{
            i+=10;
        }
    }


}
