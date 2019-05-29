package com.example.softomateidentifyerl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;


public class IdentifyerLoader extends AsyncTaskLoader<Bundle> {

    public final String TAG = getClass().getSimpleName();
    public static final String KEY_IDENT_TEXT = "ident_text_key";
    public static final int IDENT_LOADER_ID = 100;
	private String identText;
    private DBClass db;

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
    public Bundle loadInBackground() {
        try {
			Bundle bundle = db.identifyerLang(identText);
            TextClass txt = null;
			if(bundle!=null) {
			    bundle.putString(DBClass.KEY_BUNDLE_TEXT, identText);
                String lang = bundle.getString(DBClass.KEY_BUNDLE_LANG);
                int code = bundle.getInt(DBClass.KEY_BUNDLE_CODE);
                txt = new TextClass(identText);
                txt.setLang(lang);
                if(code==1) {
                    db.saveText(txt);
                }
            }
			return bundle;
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
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
    public void deliverResult(Bundle data) {
        Log.d(TAG, "deliverResult");
        super.deliverResult(data);
    }
}
