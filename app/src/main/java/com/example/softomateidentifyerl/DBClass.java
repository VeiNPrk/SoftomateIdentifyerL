package com.example.softomateidentifyerl;

import android.util.Log;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DBClass implements MVPRepository {

    public DBClass(){}

    @Override
    public void saveText(final TextClass textItem) {
        //textItem.save();
    }

    @Override
    public List<TextClass> getHistory() {
        List<TextClass> texts = null;
		//texts
        try{
            texts = (List<TextClass>) SQLite.select().from(TextClass.class).orderBy(TextClass_Table.dateTimeMils.getNameAlias(), false).queryList();

        }
        catch (Exception ex)
        {
            Log.e("CATCH getHistory", ex.getMessage());
        }
        return texts;
    }

    @Override
    public String identifyerLang(String text) {
        String returnStr = "";
        //text = "Hello world";
        /*try {
            Call<Languages> call = App.getApi().identText(App.getAuthToken(),text);

            messages.enqueue(new Callback<Languages>() {
                @Override
                public void onResponse(Call<Languages> call, Response<Languages> response) {
                    Log.d("","response " + response.body().toString());
                }

                @Override
                public void onFailure(Call<Languages> call, Throwable t) {
                    Log.d("identifyerLang", t.getMessage());
                }
            });
            messages.
            //Log.d("identifyerLang", App.getApi().identText(text).execute().body().toString());
        }
        catch (Exception ex){
            Log.e("identifyerLang", ex.getMessage());
        }*/
        Languages langs = null;
        try {
            Call<Languages> call = App.getApi().identText(App.getAuthToken(),text);
            Response<Languages> response = call.execute();

            if (response.isSuccessful()) {
                langs=response.body();
                if(langs!=null)
                    returnStr=langs.getLanguages().get(0).getLanguage();
                else
                    returnStr="NULL Langs";
            }
            else {
                returnStr=response.message()+" "+response.errorBody().toString();
                Log.e("identifyerLang", response.message()+" "+response.errorBody().toString());
            }
        } catch (IOException ex) {
            returnStr=ex.getMessage();
            Log.e("identifyerLang", ex.getMessage());
        }
        return returnStr;
    }

}
