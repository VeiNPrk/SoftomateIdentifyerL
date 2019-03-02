package com.example.softomateidentifyerl;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by VNPrk on 16.09.2018.
 */



@Database(name = AppDataBase.NAME, version = AppDataBase.VERSION)
public class AppDataBase {
    public static final String NAME = "AppDatabase";
    public static final int VERSION = 1;
}
