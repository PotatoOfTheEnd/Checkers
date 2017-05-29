package com.example.arielle.checkers;

import java.util.Random;

/**
 * Created by Connor on 2017-05-24.
 */

public class Deck {

    private Card c;
    private Card[] cList = new Card[52];

    Deck(){
        for (int h = 0; h < 52; h++){
            for (int i = 0; i < 4; i++){
                for (int j = 1; j <= 13; j++){
                   // cList[h] = new Card(i,j,);
                }
            }
        }


    }

    public Card getNewCard(){
        int rnd = new Random().nextInt(cList.length);
        return cList[rnd];
    }

    public String toString(){
        String suit;
        String value;

        if (c.getSuite() == 0){
            suit = "spades";
        }
        else if (c.getSuite() == 1){
            suit = "hearts";
        }
        else if (c.getSuite() == 2) {
            suit = "diamonds";
        }
        else{
            suit = "clubs";
        }

        if (c.getValue()== 1){
            value = "ace";
        }
        else if (c.getValue() == 11){
            value = "jack";
        }
        else if (c.getValue() == 12){
            value = "queen";
        }
        else if (c.getValue() == 13){
            value = "king";
        }
        else{
            value = String.valueOf(c.getValue());
        }

        return (value + "_of_" + suit);
    }


}
