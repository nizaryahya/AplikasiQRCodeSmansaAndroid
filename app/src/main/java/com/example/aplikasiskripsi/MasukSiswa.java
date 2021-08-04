package com.example.aplikasiskripsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MasukSiswa extends AppCompatActivity {
    private Button buttoncode;
    private Button outsiswa;
    TextView nisShow, namaShow;
    String nisHolder;
    public static final String siswaNis = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk_siswa);

        Intent intent = getIntent();
        nisHolder = intent.getStringExtra(SiswaSiswaActivity.UserNis);
        nisShow = (TextView)findViewById(R.id.nissiswamasuk);
        nisShow.setText(nisHolder);

        buttoncode = (Button)findViewById(R.id.buttoncode);
        buttoncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRCode();
            }
        });

        outsiswa = (Button) findViewById(R.id.logoutsiswa);
        outsiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKembaliHome();
            }
        });
    }
    public void openQRCode(){
        Intent intent = new Intent(this,QRCode.class);
        intent.putExtra(siswaNis,nisHolder);
        startActivity(intent);
    }
    public void openKembaliHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
