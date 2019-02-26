package com.example.softomateidentifyerl;

import android.os.Bundle;
import android.view.View;


public interface IdentifyerContractor {
    interface View {
        void showDialog(String lang);
		String getIdentText();
		void clearTvIdentText();
    }

    interface Presenter {
        void onFabWasClicked();
        void onDestroy();
    }
}
