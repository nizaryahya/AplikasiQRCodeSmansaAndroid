package com.example.aplikasiskripsi;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ProfilActivity extends AppCompatActivity {
    TextView  nama, email, jenkel, ttl, alamat, nis, pass;

    Sesion sesion;
    BaseApiService baseApiService;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Profil");
        ab.setElevation(0);

        sesion = new Sesion(this);
        baseApiService = UtilsApi.getAPIService();
        context = this;


        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        jenkel = findViewById(R.id.jenkel);
        alamat = findViewById(R.id.alamat);
        ttl = findViewById(R.id.ttl);
        nis = findViewById(R.id.nis);
        pass = findViewById(R.id.ttl);

        nama.setText(sesion.getNmsiswa());
        nis.setText(sesion.getNissiswa());
        pass.setText(sesion.getPasssiswa());
        email.setText(sesion.getEmailsiswa());
        alamat.setText(sesion.getAlamatsiswa());
        ttl.setText(sesion.getTtlsiswa());
        jenkel.setText(sesion.getJenkelsiswa());

    }
}