package com.example.aplikasiskripsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
public class InfoActivity extends AppCompatActivity {
    private ImageView openbacktoMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        openbacktoMain = (ImageView) findViewById(R.id.openbacktoMain);
        openbacktoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openkeMain();
            }
        });
    }
    public void openkeMain(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}