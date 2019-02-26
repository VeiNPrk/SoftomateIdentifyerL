package com.example.softomateidentifyerl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.database.Cursor;
import android.support.v4.app.Fragment;

import android.database.ContentObserver;
//import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VNPrk on 27.10.2018.
 */

public class HistoryFragment extends Fragment implements HistoryContractor.View {

    /*List<String> elements;
    List<Integer> selectedPositions;*/
    List<TextClass> texts;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    HistoryRecyclerAdapter adapter;
    MenuItem deleteItem;
    RecyclerView rvHistory;
    ActionMode actionMode;
    HistoryPresenter presenter;
    ProgressBar progressBar;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        presenter = new HistoryPresenter(this);
        initViews();
        setRecyclerView();
        texts = new ArrayList<TextClass>();
        presenter.viewWasInit();
        /*testFill();
        refreshData(texts);*/
        return view;

    }

    private void initViews(){
        rvHistory = (RecyclerView)view.findViewById(R.id.rv_history);
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new HistoryRecyclerAdapter(view.getContext(), texts);
        rvHistory.setLayoutManager(layoutManager);
        rvHistory.setHasFixedSize(true);
        rvHistory.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        rvHistory.addItemDecoration(itemDecoration);
        rvHistory.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void refreshData(List<TextClass> data) {
        texts = data;
        adapter.setData(texts);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
