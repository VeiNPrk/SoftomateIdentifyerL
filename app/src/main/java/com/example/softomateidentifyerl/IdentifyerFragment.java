package com.example.softomateidentifyerl;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by VNPrk on 27.10.2018.
 */

public class IdentifyerFragment extends Fragment implements LoaderManager.LoaderCallbacks<Bundle>, IdentifyerContractor.View,
    TextDialogFragment.TextDialogListener{

    public final String TAG = getClass().getSimpleName();
    private static final int MSG_SHOW_DIALOG = 111;
    private static final int MSG_SHOW_ERROR_DIALOG = 112;
    private static final String SAVE_STATE_TEXT = "state_text";
    private static final String SAVE_STATE_LOADST = "state_loader";
    TextView tvText = null;
	FloatingActionButton fabIdent;
    View view=null;
    String lang="";
    String error="";
    boolean loaderIsStart = false;
    IdentifyerPresenter presenter=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_identifyer, container, false);
        presenter = new IdentifyerPresenter(this);
        initViews();
        if(savedInstanceState != null){
            String text = savedInstanceState.getString(SAVE_STATE_TEXT);
            loaderIsStart = savedInstanceState.getBoolean(SAVE_STATE_LOADST);
            tvText.setText(text);
            if(loaderIsStart)
            {
                Bundle bundle = new Bundle();
                bundle.putString(IdentifyerLoader.KEY_IDENT_TEXT, text);
                getLoaderManager().restartLoader(IdentifyerLoader.IDENT_LOADER_ID, bundle, this);
            }
        }
        getLoaderManager();
        Log.d(TAG, "onCreateView");

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_STATE_TEXT, tvText.getText().toString());
        outState.putBoolean(SAVE_STATE_LOADST, loaderIsStart);
        Log.d(TAG, "onSaveInstanceState");
    }

    private void initViews(){
        tvText = view.findViewById(R.id.tv_text);
		fabIdent = view.findViewById(R.id.fab_add);
        fabIdent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFabWasClicked();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void isShowDialog(String lang) {
        this.lang=lang;
        handler.sendEmptyMessage(MSG_SHOW_DIALOG);
    }

    @Override
    public void isShowErrorDialog(String error) {
        this.error=error;
        handler.sendEmptyMessage(MSG_SHOW_ERROR_DIALOG);
    }

    public void showDialog(String lang) {
        String messageDialog = String.format("%s %s",getString(R.string.dlg_message), lang );
        TextDialogFragment dialog = TextDialogFragment.newInstance(messageDialog);
        dialog.setTargetFragment(this,0);
        dialog.show(getFragmentManager(), TextDialogFragment.TAG);
    }

    public void setMsgShowErrorDialog(String error) {
        String messageDialog = String.format("%s %s",getString(R.string.dlg_error_message), error );
        TextDialogFragment dialog = TextDialogFragment.newInstance(messageDialog);
        dialog.setTargetFragment(this,0);
        dialog.show(getFragmentManager(), TextDialogFragment.TAG);
    }

    @Override
    public String getIdentText() {
        return tvText.getText().toString();
    }

    @Override
    public void clearTvIdentText() {
        tvText.setText("");
    }
	
	public void initIdentifyer(String text){
		Bundle bundle = new Bundle();
        bundle.putString(IdentifyerLoader.KEY_IDENT_TEXT, text);
        getLoaderManager().initLoader(IdentifyerLoader.IDENT_LOADER_ID, bundle, this);
	}
	
	@Override
    public Loader<Bundle> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        loaderIsStart=true;
		return new IdentifyerLoader(view.getContext(), args);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Bundle> loader, Bundle data) {
        int id = loader.getId();
        presenter.onLoadFinished(data);
        getLoaderManager().destroyLoader(id);
        loaderIsStart=false;
        Log.d(TAG, "onLoadFinished");
    }

    @Override
    public void onLoaderReset(Loader<Bundle> loader) {
        Log.d(TAG, "onLoaderReset");
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == MSG_SHOW_DIALOG) {
                showDialog(lang);
            }
            else if(msg.what == MSG_SHOW_ERROR_DIALOG) {
                setMsgShowErrorDialog(error);
            }
        }
    };

    @Override
    public void onYesClicked() {
        presenter.dialogShowDone();
    }
}
