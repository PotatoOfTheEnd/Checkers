package com.example.arielle.checkers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridView;

import static android.widget.GridView.NO_STRETCH;

/**
 * Created by arielle on 08/05/2017.
 */

public class ConnectFourGame extends AppCompatActivity {
    private GridView mGridView;
    private ConnectFourBoard iAmBored;
    private boolean playerTurn;
    private int playerID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iAmBored = new ConnectFourBoard();
        playerTurn = true;
        playerID = 0;
        setContentView(R.layout.activity_connect_four);
        mGridView = (GridView) findViewById(R.id.GVBoard);
        mGridView.setStretchMode(NO_STRETCH);
        mGridView.setAdapter(new ImageAdapter(this, iAmBored, 6, 7));
        Button mStartButton = (Button) findViewById(R.id.returnButton);
    }

}
