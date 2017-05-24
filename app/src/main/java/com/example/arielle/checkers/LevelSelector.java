package com.example.arielle.checkers;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by arielle on 18/05/2017.
 */

public class LevelSelector extends DialogFragment {
    public interface LevelSelectorListener {
        void setLevel(int level);
    }

    LevelSelectorListener levelSelectorListener;

    @Override
    public void onAttach(Context isIrrelevant) {
        super.onAttach(isIrrelevant);
        setCancelable(false);
        levelSelectorListener = (LevelSelectorListener) isIrrelevant;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(R.string.level_choice).setPositiveButton(R.string.easy,
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        levelSelectorListener.setLevel(2);
                    }
                }).setNeutralButton(R.string.medium,
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        levelSelectorListener.setLevel(4);
                    }
                }).setNegativeButton(R.string.hard,
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        levelSelectorListener.setLevel(6);
                    }
                });
        return alertDialogBuilder.create();
    }
}
