package com.example.softomateidentifyerl;

import java.util.ArrayList;
import java.util.List;

public class DBClass implements MVPRepository {

    public DBClass(){}

    @Override
    public void saveText(TextClass textItem) {

    }

    @Override
    public List<TextClass> getHistory() {

        List<TextClass> texts = new ArrayList<TextClass>();
        TextClass t1 = new TextClass("123fgbdb gvbe greg");
        t1.setLang("RU");
        texts.add(t1);
        TextClass t2 = new TextClass("123fgbdb gvbe greg");
        t2.setLang("RUEND");
        texts.add(t2);
        TextClass t3 = new TextClass("123fgbdb gvbe greg");
        t3.setLang("RUS");
        texts.add(t3);
        TextClass t4 = new TextClass("123fgbdb gvbe greg");
        t4.setLang("ENG");
        texts.add(t4);
        return texts;
    }

    @Override
    public String identifyerLang(String text) {
        return null;
    }

    private void testFill()
    {

    }
}
