package com.example.softomateidentifyerl;

import android.os.Bundle;

import java.util.List;


interface MVPRepository {
        void saveText(TextClass textItem);
		List<TextClass> getHistory();
		Bundle identifyerLang(String text);
		void clearHistory();
    }
