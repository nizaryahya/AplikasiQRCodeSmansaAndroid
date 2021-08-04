package com.example.aplikasiskripsi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Sesion {
    public static final String APP = "Aplikasi Skripsi";

    public static final String IDSISWA = "idSISWA";
    public static final String NMSISWA = "nmSISWA";
    public static final String TTLSISWA = "ttlSISWA";
    public static final String EMAISISWA = "emailSISWA";
    public static final String JENKELSISWA = "jenkelSISWA";
    public static final String ALAMATSISWA = "alamatSISWA";
    public static final String NISSISWA = "nisSISWA";
    public static final String PASSSISWA = "passSISWA";


    public static final String SUDAH_LOGIN = "SudahLogin";

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    @SuppressLint("CommitPrefEdits")
    public Sesion(Context context){
        sp = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }
    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }
    public String getIdsiswa(){
        return sp.getString(IDSISWA, "");
    }
    public String getNmsiswa(){ return sp.getString(NMSISWA, ""); }
    public String getEmailsiswa(){ return sp.getString(EMAISISWA, ""); }
    public String getTtlsiswa(){ return sp.getString(TTLSISWA, ""); }
    public String getJenkelsiswa(){ return sp.getString(JENKELSISWA, ""); }
    public String getAlamatsiswa(){ return sp.getString(ALAMATSISWA, ""); }
    public String getNissiswa(){ return sp.getString(NISSISWA, ""); }
    public String getPasssiswa(){ return sp.getString(PASSSISWA, ""); }



}


