package com.example.aplikasiskripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class SiswaSiswaActivity extends AppCompatActivity {
    private Button buttonmasuksiswa,tomboldaftar;
    private ImageView backlogsiswa;
    private EditText passsiswalogin;
    private TextView showlogsiswa;
    private String nis, pass;
    private EditText nissiswalogin;
    Boolean CheckEditText;
    public static final String UserNis = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswasiswa);

        buttonmasuksiswa = (Button) findViewById(R.id.buttonmasuksiswa);
        buttonmasuksiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMasukSiswa();
            }
        });

        backlogsiswa = (ImageView) findViewById(R.id.backlogsiswa);
        backlogsiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentampilanawal();
            }
        });

        tomboldaftar = (Button) findViewById(R.id.tomboldaftar);
        tomboldaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDaftarAkun();
            }
        });

        nissiswalogin = (EditText) findViewById(R.id.nissiswalogin);
        passsiswalogin = (EditText) findViewById(R.id.passsiswalogin);
        showlogsiswa = (TextView)findViewById(R.id.showlogsiswa);

        showlogsiswa.setVisibility(View.GONE);
        passsiswalogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passsiswalogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passsiswalogin.getText().length()>0){
                    showlogsiswa.setVisibility(View.VISIBLE);
                }
                else{
                    showlogsiswa.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        showlogsiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showlogsiswa.getText() == "SHOW"){
                    showlogsiswa.setText("HIDE");
                    passsiswalogin.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passsiswalogin.setSelection(passsiswalogin.length());
                }
                else{
                    showlogsiswa.setText("SHOW");
                    passsiswalogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passsiswalogin.setSelection(passsiswalogin.length());
                }
            }
        });
    }
    public void openMasukSiswa(){
        CheckEditTextIsEmptyOrNot();

        if(CheckEditText){

            LoginSiswa(nis, pass);

        }
        else {

            Toast.makeText(SiswaSiswaActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

        }
//        Intent intent = new Intent(this,MasukMahasiswa.class);
//        startActivity(intent);
    }
    public void opentampilanawal(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
    public void openDaftarAkun(){
        Intent intent = new Intent(this,SignUpSiswa.class);
        startActivity(intent);
    }

    public void CheckEditTextIsEmptyOrNot(){

        nis = nissiswalogin.getText().toString();
        pass = passsiswalogin.getText().toString();

        if(TextUtils.isEmpty(nis) || TextUtils.isEmpty(pass))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }
    public void LoginSiswa(final String nis, final String password){

        class LoginSiswaClass extends AsyncTask<String,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(SiswaSiswaActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if(httpResponseMsg.equalsIgnoreCase("Data Matched")){

                    finish();

                    Intent intent = new Intent(SiswaSiswaActivity.this, MasukSiswa.class);

                    intent.putExtra(UserNis,nis);

                    startActivity(intent);

                }
                else{

                    Toast.makeText(SiswaSiswaActivity.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... s) {

                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_siswa_NIS,nis);
                params.put(konfigurasi.KEY_siswa_PASS,password);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_LOGIN_SISWA, params);
                return res;
            }
        }

        LoginSiswaClass siswaLoginClass = new LoginSiswaClass();
        siswaLoginClass.execute(nis,password);
    }

}