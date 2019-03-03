package com.example.softomateidentifyerl;

import java.util.List;


public interface HistoryContractor {
    interface View {
        void refreshData(List<TextClass> data);
    }

    interface Presenter {
        void viewWasInit();
        void sendEventMessage(String message);
        void deleteAllHistory();
        void onDestroy();
    }

}
