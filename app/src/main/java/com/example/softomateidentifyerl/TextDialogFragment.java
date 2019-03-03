package com.example.softomateidentifyerl;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

public class TextDialogFragment extends DialogFragment {
    public static final String TAG = "TextDialogFragment";
    final String LOG_TAG = "myLogs";
    static String mMessageToDisplay="";
    static TextDialogListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mListener = (TextDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    public static TextDialogFragment newInstance(String message) {

        TextDialogFragment textDialog = new TextDialogFragment();
        mMessageToDisplay = message;
        return textDialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //LayoutInflater inflater = getActivity().getLayoutInflater();
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
			.setTitle(getString(R.string.dlg_tittle))
            .setPositiveButton(R.string.dlg_ok,new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    mListener.onYesClicked();
                }
            })
            .setMessage(mMessageToDisplay);

        return adb.create();
    }

    public interface TextDialogListener {
        public void onYesClicked();
    }

    public void onDismiss(DialogInterface dialog) {
        mListener.onYesClicked();
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "TextDialog : onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "TextDialog : onCancel");
    }
}
