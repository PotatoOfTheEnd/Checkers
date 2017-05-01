package com.example.arielle.checkers;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by arielle on 30/04/2017.
 */

public class JumpAgainDialogue extends DialogFragment {
    public interface JumpDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    JumpDialogListener mListener;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mListener = (JumpDialogListener) activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement JumpDialogListner");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.jump_again).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                mListener.onDialogPositiveClick(JumpAgainDialogue.this);
            }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                mListener.onDialogNegativeClick(JumpAgainDialogue.this);
            }
        });
        return builder.create();
    }
}
