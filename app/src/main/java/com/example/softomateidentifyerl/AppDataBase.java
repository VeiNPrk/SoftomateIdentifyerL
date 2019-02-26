package com.example.softomateidentifyerl;

import com.dbflow5.annotation.Database;
import com.dbflow5.config.DBFlowDatabase;

/**
 * Created by VNPrk on 16.09.2018.
 */



@Database(/*name = AppDataBase.NAME,*/ version = AppDataBase.VERSION)
public abstract class AppDataBase extends DBFlowDatabase {
    //public static final String NAME = "AppDatabase";
    public static final int VERSION = 1;
}
