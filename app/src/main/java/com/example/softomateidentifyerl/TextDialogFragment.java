package com.example.softomateidentifyerl;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


/**
 * Created by VNPrk on 22.10.2018.
 */

public class TextDialogFragment extends DialogFragment {
    public static final String TAG = "TextDialogFragment";
    final String LOG_TAG = "myLogs";
    static String mMessageToDisplay="";
    static TextDialogListener mListener;
    EditText etDescribe = null;
    int type = 0;

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
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //View view = inflater.inflate(R.layout.maket_describe_dialog, null);
        
		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
			.setTitle(getString(R.string.dlg_tittle))
            .setPositiveButton(R.string.dlg_ok,new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    mListener.onYesClicked();
                        //succesRequest(requestUsers.get(position).getId());
                        //Toast.makeText(getContext(),"id="+id+" position="+position, Toast.LENGTH_LONG).show();
                }
            })
            .setMessage(mMessageToDisplay);

        return adb.create();
    }

    public interface TextDialogListener {
        public void onYesClicked();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "TextDialog : onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "TextDialog : onCancel");
    }
}
