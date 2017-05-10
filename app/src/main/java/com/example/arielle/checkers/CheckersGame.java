package com.example.arielle.checkers;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import static android.widget.GridView.NO_STRETCH;

public class CheckersGame extends AppCompatActivity implements JumpAgainDialogue.JumpDialogListener{
    private GridView mGridView;
    private CheckersBoard boredom;
    private boolean chainJump;
    private boolean playerTurn, fclick;
    private int pvrow, pvcol, crow, ccol;
    private int playerId;
    private void update(){
        mGridView.setAdapter(new ImageAdapter(this, boredom, 8, 8));
    }
    public void showJumpDialog(){
        DialogFragment dialog = new JumpAgainDialogue();
        dialog.show(getSupportFragmentManager(), "JumpDialogFragment");
    }
    @Override
    public void onDialogPositiveClick(DialogFragment dialog){
        chainJump = true;
        fclick = true;
        pvrow=crow; pvcol=ccol;
    }
    @Override
    public void onDialogNegativeClick(DialogFragment dialog){
        chainJump = false;
        fclick = false;
        cpuMove();
    }
    private void newGame(){
        playerId = (playerId+1)%2;
        boredom = new CheckersBoard();
        if (playerId==1){
            boredom.makeMove(MinMax.getVal(boredom, 2, 6, -100000000, 100000000));
        }
    }
    private void cpuMove(){
        CheckersBoard tpb = boredom.copy();
        CheckersMove tpMove = (CheckersMove) MinMax.getVal(tpb, 2, 6, -100000000, 100000000);
        if (tpMove.getA() != tpMove.getB()) {
            boredom.makeMove(tpMove);
        }
        int tpval = boredom.evaluateBoard();
        boolean player1move = true;
        if (tpMove.getA() == tpMove.getB() || tpval == 100000000) {
            Toast.makeText(CheckersGame.this, R.string.player_win, Toast.LENGTH_SHORT).show();
            newGame();
        } else if (boredom.hasWon(2)) {
            Toast.makeText(CheckersGame.this, R.string.computer_win, Toast.LENGTH_SHORT).show();
            newGame();
        }
        update();
    }
    //does this work?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boredom = new CheckersBoard();
        playerTurn = true; fclick = false;
        playerId = 0;
        setContentView(R.layout.activity_checkers_game);
        mGridView = (GridView) findViewById(R.id.GVBoard);
        mGridView.setStretchMode(NO_STRETCH);
        update();
        chainJump = false;
        Button mStartButton = (Button) findViewById(R.id.returnButton);
        mStartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(CheckersGame.this, StartPage.class);
                startActivity(i);
            }

        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                if (playerTurn){
                    if (fclick){
                        crow=7-position/8; ccol=position%8;
                        if (chainJump){
                            if (boredom.isLegalJump(pvrow, pvcol, crow, ccol, 1)){
                                boredom.movePiece(pvrow, pvcol, crow, ccol);
                                update();
                                if (boredom.hasJumps(crow, ccol)){
                                    showJumpDialog();
                                }
                                else{
                                    chainJump = false;
                                    fclick = false;
                                    cpuMove();
                                }
                            }
                        }
                        else if (boredom.isLegalPlay(pvrow, pvcol, crow, ccol, 1)) {
                            playerTurn = false;
                            boolean isJump = boredom.movePiece(pvrow, pvcol, crow, ccol);
                            update();
                            if (isJump && boredom.hasJumps(crow, ccol)){
                                showJumpDialog();
                            }
                            else{
                                fclick = false;
                                cpuMove();
                            }
                            playerTurn = true;
                        }
                        else{
                            pvrow=7-position/8; pvcol=position%8;
                        }
                    }
                    else{
                        pvrow=7-position/8; pvcol=position%8;
                        fclick=true;
                    }

                }
            }
        });
    }
}