package com.example.softomateidentifyerl;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(database = AppDataBase.class)
public class TextClass extends BaseModel {
    @PrimaryKey
	@Column
	private long dateTimeMils = 0;
    @Column
    private String text = "";
	@Column
    private String lang = "";

    public TextClass(){}
	
	public TextClass(String _text)
    {
        text=_text;
		dateTimeMils = new Date().getTime();
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public long getDateTimeMils(){
        return dateTimeMils;
    }

    public void setDateTimeMils(long dateTime){
        dateTimeMils = dateTime;
    }


}
