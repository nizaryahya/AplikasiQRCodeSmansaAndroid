package com.example.aplikasiskripsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class QRCode extends AppCompatActivity {
    private ImageView backqr;
    private ImageView gambarqr;
    private String nisQrHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        Intent intent = getIntent();
        nisQrHolder = intent.getStringExtra(MasukSiswa.siswaNis);

        backqr = (ImageView) findViewById(R.id.backqr);
        gambarqr = (ImageView) findViewById(R.id.gambarqr) ;
        Glide.with(QRCode.this)
                // LOAD URL DARI INTERNET
                .load(konfigurasi.URL_Qr_nis + nisQrHolder +".png")
//                .load(konfigurasi.URL_Qr_nim +"A11.2017.10115.png");
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                //.placeholder()
                .into(gambarqr);
        backqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbackSiswa();
            }
        });
    }

    public void openbackSiswa(){
        Intent intent = new Intent(this, MasukSiswa.class);
        startActivity(intent);
    }
}
