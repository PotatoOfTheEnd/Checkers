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
import android.widget.TextView;
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
    private TextView scoreShower;
    private int turnNumber = 0;


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

    private void addNewCard(Deck deck, ArrayList<Card> c, int nCards){
      //  TextView tv = (TextView) findViewById(R.id.scoreShower);
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
        nCards += 1;
        scoreShower.setText(String.valueOf(current_total));
    }

    private void reset(){
        hasStarted = false;
        current_total = 0;
        cStack.setImageResource(R.drawable.no_cards_down_v3);
        for (int j = cards.size()-1; j > 0; j--) {
            cards.remove(j);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackjack);

        rtnButton = (Button) findViewById(R.id.rtnButton);
        hitButton = (Button) findViewById(R.id.hitButton);
        passButton = (Button) findViewById(R.id.passButton);
        cStack = (ImageView) findViewById(R.id.cStack);
        scoreShower = (TextView) findViewById(R.id.scoreShower);

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
                turnNumber++;
                if (!hasStarted){
                    hasStarted = true;
                    if (numCards >30){
                        Deck d = new Deck();
                    }
                    current_total = 0;
                    for (int i = 0; i < 2; i++){
                        addNewCard(d, cards, numCards);
                        current_total += cards.get(0).getValue();
                    }


                }
                else{
                    addNewCard(d, cards, numCards);
                    current_total += cards.get(0).getValue();
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

                if ((current_total == 21) && (turnNumber == 1)){
                    Context context = getApplicationContext();
                    CharSequence text = "Blackjack!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    reset();
                }
                else if (current_total == 21){
                    Context context = getApplicationContext();
                    CharSequence text = "You win!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    reset();
                }
                else if (current_total > 21){
                    Context context = getApplicationContext();
                    CharSequence text = "You loose!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    reset();
                }
            }
        });

        passButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Card lastCard = d.getNewCard();
                if ((current_total + lastCard.getValue()) > 21){
                    Context context = getApplicationContext();
                    String text = "You Win! Next card was a " + (lastCard.toString()).replace("_", " ");
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    reset();
                }
                else{
                    Context context = getApplicationContext();
                    String text = "You loose! Next card was a " + (lastCard.toString()).replace("_", " ");
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    reset();
               }

            }
        });

    }
}