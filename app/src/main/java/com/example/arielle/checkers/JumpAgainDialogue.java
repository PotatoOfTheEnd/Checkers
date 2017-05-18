package com.example.arielle.checkers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by arielle on 30/04/2017.
 */

public class JumpAgainDialogue extends DialogFragment {
    public interface JumpDialogListener{
        void doJump(DialogFragment dialog);
        void doNotJump(DialogFragment dialog);
    }
    JumpDialogListener mListener;
    @Override
    public void onAttach(Context mContext){
        super.onAttach(mContext);
        mListener = (JumpDialogListener) mContext;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setMessage(R.string.jump_again).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                mListener.doJump(JumpAgainDialogue.this);
            }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                mListener.doNotJump(JumpAgainDialogue.this);
            }
        });
        return mBuilder.create();
    }
}
