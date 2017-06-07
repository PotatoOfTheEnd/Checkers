package com.example.arielle.checkers;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Connor on 2017-05-24.
 */

public class Deck {

    private Card c;
    private ArrayList<Card> cList = new ArrayList<>();
    private  int h = 0;

    Deck(){
            for (int i = 0; i < 4; i++){
                for (int j = 1; j <= 13; j++){
                    Card temp = new Card(i,j);
                    cList.add(h, temp.addImage());
                    if (cList.get(h).getValue() > 10){
                        cList.get(h).updateValue(10);
                    }
                    h++;
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
