package com.example.softomateidentifyerl;

public class IdentifyerPresenter implements IdentifyerContractor.Presenter {

    IdentifyerContractor.View view =null;
    DBClass db = null;
    public IdentifyerPresenter(IdentifyerContractor.View _view){
        view=_view;
        db = new DBClass();
    }
    @Override
    public void onFabWasClicked() {
        String text = view.getIdentText();
        //String lang = db.identifyerLang(text);
		view.initIdentifyer(text);
    }
	
	@Override
    public void onLoadFinished(TextClass txt) {
        view.isShowDialog(txt.getLang());
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void dialogShowDone() {
        view.clearTvIdentText();
    }
}
