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
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        Button mCheckersButton = (Button) findViewById(R.id.checkersButton);
        mCheckersButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(StartPage.this, CheckersGame.class);
                startActivity(i);
            }
        });
        Button mConnectFourButton = (Button) findViewById(R.id.connectfourButton);
        mConnectFourButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(StartPage.this, ConnectFourGame.class);
                startActivity(i);
            }
        });
    }
}
