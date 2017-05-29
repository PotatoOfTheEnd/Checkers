package com.example.arielle.checkers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;

public class blackjack extends AppCompatActivity {

    private Button hitButton;
    private Button passButton;
    private ImageView Cards;
    private boolean hasStarted = false;
    private ArrayList<Card> cards = new ArrayList<>();
    private int numCards = 0;
    private int current_total = 0;
    private Deck d = new Deck();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackjack);

        hitButton = (Button) findViewById(R.id.hitButton);
        passButton = (Button) findViewById(R.id.passButton);
        Cards = (ImageView) findViewById(R.id.Cards);


        hitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!hasStarted){
                    hasStarted = true;
                    current_total = 0;
                    for (int i = 0; i < 2; i++){
                        cards.add(d.getNewCard());
                        numCards++;
                        cards.get(cards.size()-1).updateTotal(current_total);
                        while (cards.get(0).equals(cards.get(1))){
                            cards.remove(1);
                            cards.add(d.getNewCard());
                        }
                    }


                }
                else{
                    numCards++;
                    cards.add(d.getNewCard());
                    cards.get(cards.size()-1).updateTotal(current_total);
                }
                String cCard = cards.get(cards.size()-1).toString();
//*
/*
                int [] rIDs = {R.mipmap.d_1, R.mipmap.d_2, R.mipmap.d_3,R.mipmap.d_4,R.mipmap.d_5,R.mipmap.d_6,R.mipmap.d_7,R.mipmap.d_8,R.mipmap.d_9
                        ,R.mipmap.d_10,R.mipmap.d_11,R.mipmap.d_12,R.mipmap.d_13};
                int val=0;
                for (int i = 0; i< rIDs.length; i++) {
                    Bitmap cardBMP = BitmapFactory.decodeResource(context.getResources(), rIDs[i]);
                    switch (i){
                        case 0:
                            tmpName="Ace";
                            val=1;
                            break;
                        case 1:
                            tmpName = "Two";
                            val=2;
                            break;
                        case 2:
                            tmpName = "Three";
                            val=3;
                            break;
                        case 3:
                            tmpName = "Four";
                            val=4;
                            break;
                        case 4:
                            tmpName = "Five";
                            val=5;
                            break;
                        case 5:
                            tmpName = "Six";
                            val=6;
                            break;
                        case 6:
                            tmpName = "Seven";
                            val=7;
                            break;
                        case 7:
                            tmpName = "Eight";
                            val=8;
                            break;
                        case 8:
                            tmpName = "Nine";
                            val=9;
                            break;
                        case 9:
                            tmpName = "Ten";
                            val=10;
                            break;
                        case 10:
                            tmpName = "Jack";
                            val=10;
                            break;
                        case 11:
                            tmpName = "Queen";
                            val=10;
                            break;
                        case 12:
                            tmpName = "King";
                            val=10;
                            break;

                    }
                    /*


                Cards.setImageResource(R.drawable.); //set to cCard toString value

*/
            }
        });

    }
}