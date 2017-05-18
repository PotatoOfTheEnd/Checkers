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

import static android.widget.GridView.NO_STRETCH;

/**
 * Created by arielle on 18/05/2017.
 */

public abstract class GridGame extends AppCompatActivity implements EndGameDialogue.EndGameDialogueListener{
    public void justEndIt(){
        playerID = (playerID+1)%2;
        resetBoard();
        updateScore();
        update();
        if (playerID==1){
            computerMove();
        }
    }
    TextView scoreKeeper;
    boolean playerTurn;
    int row, cols, computerScore, playerScore, playerID;
    Context mContext;
    GridView mGridView;
    Button mStartButton;
    abstract gameBoard getBoard();
    abstract void resetBoard();
    abstract void computerMove();
    void init(Context _mContext, int _row, int _cols) {
        mContext = _mContext; row=_row; cols=_cols;
        computerScore = 0; playerScore = 0; playerID = 0;
        mStartButton =  (Button) findViewById(R.id.returnButton);;
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, StartPage.class);
                startActivity(i);
            }
        });
        mGridView = (GridView) findViewById(R.id.GVBoard);
        mGridView.setStretchMode(NO_STRETCH);
        scoreKeeper = (TextView) findViewById(R.id.scoreTracker);
        update();
        updateScore();

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    void newGame(){
        DialogFragment dia = new EndGameDialogue();
        dia.show(getSupportFragmentManager(), "EndDialogueFragment");
    }
    void showMessage(int id){
        Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
    }

    void update(){
        mGridView.setAdapter(new ImageAdapter(this, getBoard(), row, cols));
    }
    void updateScore(){
        scoreKeeper.setText(String.format(getResources().getString(R.string.computer_score)+"%d "+getResources().getString(R.string.player_score)+"%d", computerScore, playerScore));
    }


}
