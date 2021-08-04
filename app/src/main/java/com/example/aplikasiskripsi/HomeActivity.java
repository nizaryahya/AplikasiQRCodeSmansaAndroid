package com.example.aplikasiskripsi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {
    private CardView buttonsiswa;
    private CardView buttonguru;
    private CardView buttoninfo;
    private CardView buttonkeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Button Siswa
        buttonsiswa = (CardView) findViewById(R.id.buttonsiswa);
        buttonsiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMahaActivity();
            }
        });

//        Button Guru
        buttonguru = (CardView) findViewById(R.id.buttonguru);
        buttonguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDosenActivity();
            }
        });

        buttoninfo = (CardView) findViewById(R.id.buttoninfo);
        buttoninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoActivity();
            }
        });

        buttonkeluar = (CardView) findViewById(R.id.buttonkeluar);
        buttonkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
        private void logout() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Konfirmasi");
            builder.setMessage("Apakah anda ingin keluar dari aplikasi ?");
            builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                  //  startActivity(new Intent(HomeActivity.this, LoginActivity.class)
                          //  .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }


//    Tampilan ke LogIn Mahasiswa
    public void openMahaActivity(){
        Intent intent = new Intent(this, SiswaSiswaActivity.class);
        startActivity(intent);
    }
//    Tampilan ke LogIn Dosen
    public void openDosenActivity(){
        Intent intent = new Intent(this, GuruActivity.class);
        //Intent intent = new Intent(this, DosenActivity.class);
        startActivity(intent);
    }
    public void openInfoActivity(){
        Intent intent = new Intent(this,InfoActivity.class);
        startActivity(intent);
    }

}
