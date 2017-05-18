package com.example.arielle.checkers;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.GridView.NO_STRETCH;

/**
 * Created by arielle on 08/05/2017.
 */

public class ConnectFourGame extends GridGame {
    private ConnectFourBoard iAmBored;
    void resetBoard(){
        iAmBored = new ConnectFourBoard();
    }
    gameBoard getBoard(){
        return iAmBored;
    }
    void computerMove(){
        iAmBored.makeMove(MinMax.getVal(iAmBored, 2, 5, -100000000, 100000000));
        update();
        if (iAmBored.hasWon(2)) {
            showMessage(R.string.computer_win);
            computerScore++;
            newGame();
        }
        else if (iAmBored.isTie()){
            showMessage(R.string.tie);
            newGame();
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_four);
        iAmBored = new ConnectFourBoard();
        init(ConnectFourGame.this, 6, 7);
        playerTurn = true;
        mContext = ConnectFourGame.this;
        update();
        updateScore();


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (playerTurn){
                    if (iAmBored.isLegalMove(position%7)){
                        playerTurn = false;
                        iAmBored.makeMove(position%7, 1);
                        update();
                        if (iAmBored.hasWon(1)){
                            showMessage(R.string.player_win);
                            playerScore++;
                            newGame();
                        }
                        else if (iAmBored.isTie()){
                            showMessage(R.string.tie);
                            newGame();
                        }
                        else {
                            computerMove();
                        }
                        playerTurn = true;
                    }
                }

            }
        });
    }

}
