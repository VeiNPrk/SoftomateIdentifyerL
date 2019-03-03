package com.example.softomateidentifyerl;

import android.os.Bundle;
import android.util.Log;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class DBClass implements MVPRepository {

    public static final String KEY_BUNDLE_LANG="key_lang_bundle";
    public static final String KEY_BUNDLE_CODE="key_code_bundle";
    public static final String KEY_BUNDLE_TEXT="key_text_bundle";
    public final String TAG = getClass().getSimpleName();
    public DBClass(){}

    @Override
    public void saveText(final TextClass textItem) {
        textItem.save();
        EventBus.getDefault().post(new MessageEvent(MessageEvent.EVENT_UPDATE_HISTORY));
    }

    @Override
    public List<TextClass> getHistory() {
        List<TextClass> texts = null;
        try{
            texts = (List<TextClass>) SQLite.select().from(TextClass.class).orderBy(TextClass_Table.dateTimeMils.getNameAlias(), false).queryList();

        }
        catch (Exception ex)
        {
            Log.e(TAG, ex.getMessage());
        }
        return texts;
    }

    @Override
    public Bundle identifyerLang(String text) {
        String returnStr = "";
        int returnCode = 0;
        Bundle bundle = new Bundle();
        Languages langs = null;
        try {
            Call<Languages> call = App.getApi().identText(App.getAuthToken(),text);
            Response<Languages> response = call.execute();

            if (response.isSuccessful()) {
                langs = response.body();
                if (langs != null){
                    returnStr = langs.getLanguages().get(0).getLanguage();
                    returnCode=1;
                }
                else
                    returnStr="NULL Langs";
            }
            else {
                returnStr=response.message()+" "+response.errorBody().toString();
                Log.e(TAG, response.message()+" "+response.errorBody().toString());
            }
        } catch (IOException ex) {
            returnStr=ex.getMessage();
            Log.e(TAG, ex.getMessage());
        }
        bundle.putString(KEY_BUNDLE_LANG, returnStr);
        bundle.putInt(KEY_BUNDLE_CODE, returnCode);
        return bundle;
    }

    @Override
    public void clearHistory() {
        SQLite.delete().from(TextClass.class).execute();
        EventBus.getDefault().post(new MessageEvent(MessageEvent.EVENT_UPDATE_HISTORY));
    }
}
