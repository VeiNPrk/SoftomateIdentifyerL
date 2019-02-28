package com.example.softomateidentifyerl;

import android.util.Log;

import com.dbflow5.query.SQLite;
import com.dbflow5.query.Select;

import java.util.ArrayList;
import java.util.List;

public class DBClass implements MVPRepository {

    public DBClass(){}

    @Override
    public void saveText(TextClass textItem) {
	//	textItem.save();
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
		/*List<TextClass> texts = null;
        try{
            texts = SQLite.select().from(TextClass.class).orderBy(TextClass_Table.dateTimeMils, true).queryList();
        }
        catch (Exception ex)
        {
            Log.e("CATCH getHistory", ex.getMessage());
        }*/
        return texts;
    }

    @Override
    public String identifyerLang(String text) {
		
        return "Чтото";
    }

    private void testFill()
    {

    }
}
