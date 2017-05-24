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

public class EndGameDialogue extends DialogFragment {
    public interface EndGameDialogueListener {
        void justEndIt();
    }

    EndGameDialogueListener listenerOfTheEnd;

    @Override
    public void onAttach(Context noContext) {
        super.onAttach(noContext);
        listenerOfTheEnd = (EndGameDialogueListener) noContext;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder epicBeaver = new AlertDialog.Builder(getActivity());
        epicBeaver.setMessage(R.string.game_over).setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface namelessDialogue, int id) {
                listenerOfTheEnd.justEndIt();
            }

        });
        return epicBeaver.create();
    }
}
