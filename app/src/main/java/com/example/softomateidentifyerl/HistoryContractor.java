package com.example.softomateidentifyerl;

import android.os.Bundle;
import android.view.View;

import java.util.List;


public interface HistoryContractor {
    interface View {
        void refreshData(List<TextClass> data);
    }

    interface Presenter {
        void viewWasInit();
        void onDestroy();
    }

}
