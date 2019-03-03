package com.example.softomateidentifyerl;

import android.os.Bundle;


public interface IdentifyerContractor {
    interface View {
        void isShowDialog(String lang);
        void isShowErrorDialog(String error);
		String getIdentText();
		void clearTvIdentText();
		void initIdentifyer(String text);
    }

    interface Presenter {
        void onFabWasClicked();
		void onLoadFinished(Bundle bundle);
        void onDestroy();
        void dialogShowDone();
    }
}
