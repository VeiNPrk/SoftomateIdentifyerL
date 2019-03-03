package com.example.softomateidentifyerl;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface IdentApi {
        @FormUrlEncoded
		@Headers({
			"Content-Type: text/plain",
		})
		@POST("/language-translator/api/v3/identify?version=2018-05-01")
		Call<Languages> identText(@Header("Authorization") String authkey,@Field("text") String text);
}
