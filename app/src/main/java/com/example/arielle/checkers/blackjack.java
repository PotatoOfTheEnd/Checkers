package com.example.arielle.checkers;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class blackjack extends AppCompatActivity {

    private Button hitButton;
    private Button passButton;
    private Button rtnButton;
    private ImageView cStack;
    private boolean hasStarted = false;
    private ArrayList<Card> cards = new ArrayList<>();
    private int numCards = 0;
    private int current_total = 0;
    private int possible_total;
    private Deck d = new Deck();


    private int numAces(ArrayList<Card> cardArray){
        int nAce = 0;
        for (int i = 0; i < cardArray.size(); i++){
            if ((cardArray.get(i).toString().equals("ace_of_spades"))|| (cardArray.get(i).toString().equals("ace_of_hearts"))|| (cardArray.get(i).toString().equals("ace_of_diamonds"))||(cardArray.get(i).toString().equals("ace_of_clubs"))){
                nAce++;
            }
        }
        return nAce;
    }

    private boolean isAce(Card c){
        return (c.toString().equals("ace_of_spades")) || (c.toString().equals("ace_of_hearts")) || (c.toString().equals("ace_of_diamonds")) || (c.toString().equals("ace_of_clubs"));
    }

    private void addNewCard(Deck deck, ArrayList<Card> c, int total, int nCards){
        boolean goodCard = false;
        Card newCard = deck.getNewCard();
        while (!goodCard){
            if (c.contains(newCard)){
                newCard = deck.getNewCard();
            }
            else{
                c.add(0, newCard);
                goodCard = true;
            }
        }
        total += c.get(c.size()-1).getValue();
        nCards += 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackjack);

        rtnButton = (Button) findViewById(R.id.rtnButton);
        hitButton = (Button) findViewById(R.id.hitButton);
        passButton = (Button) findViewById(R.id.passButton);
        cStack = (ImageView) findViewById(R.id.cStack);


        rtnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(blackjack.this, StartPage.class);
                startActivity(i);
            }
        });

        hitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!hasStarted){
                    hasStarted = true;
                    if (numCards >30){
                        Deck d = new Deck();
                    }
                    current_total = 0;
                    for (int i = 0; i < 2; i++){
                        addNewCard(d, cards, current_total, numCards);
                    }


                }
                else{
                    addNewCard(d, cards, current_total, numCards);
                }
                Card cCard = cards.get(0);
                cStack.setImageBitmap(cCard.getImage());
                if (numAces(cards) == 1){
                    for (int i = 0; i < cards.size(); i++){
                        if (isAce(cards.get(i))){
                            cards.get(i).updateValue(11);
                        }
                    }
                }
                for (int i = 0; i < cards.size(); i++){
                    while ((numAces(cards) > 0) && (current_total > 21)){
                        if (isAce(cards.get(i))) {
                            cards.get(i).updateValue(1);
                        }
                    }
                }







            }
        });

        passButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Card lastCard = d.getNewCard();
                if ((current_total + lastCard.getValue()) > 21){
                    Context context = getApplicationContext();
                    CharSequence text = "You win!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "You loose!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();;
               }
            }
        });

    }
}