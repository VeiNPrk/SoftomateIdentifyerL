package com.example.softomateidentifyerl;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * Created by VNPrk on 27.10.2018.
 */

public class IdentifyerFragment extends Fragment implements LoaderManager.LoaderCallbacks<TextClass>, IdentifyerContractor.View{

    private static final int MSG_SHOW_DIALOG = 111;
    private static final String SAVE_STATE_TEXT = "state_text";
    TextView tvText = null;
	FloatingActionButton fabIdent;
    View view=null;
    String lang="";
    IdentifyerPresenter presenter=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment", "onCreate");
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
            tvText.setText(text);
        }
        Log.d("Fragment", "onCreateView");
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_STATE_TEXT, tvText.getText().toString());
        Log.d("Fragment", "onSaveInstanceState");
    }

    private void initViews(){
        tvText = (TextView) view.findViewById(R.id.tv_text);
		fabIdent = (FloatingActionButton)view.findViewById(R.id.fab_add);
        //progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        fabIdent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFabWasClicked();
            }
        });
        //progressBar=(ProgressBar)view.findViewById(R.id.progressBar2);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
/*private ContentObserver observer = new ContentObserver(new Handler()){
        @Override
        public void onChange(boolean selfChange){
            super.onChange(selfChange);
            refreshData();
        }
    };*/


    @Override
    public void isShowDialog(String lang) {
        this.lang=lang;
        handler.sendEmptyMessage(MSG_SHOW_DIALOG);
    }

    public void showDialog(String lang) {
        String messageDialog = String.format("%s %s",getString(R.string.dlg_message), lang );
        TextDialogFragment dialog = TextDialogFragment.newInstance(messageDialog);
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
    public Loader<TextClass> onCreateLoader(int id, Bundle args) {
		return new IdentifyerLoader(view.getContext(), args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<TextClass> loader, TextClass data) {
        int id = loader.getId();
        presenter.onLoadFinished(data);
        getActivity().getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader<TextClass> loader) {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == MSG_SHOW_DIALOG) {
                showDialog(lang);
            }
        }
    };
}
