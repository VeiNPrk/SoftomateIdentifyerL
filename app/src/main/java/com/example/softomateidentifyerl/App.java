package com.example.softomateidentifyerl;

import android.app.Application;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.io.UnsupportedEncodingException;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VNPrk on 16.09.2018.
 */

public class App extends Application {

    public static final String USERNAME_API="6987a48d-342e-4a69-8adc-e65b1cc0b9da";
    public static final String PASSWORD_API="MxYSIi6nQP2Y";
    public static final String MAINURL_API="https://gateway.watsonplatform.net/language-translator/api/";
    /*public static final UserClass iam = new UserClass();
    private Retrofit retrofit;*/
	private static IdentApi identApi;
	private Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        //String baseUrlApi="https://"+USERNAME_API+PASSWORD_API+MAINURL_API;
       /* FlowManager.init(new FlowConfig.Builder(this).build());
        // Setting Log Display
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
*/
        FlowManager.init(FlowConfig.builder(this)
                .addDatabaseConfig(DatabaseConfig.builder(AppDataBase.class)
                        .databaseName("AppDatabase")
                        .build())
                .build());

        Gson gson = new GsonBuilder() .setLenient() .create();
        /*OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .authenticator()*/

        retrofit = new Retrofit.Builder()
                .baseUrl(MAINURL_API) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create(gson)) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        identApi = retrofit.create(IdentApi.class); //Создаем объект, при помощи которого будем выполнять запросы

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

    public static IdentApi getApi() {
        return identApi;
    }

    public static String getAuthToken() {
        byte[] data = new byte[0];
        try {
            data = (USERNAME_API + ":" + PASSWORD_API).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
    }
}
