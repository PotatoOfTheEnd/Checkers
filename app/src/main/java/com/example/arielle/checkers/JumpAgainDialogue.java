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
    public interface JumpDialogListener {
        void doJump();

        void doNotJump();
    }

    JumpDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setCancelable(false);
        listener = (JumpDialogListener) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.jump_again).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listener.doJump();
            }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listener.doNotJump();
            }
        });
        return builder.create();
    }
}
