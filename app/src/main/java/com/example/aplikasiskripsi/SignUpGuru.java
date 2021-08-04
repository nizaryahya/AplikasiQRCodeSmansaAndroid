package com.example.aplikasiskripsi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class SignUpGuru extends AppCompatActivity {
    private Button buttonsignupguru;
    private Button buttonkelogin;
    private TextView showpassguru;
    private EditText passguru;
    private EditText nipguru;
    private EditText namaguru;
    private EditText jabatanguru;
    private EditText emailguru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_guru);

        buttonsignupguru = (Button) findViewById(R.id.buttondaftarguru);
        buttonsignupguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpGur();
            }
        });

        buttonkelogin = (Button) findViewById(R.id.buttonkelogin);
        buttonkelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeLogIn();
            }
        });

        nipguru = (EditText) findViewById(R.id.nipgurusignup);
        namaguru = (EditText) findViewById(R.id.namagurusignup);
        emailguru = (EditText) findViewById(R.id.emailgurusignup);
        passguru = (EditText) findViewById(R.id.passgurusignup);
        jabatanguru = (EditText) findViewById(R.id.jabatangurusignup);
        showpassguru = (TextView)findViewById(R.id.showpassguru);


        showpassguru.setVisibility(View.GONE);
        passguru.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passguru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passguru.getText().length()>0){
                    showpassguru.setVisibility(View.VISIBLE);
                }
                else{
                    showpassguru.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        showpassguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showpassguru.getText() == "SHOW"){
                    showpassguru.setText("HIDE");
                    passguru.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passguru.setSelection(passguru.length());
                }
                else{
                    showpassguru.setText("SHOW");
                    passguru.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passguru.setSelection(passguru.length());
                }
            }
        });

    }

    public void openSignUpGur(){
        if (nipguru.getText().toString().length() == 0) {
            nipguru.setError("Harus Diisi");
        }  if (namaguru.getText().toString().length() == 0) {
            namaguru.setError("Harus Diisi");
        } if (jabatanguru.getText().toString().length() == 0) {
            jabatanguru.setError("Harus Diisi");
        }  if (emailguru.getText().toString().length() == 0) {
            emailguru.setError("Harus Diisi");
        }  if (passguru.getText().toString().length() == 0) {
            passguru.setError("Harus Diisi");
        } else {
            addGuru();
            //Intent intent = new Intent(this,SignUpDosen.class);
            //startActivity(intent);
        }
    }
    public void openKeLogIn(){
        Intent intent = new Intent(this, GuruActivity.class);
        startActivity(intent);
    }
    private void addGuru(){

        final String nama = namaguru.getText().toString().trim();
        final String nip = nipguru.getText().toString().trim();
        final String jabatan = jabatanguru.getText().toString().trim();
        final String email = emailguru.getText().toString().trim();
        final String pass = passguru.getText().toString().trim();

        class AddDosen extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUpGuru.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(SignUpGuru.this,s, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), GuruActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_guru_NIP,nip);
                params.put(konfigurasi.KEY_guru_NAMA,nama);
                params.put(konfigurasi.KEY_guru_JABATAN,jabatan);
                params.put(konfigurasi.KEY_guru_EMAIL,email);
                params.put(konfigurasi.KEY_guru_PASS,pass);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD_GURU, params);
                return res;
            }
        }

        AddDosen ad = new AddDosen();
        ad.execute();
    }
}
