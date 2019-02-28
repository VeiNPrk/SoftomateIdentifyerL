package com.example.softomateidentifyerl;

import android.os.Bundle;
import android.view.View;


public interface IdentifyerContractor {
    interface View {
        void isShowDialog(String lang);
		String getIdentText();
		void clearTvIdentText();
		void initIdentifyer(String text);
    }

    interface Presenter {
        void onFabWasClicked();
		void onLoadFinished(TextClass txt);
        void onDestroy();
    }
}
