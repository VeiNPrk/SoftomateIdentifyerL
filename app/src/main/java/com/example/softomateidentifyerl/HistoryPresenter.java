package com.example.softomateidentifyerl;

import java.util.List;

public class HistoryPresenter implements HistoryContractor.Presenter {

    private HistoryContractor.View view=null;
    private DBClass db = null;

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
    public void sendEventMessage(String message) {
        if(message==MessageEvent.EVENT_UPDATE_HISTORY)
            viewWasInit();
    }

    @Override
    public void deleteAllHistory() {
        db.clearHistory();
    }

    @Override
    public void onDestroy() {
        view=null;
        db = null;
    }
}
