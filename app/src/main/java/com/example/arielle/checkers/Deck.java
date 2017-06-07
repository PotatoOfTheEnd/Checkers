package com.example.arielle.checkers;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Connor on 2017-05-24.
 */

public class Deck {

    private Card c;
    private ArrayList<Card> cList = new ArrayList<>();

    Deck(){
        for (int h = 0; h < 52; h++){
            for (int i = 0; i < 4; i++){
                for (int j = 1; j <= 13; j++){
                    cList.add(new Card(i,j));
                    cList.get(h).addImage();
                }
            }
        }


    }

    public Card getNewCard(){
        int rnd = new Random().nextInt(cList.size());
        Card n = cList.get(rnd);
        cList.remove(rnd);
        return n;
    }


}
