package com.example.aplikasiskripsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.aplikasiskripsi.GuruActivity.UserNIP;
import static com.example.aplikasiskripsi.GuruActivity.UserNama;

public class MasukGuru extends AppCompatActivity {
    private Button buttonlist;
    private Button buttonabsen;
    private Button outguru;
  //  TextView namaShow;
    TextView nipShow;
   // String namaHolder;
    String nipHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk_guru);

       // namaShow = (TextView)findViewById(R.id.namagurumasuk);
        nipShow = (TextView)findViewById(R.id.nipgurumasuk);

        Intent intent = getIntent();
      //  namaHolder = intent.getStringExtra(UserNama);
        nipHolder = intent.getStringExtra(UserNama);
      //  namaShow.setText(namaHolder);
        nipShow.setText(nipHolder);

        buttonlist = (Button) findViewById(R.id.buttonlist);
        buttonlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlistkehadiran();
            }
        });

        buttonabsen = (Button) findViewById(R.id.buttonabsen);
        buttonabsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbsenScanner();
            }
        });

        outguru = (Button) findViewById(R.id.outguru);
        outguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKembaliKeHome();
            }
        });

    }
    public void openlistkehadiran(){
        Intent intent = new Intent(this,ListKehadiran.class);
//        Intent intent = new Intent(this,tampil_kelas.class);
        startActivity(intent);
    }
    public void openAbsenScanner(){
        Intent intent = new Intent(this,ListKelas.class);
        intent.putExtra(UserNIP,nipHolder);
        startActivity(intent);
    }
    public void openKembaliKeHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
