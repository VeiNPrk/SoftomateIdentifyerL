package com.example.softomateidentifyerl;

import android.os.Bundle;

public class IdentifyerPresenter implements IdentifyerContractor.Presenter {

    private IdentifyerContractor.View view =null;
    private DBClass db = null;
    public IdentifyerPresenter(IdentifyerContractor.View _view){
        view=_view;
        db = new DBClass();
    }
    @Override
    public void onFabWasClicked() {
        String text = view.getIdentText();
        text=text.trim();
        if(text.length()==0 || text.isEmpty())
            view.isShowErrorDialog(App.getContext().getString(R.string.dlg_null_message));
        else
            view.initIdentifyer(text);
    }
	
	@Override
    public void onLoadFinished(Bundle bundle) {
        if(bundle!=null) {
            String lang = bundle.getString(DBClass.KEY_BUNDLE_LANG);
            int code = bundle.getInt(DBClass.KEY_BUNDLE_CODE);
            if(code==1) {
                view.isShowDialog(lang);
            }
            else
                view.isShowErrorDialog(lang);
        }
    }

    @Override
    public void onDestroy() {
        view=null;
        db=null;
    }

    @Override
    public void dialogShowDone() {
        view.clearTvIdentText();
    }
}
