package com.example.arielle.checkers;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import static android.widget.GridView.NO_STRETCH;

public class CheckersGame extends AppCompatActivity implements JumpAgainDialogue.JumpDialogListener{
    GridView mGridView;
    boardState boredom;
    private boolean chainJump;
    boolean playerTurn, fclick;
    int pvrow, pvcol, crow, ccol;
    void update(){
        mGridView.setAdapter(new ImageAdapter(this, boredom));
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
    public void cpuMove(){
        boardState tpb = new boardState(boredom.getCurrentBoard());
        Move tpMove = MinMax.getVal(tpb, 2, 6, -100000000, 100000000);
        if (tpMove.getA() != tpMove.getB()) {
            boredom.makeMove(tpMove);
        }
        int tpval = boredom.evaluateBoard();
        boolean player1move = true;
        for (int i = 0; i < 32; i++) {
            if (boredom.getCell(i).getPlayer() == 1 && boredom.getMoves(i).size() > 0) {
                player1move = false;
                break;
            }
        }
        if (tpMove.getA() == tpMove.getB() || tpval == 100000000) {
            boredom = new boardState();
            Toast.makeText(CheckersGame.this, R.string.player_win, Toast.LENGTH_SHORT).show();
        } else if (tpval == -100000000 || player1move) {
            boredom = new boardState();
            Toast.makeText(CheckersGame.this, R.string.computer_win, Toast.LENGTH_SHORT).show();
        }
        update();
    }
    //does this work?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boredom = new boardState();
        playerTurn = true; fclick = false;
        setContentView(R.layout.activity_checkers_game);
        mGridView = (GridView) findViewById(R.id.GVBoard);
        mGridView.setStretchMode(NO_STRETCH);
        mGridView.setAdapter(new ImageAdapter(this, boredom));
        chainJump = false;
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                if (playerTurn){
                    if (fclick){
                        crow=7-position/8; ccol=position%8;
                        if (chainJump){
                            if (boredom.isLegalJump(pvrow, pvcol, crow, ccol, 1)){
                                boredom.movePiece(pvrow, pvcol, crow, ccol);
                                update();
                                if (boredom.getJumps(boredom.getIndexOfCell(crow, ccol)).size()>0){
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
                            if (isJump && boredom.getJumps(boredom.getIndexOfCell(crow, ccol)).size()>0){
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
