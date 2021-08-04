package com.example.aplikasiskripsi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("android/login.php")
    Call<ResponseBody> loginRequest(@Field("nis") String nis,
                                    @Field("password") String password);





}

