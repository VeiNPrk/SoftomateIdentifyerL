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
        //сделать лоадер
        String lang = db.identifyerLang(text);
        TextClass txt = new TextClass(text);
        txt.setLang(lang);
        db.saveText(txt);
        view.showDialog(text);
    }

    @Override
    public void onDestroy() {

    }
}
