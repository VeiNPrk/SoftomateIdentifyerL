package com.example.softomateidentifyerl;

import java.util.List;

public class HistoryPresenter implements HistoryContractor.Presenter {


    HistoryContractor.View view=null;
    DBClass db = null;

    public HistoryPresenter(HistoryContractor.View _view){
        view=_view;
        db = new DBClass();
    }

    @Override
    public void viewWasInit() {
        List<TextClass> historyData = db.getHistory();
        view.refreshData(historyData);
    }

    @Override
    public void onDestroy() {
        db = null;
    }
}
