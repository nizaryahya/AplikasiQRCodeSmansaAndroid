package com.example.aplikasiskripsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class KegiatanActivity extends AppCompatActivity {
    private Button buttonmapel1;
    private ImageView backlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);

        buttonmapel1 = (Button) findViewById(R.id.buttonkegiatan);
        buttonmapel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapelActivity();
            }
        });

        backlist = (ImageView) findViewById(R.id.backlist);
        backlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBackList();
            }
        });
    }

    public void openMapelActivity(){
        Intent intent = new Intent(this, ListKehadiran.class);
        startActivity(intent);
    }
    public void openBackList(){
        Intent intent = new Intent(this, MasukGuru.class);
        startActivity(intent);
    }
}
