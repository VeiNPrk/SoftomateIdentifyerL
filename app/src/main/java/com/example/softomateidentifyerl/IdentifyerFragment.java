package com.example.softomateidentifyerl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * Created by VNPrk on 27.10.2018.
 */

public class IdentifyerFragment extends Fragment implements IdentifyerContractor.View{

    TextView tvText = null;
	FloatingActionButton fabIdent;
    View view=null;
    IdentifyerPresenter presenter=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_identifyer, container, false);
        presenter = new IdentifyerPresenter(this);
        initViews();

        //initData();
        return view;
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
    public void showDialog(String lang) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
        adb.setTitle(getString(R.string.dlg_tittle))
                .setPositiveButton(R.string.dlg_ok,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //succesRequest(requestUsers.get(position).getId());
                        //Toast.makeText(getContext(),"id="+id+" position="+position, Toast.LENGTH_LONG).show();
                    }
                })
                .setMessage(String.format("%s %s",getString(R.string.dlg_message), lang ) );
        adb.create().show();
    }

    @Override
    public String getIdentText() {
        return tvText.getText().toString();
    }

    @Override
    public void clearTvIdentText() {
        tvText.setText("");
    }
}
