package com.example.softomateidentifyerl;

import android.app.Application;

import com.dbflow5.config.DatabaseConfig;
import com.dbflow5.config.FlowConfig;
import com.dbflow5.config.FlowLog;
import com.dbflow5.config.FlowManager;
import com.dbflow5.database.AndroidSQLiteOpenHelper;

/**
 * Created by VNPrk on 16.09.2018.
 */

public class App extends Application {

    /*public static final UserClass iam = new UserClass();
    private static LocationApi locationApi;
    private Retrofit retrofit;*/

    @Override
    public void onCreate() {
        super.onCreate();
       /* FlowManager.init(new FlowConfig.Builder(this).build());
        // Setting Log Display
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
*/
        FlowManager.init(new FlowConfig.Builder(getApplicationContext())
                .database(DatabaseConfig.builder(AppDataBase.class, AndroidSQLiteOpenHelper.createHelperCreator(getApplicationContext()))
                        .databaseName("MyDatabase")
                .build())
                        .build());



        /*Gson gson = new GsonBuilder() .setLenient() .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://sert-pknk-ru.1gb.ru/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create(gson)) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        locationApi = retrofit.create(LocationApi.class); //Создаем объект, при помощи которого будем выполнять запросы
        */
        //CheckMyId();
    }

    /*private void CheckMyId()
    {
        UserClass iam = new Select().from(UserClass.class).where(UserClass_Table.type.is(1)).querySingle();
        //UserClass ids = null;
        if(iam==null) {
            getApi().getNewId().enqueue(new Callback<UserClass>() {
                @Override
                public void onResponse(Call<UserClass> call, Response<UserClass> response) {
                    if (response.isSuccessful()) {
                        im.setId(response.body().getId());
                        im.setName("");
                        im.setType(1);
                        im.save();
                        Log.d("Retrofit CheckMyID", "ID="+response.body().getId());

                    }
                        else {
                        Log.d("Retrofit CheckMyID", "CATCH "+response.code());
                    }
                }

                @Override
                public void onFailure(Call<UserClass> call, Throwable t) {
                    Log.d("Retrofit CheckMyID", "onFailure "+t.getMessage());
                    //Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/

    /*public static LocationApi getApi() {
        return locationApi;
    }*/
}
