package com.example.arielle.checkers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by arielle on 18/05/2017.
 */

public abstract class GridGame extends AppCompatActivity
        implements EndGameDialogue.EndGameDialogueListener, LevelSelector.LevelSelectorListener {

    public void setLevel(int level) {
        ai = new MinMax(level);
        this.level = level;
    }

    public void justEndIt() {
        playerID = (playerID + 1) % 2;
        gameOver = true;
    }

    public void newGame() {
        resetBoard();
        updateScore();
        update();
        if (playerID == 1) {
            computerMove();
        }
        gameOver = false;
    }

    protected TextView scoreKeeper;
    protected boolean playerTurn, gameOver;
    protected int row, cols, computerScore, playerScore, playerID, level;
    protected Context context;
    protected GridView gridView;
    protected Button startButton;
    protected MinMax ai;

    abstract GameBoard getBoard();

    abstract void resetBoard();

    abstract void computerMove();

    public void init(Context currContext, int row, int cols) {
        context = currContext;
        this.row = row;
        this.cols = cols;
        computerScore = 0;
        playerScore = 0;
        playerID = 0;
        gameOver = false;
        startButton = (Button) findViewById(R.id.returnButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, StartPage.class);
                startActivity(i);
            }
        });
        gridView = (GridView) findViewById(R.id.GVBoard);
        scoreKeeper = (TextView) findViewById(R.id.scoreTracker);
        update();
        updateScore();
        DialogFragment dialog = new LevelSelector();
        dialog.show(getSupportFragmentManager(), "LevelSelectFragment");

    }

    public void gameOver() {
        DialogFragment dia = new EndGameDialogue();
        dia.show(getSupportFragmentManager(), "EndDialogueFragment");
    }

    public void showMessage(int id) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }

    public void update() {
        gridView.setAdapter(new ImageAdapter(this, getBoard(), row, cols));
    }

    public void updateScore() {
        scoreKeeper.setText(String.format(getResources().getString(R.string.computer_score) + " %d " + getResources().getString(R.string.player_score) + " %d", computerScore, playerScore));
    }


}
