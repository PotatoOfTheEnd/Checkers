package com.example.arielle.checkers;

import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class CheckersGame extends GridGame implements JumpAgainDialogue.JumpDialogListener {
    private CheckersBoard boredom;
    private boolean chainJump;
    private boolean fclick;
    private int pvrow, pvcol, crow, ccol, lastJump, moveCounter;

    GameBoard getBoard() {
        return boredom;
    }

    void resetBoard() {
        boredom = new CheckersBoard();
    }

    public void showJumpDialog() {
        DialogFragment dialog = new JumpAgainDialogue();
        dialog.show(getSupportFragmentManager(), "JumpDialogFragment");
    }

    @Override
    public void doJump() {
        chainJump = true;
        fclick = true;
        pvrow = crow;
        pvcol = ccol;
    }

    @Override
    public void doNotJump() {
        chainJump = false;
        fclick = false;
        computerMove();
    }

    void computerMove() {
        moveCounter++;
        CheckersBoard tpb = boredom.copy();
        CheckersMove tpMove = (CheckersMove) ai.getVal(tpb, 2, level, -100000000, 100000000);
        if (tpMove.getA() != tpMove.getB()) {
            if (tpMove.getType() == 2 || tpMove.getType() == 3) {
                lastJump = moveCounter;
            }
            boredom.makeMove(tpMove);
        }
        boolean player1move = true;
        if (tpMove.getA() == tpMove.getB() || boredom.hasWon(1)) {
            showMessage(R.string.player_win);
            playerScore++;
            lastJump = -1;
            moveCounter = 0;
            gameOver();
        } else if (boredom.hasWon(2)) {
            showMessage(R.string.computer_win);
            computerScore++;
            gameOver();
        }
        update();
    }

    //does this work?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boredom = new CheckersBoard();
        CheckersBoard.genVals();
        setContentView(R.layout.activity_checkers_game);
        init(CheckersGame.this, 8, 8);
        playerTurn = true;
        fclick = false;
        update();
        chainJump = false;
        lastJump = 0;
        moveCounter = 0;
        Button drawButton = (Button) findViewById(R.id.drawButton);
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moveCounter - lastJump >= 30) {
                    showMessage(R.string.tie);
                    gameOver();
                } else {
                    showMessage(R.string.draw_rejected);
                }
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (gameOver) {
                    newGame();
                } else if (playerTurn) {
                    if (fclick) {
                        moveCounter++;
                        crow = 7 - position / 8;
                        ccol = position % 8;
                        if (chainJump) {
                            if (boredom.isLegalJump(pvrow, pvcol, crow, ccol, 1)) {
                                lastJump = moveCounter;
                                boredom.movePiece(pvrow, pvcol, crow, ccol);
                                update();
                                if (boredom.hasJumps(crow, ccol)) {
                                    showJumpDialog();
                                } else {
                                    chainJump = false;
                                    fclick = false;
                                    computerMove();
                                }
                            }
                        } else if (boredom.isLegalPlay(pvrow, pvcol, crow, ccol, 1)) {
                            playerTurn = false;
                            boolean isJump = boredom.movePiece(pvrow, pvcol, crow, ccol);
                            update();
                            if (isJump && boredom.hasJumps(crow, ccol)) {
                                showJumpDialog();
                            } else {
                                fclick = false;
                                computerMove();
                            }
                            playerTurn = true;
                        } else {
                            pvrow = 7 - position / 8;
                            pvcol = position % 8;
                        }
                    } else {
                        pvrow = 7 - position / 8;
                        pvcol = position % 8;
                        fclick = true;
                    }

                }
            }
        });
    }
}