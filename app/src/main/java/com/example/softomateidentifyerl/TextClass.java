package com.example.softomateidentifyerl;


import com.dbflow5.annotation.Column;
import com.dbflow5.annotation.PrimaryKey;
import com.dbflow5.annotation.Table;
import com.dbflow5.structure.BaseModel;

import java.util.Date;

/**
 * Created by VNPrk on 16.09.2018.
 */

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
