package com.example.aplikasiskripsi;

public class UtilsApi {

    public static BaseApiService getAPIService(){

        return RetrofitClient.getClient("http://192.168.100.212/smansa-android/").create(BaseApiService.class);

    }
}
