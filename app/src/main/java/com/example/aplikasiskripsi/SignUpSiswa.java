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

public class SignUpSiswa extends AppCompatActivity {
    private Button buttondaftarsiswa,kembalikelogin;
    private TextView show;
    private EditText passsiswa;
    private EditText namasiswa;
    private EditText ttlsiswa;
    private EditText jeniskelaminsiswa;
    private EditText alamatsiswa;
    private EditText nissiswa;
    private EditText emailsiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_siswa);

        buttondaftarsiswa = (Button) findViewById(R.id.buttondaftarsiswa);
        buttondaftarsiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupSis();
            }
        });

        kembalikelogin = (Button) findViewById(R.id.kembalikelogin);
        kembalikelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKembaliLogin();
            }
        });

        nissiswa = (EditText) findViewById(R.id.nimsiswasignup);
        namasiswa = (EditText) findViewById(R.id.namasiswasignup);
        ttlsiswa = (EditText) findViewById(R.id.ttlsiswasignup);
        jeniskelaminsiswa = (EditText) findViewById(R.id.jeniskelaminsiswasignup);
        alamatsiswa = (EditText) findViewById(R.id.alamatsiswasignup);
        emailsiswa = (EditText) findViewById(R.id.emailsiswasignup);
        passsiswa = (EditText) findViewById(R.id.passsiswasignup);
        show = (TextView)findViewById(R.id.show);

        show.setVisibility(View.GONE);
        passsiswa.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passsiswa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passsiswa.getText().length()>0){
                    show.setVisibility(View.VISIBLE);
                }
                else{
                    show.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show.getText() == "SHOW"){
                    show.setText("HIDE");
                    passsiswa.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passsiswa.setSelection(passsiswa.length());
                }
                else{
                    show.setText("SHOW");
                    passsiswa.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passsiswa.setSelection(passsiswa.length());
                }
            }
        });
    }

    public void openSignupSis() {
        if (namasiswa.getText().toString().length() == 0) {
            namasiswa.setError("Harus Diisi");
        }  if (ttlsiswa.getText().toString().length() == 0) {
                ttlsiswa.setError("Harus Diisi");
        } if (nissiswa.getText().toString().length() == 0) {
            nissiswa.setError("Harus Diisi");
        }  if (jeniskelaminsiswa.getText().toString().length() == 0) {
            jeniskelaminsiswa.setError("Harus Diisi");
        }  if (alamatsiswa.getText().toString().length() == 0) {
            alamatsiswa.setError("Harus Diisi");
        }  if (emailsiswa.getText().toString().length() == 0) {
            emailsiswa.setError("Harus Diisi");
        }  if (passsiswa.getText().toString().length() == 0) {
            passsiswa.setError("Harus Diisi");
            } else {
                addSiswa();
                //Intent intent = new Intent(this,MasukMahasiswa.class);
                //startActivity(intent);
            }
        }

    public void openKembaliLogin(){
        Intent intent = new Intent(this, SiswaSiswaActivity.class);
        startActivity(intent);
    }
    private void addSiswa(){

        final String nama = namasiswa.getText().toString().trim();
        final String nis = nissiswa.getText().toString().trim();
        final String ttl = ttlsiswa.getText().toString().trim();
        final String jeniskelamin = jeniskelaminsiswa.getText().toString().trim();
        final String alamat = alamatsiswa.getText().toString().trim();
        final String email = emailsiswa.getText().toString().trim();
        final String pass = passsiswa.getText().toString().trim();

        class AddSiswa extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUpSiswa.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(SignUpSiswa.this,s, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), SiswaSiswaActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_siswa_NIS,nis);
                params.put(konfigurasi.KEY_siswa_NAMA,nama);
                params.put(konfigurasi.KEY_siswa_TTL,ttl);
                params.put(konfigurasi.KEY_siswa_JENKEL,jeniskelamin);
                params.put(konfigurasi.KEY_siswa_ALAMAT,alamat);
                params.put(konfigurasi.KEY_siswa_EMAIL,email);
                params.put(konfigurasi.KEY_siswa_PASS,pass);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD_SISWA, params);
                return res;
            }
        }

        AddSiswa am = new AddSiswa();
        am.execute();
    }
}
