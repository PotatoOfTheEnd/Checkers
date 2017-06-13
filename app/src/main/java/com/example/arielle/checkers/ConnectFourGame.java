package com.example.arielle.checkers;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by arielle on 08/05/2017.
 */

public class ConnectFourGame extends GridGame {
    private ConnectFourBoard iAmBored;

    void resetBoard() {
        iAmBored = new ConnectFourBoard();
    }

    GameBoard getBoard() {
        return iAmBored;
    }

    void computerMove() {
        iAmBored.makeMove(ai.getVal(iAmBored, 2, level, -100000000, 100000000));
        update();
        if (iAmBored.hasWon(2)) {
            showMessage(R.string.computer_win);
            computerScore++;
            gameOver();
        } else if (iAmBored.isTie()) {
            showMessage(R.string.tie);
            gameOver();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_four);
        ConnectFourBoard.genVals();
        iAmBored = new ConnectFourBoard();
        init(ConnectFourGame.this, 6, 7);
        playerTurn = true;
        update();
        updateScore();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (gameOver) {
                    newGame();
                } else if (playerTurn) {
                    if (iAmBored.isLegalMove(position % 7)) {
                        playerTurn = false;
                        iAmBored.makeMove(position % 7, 1);
                        update();
                        if (iAmBored.hasWon(1)) {
                            showMessage(R.string.player_win);
                            playerScore++;
                            gameOver();
                        } else if (iAmBored.isTie()) {
                            showMessage(R.string.tie);
                            gameOver();
                        } else {
                            computerMove();
                        }
                        playerTurn = true;
                    }
                }

            }
        });
    }

}
