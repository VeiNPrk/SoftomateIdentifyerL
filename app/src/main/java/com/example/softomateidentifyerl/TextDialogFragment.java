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
    final String LOG_TAG = "myLogs";
    static String mMessageToDisplay="";
    //DescribeDialogListener mListener;
    EditText etDescribe = null;
    int type = 0;

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mListener = (DescribeDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }*/
	
	public static final String TAG = "NotificationDialogFragment";


    public static TextDialogFragment newInstance(String message) {

        TextDialogFragment textDialog = new TextDialogFragment();
        mMessageToDisplay = message;
        return textDialog;
    }

/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;
        a=(Activity) context;
        try {
            // Instantiate the MyDialogListener so we can send events to the host
            mListener = (DescribeDialogListener) a;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(a.toString() + " must implement MyDialogListener");
        }
    }*/

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //View view = inflater.inflate(R.layout.maket_describe_dialog, null);
        
		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
			.setTitle(getString(R.string.dlg_tittle))
            .setPositiveButton(R.string.dlg_ok,new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                        //succesRequest(requestUsers.get(position).getId());
                        //Toast.makeText(getContext(),"id="+id+" position="+position, Toast.LENGTH_LONG).show();
                }
            })
            .setMessage(mMessageToDisplay);
        /*adb.create().show();
		
		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.dialog_send_tittle))
                .setView(view)
                .setPositiveButton(getString(R.string.dialog_send_yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String idDescribe = etDescribe.getText().toString();
                        mListener.onYesClicked(DescribeDialogFragment.this, idDescribe);
                    }
                })
                .setNegativeButton(getString(R.string.dialog_send_no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onNoClicked(DescribeDialogFragment.this);
                    }
                })
                .setMessage(getString(R.string.dialog_text_edit));*/
        return adb.create();
    }
/*
    public interface DescribeDialogListener {
        public void onYesClicked(DialogFragment dialog, String data);
        public void onNoClicked(DialogFragment dialog);
    }
*/
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "TextDialog : onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "TextDialog : onCancel");
    }
}
