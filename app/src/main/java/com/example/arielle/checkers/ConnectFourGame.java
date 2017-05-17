package com.example.arielle.checkers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import static android.widget.GridView.NO_STRETCH;

/**
 * Created by arielle on 08/05/2017.
 */

public class ConnectFourGame extends AppCompatActivity {
    private GridView mGridView;
    private ConnectFourBoard iAmBored;
    private boolean playerTurn;
    private int playerID;
    private void update(){
        mGridView.setAdapter(new ImageAdapter(this, iAmBored, 6, 7));
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iAmBored = new ConnectFourBoard();
        playerTurn = true;
        playerID = 0;
        setContentView(R.layout.activity_connect_four);
        mGridView = (GridView) findViewById(R.id.GVBoard);
        mGridView.setStretchMode(NO_STRETCH);
        update();
        Button mStartButton = (Button) findViewById(R.id.returnButton);
        mStartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(ConnectFourGame.this, StartPage.class);
                startActivity(i);
            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (playerTurn){
                    if (iAmBored.isLegalMove(position%7)){
                        playerTurn = false;
                        iAmBored.makeMove(position%7, 1);
                        update();
                        if (iAmBored.hasWon(1)){
                            Toast.makeText(ConnectFourGame.this, R.string.player_win, Toast.LENGTH_SHORT).show();
                            iAmBored = new ConnectFourBoard();
                            update();
                        }
                   //     ConnectFourBoard tpbored = (ConnectFourBoard) iAmBored.copy();
                      //  ConnectFourMove tpMove = new ConnectFourMove(2, position%7);
                    //    iAmBored.makeMove(tpMove);
                        iAmBored.makeMove(MinMax.getVal(iAmBored, 2, 5, -100000000, 100000000));
                        update();
                        if(iAmBored.hasWon(2)){
                            Toast.makeText(ConnectFourGame.this, R.string.computer_win, Toast.LENGTH_SHORT).show();
                            iAmBored = new ConnectFourBoard();
                            update();
                        }
                    //    Toast.makeText(ConnectFourGame.this, String.format("col%dplayer%d", tpMove.getColumn(), tpMove.getPlayer()), Toast.LENGTH_SHORT).show();

                        playerTurn = true;
                    }
                }

            }
        });
    }

}
