package com.example.arielle.checkers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.arielle.checkers.R.styleable.View;

/**
 * Created by arielle on 04/05/2017.
 */

public class StartPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        Button checkersButton = (Button) findViewById(R.id.checkersButton);
        checkersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartPage.this, CheckersGame.class);
                startActivity(i);
            }
        });
        Button connectFourButton = (Button) findViewById(R.id.connectfourButton);
        connectFourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartPage.this, ConnectFourGame.class);
                startActivity(i);
            }
        });
        Button blackjackButton = (Button) findViewById(R.id.blackjackButton);
        blackjackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartPage.this, blackjack.class);
                startActivity(i);
            }
        });
    }
}
