package com.example.softomateidentifyerl;

import android.os.Bundle;
import android.view.View;

import java.util.List;


interface MVPRepository {
        void saveText(TextClass textItem);
		List<TextClass> getHistory();
		String identifyerLang(String text);
    }
