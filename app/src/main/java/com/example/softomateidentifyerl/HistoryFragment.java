package com.example.softomateidentifyerl;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VNPrk on 27.10.2018.
 */

public class HistoryFragment extends Fragment implements HistoryContractor.View {

    private List<TextClass> texts;
    private HistoryRecyclerAdapter adapter;
    private RecyclerView rvHistory;
    private HistoryPresenter presenter;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        presenter.sendEventMessage(event.message);
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.history_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_delete_all: {
                presenter.deleteAllHistory();
                return true;
            }
            default:
                return false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


}
