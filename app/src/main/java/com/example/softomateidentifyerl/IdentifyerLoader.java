package com.example.softomateidentifyerl;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VNPrk on 23.01.2018.
 */

public class IdentifyerLoader extends AsyncTaskLoader<TextClass> {

    public final String TAG = getClass().getSimpleName();
    public static final String KEY_IDENT_TEXT = "ident_text_key";

    public static final int IDENT_LOADER_ID = 100;
    /*private int typeOperation;
	private int typeCondition;
	private double latitude;
	private double longitude;*/
	private String identText;
	DBClass db;

    public IdentifyerLoader(Context context, Bundle args) {
        super(context);
        if (args != null){
			identText = args.getString(KEY_IDENT_TEXT);
		}
		db = new DBClass();
    }

    public IdentifyerLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    public TextClass loadInBackground() {
        try {
			String lang = db.identifyerLang(identText);
			TextClass txt = new TextClass(identText);
			txt.setLang(lang);
			db.saveText(txt);
			return txt;
            //return apiCall(idUser);
        } catch (Exception e) {
            Log.d("loadInBackground", e.getMessage());
            return null;
        }
    }


    @Override
    public void forceLoad() {
        Log.d(TAG, "forceLoad");
        super.forceLoad();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(TAG, "onStartLoading");
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d(TAG, "onStopLoading");
    }

    @Override
    public void deliverResult(TextClass data) {
        Log.d(TAG, "deliverResult");
        super.deliverResult(data);
    }
}
