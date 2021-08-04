package com.example.aplikasiskripsi;

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

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GuruActivity extends AppCompatActivity {
    private Button buttonmasukguru,buttondaftarguru;
    private ImageView backlogguru;
    private TextView showpassguru;
    private String nip, pass;
    private EditText passgurulogin;
    private EditText nipgurulogin;
    Boolean CheckEditText;
    public static final String UserNIP = "";
    public static final String UserNama = "";
    public static final String UserID = "";
    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);

//        Button Masuk Guru
        buttonmasukguru = (Button) findViewById(R.id.buttonmasukguru);
        buttonmasukguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMasukGuru();
            }
        });
//        Button back
        backlogguru = (ImageView) findViewById(R.id.backlogguru);
        backlogguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKembaliGuru();
            }
        });
//        Button sign up
        buttondaftarguru = (Button) findViewById(R.id.buttonsignupguru);
        buttondaftarguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDaftarGuru();
            }
        });

        nipgurulogin = (EditText) findViewById(R.id.nipgurulogin);
        passgurulogin = (EditText) findViewById(R.id.passgurulogin);
        showpassguru = (TextView)findViewById(R.id.showpassguru);

        showpassguru.setVisibility(View.GONE);
        passgurulogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passgurulogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passgurulogin.getText().length()>0){
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
                    passgurulogin.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passgurulogin.setSelection(passgurulogin.length());
                }
                else{
                    showpassguru.setText("SHOW");
                    passgurulogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passgurulogin.setSelection(passgurulogin.length());
                }
            }
        });
    }

//    Tampil ke Masuk Guru
    public void openMasukGuru(){
        CheckEditTextIsEmptyOrNot();

        if(CheckEditText){

            LoginGuru(nip, pass);

        }
        else {

            Toast.makeText(GuruActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

        }
//        Intent intent = new Intent(this, MasukDosen.class);
//        startActivity(intent);
    }
    public void openKembaliGuru(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void openDaftarGuru(){
        Intent intent = new Intent(this, SignUpGuru.class);
        startActivity(intent);
    }
    public void CheckEditTextIsEmptyOrNot(){

        nip = nipgurulogin.getText().toString();
        pass = passgurulogin.getText().toString();

        if(TextUtils.isEmpty(nip) || TextUtils.isEmpty(pass))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }
    public void LoginGuru(final String nip, final String password){

        class LoginDsnClass extends AsyncTask<String, Void, String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(GuruActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if(httpResponseMsg.equalsIgnoreCase("Data Matched")){

                    finish();
                    //getJSON();

                    Intent intent = new Intent(GuruActivity.this, MasukGuru.class);

                    //showDosen();
                    //intent.putExtra(UserNama,konfigurasi.TAG_dsn_NAMA);
                    //intent.putExtra(UserNIP,konfigurasi.TAG_dsn_NIP);
                    intent.putExtra(UserNIP,nip);
                    //intent.putExtra(UserNama,email);

                    startActivity(intent);

                }
                else{

                    Toast.makeText(GuruActivity.this,httpResponseMsg, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... s) {

                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_guru_NIP,nip);
                params.put(konfigurasi.KEY_guru_PASS,password);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_LOGIN_GURU, params);
                return res;
            }
        }

        LoginDsnClass guruLoginClass = new LoginDsnClass();
        guruLoginClass.execute(nip,password);
    }

    private void showGuru(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            JSONObject jo = result.getJSONObject(0);
            String id = jo.getString(konfigurasi.TAG_guru_ID);
            String name = jo.getString(konfigurasi.TAG_guru_NAMA);
            String nip = jo.getString(konfigurasi.TAG_guru_NIP);

            Intent intent = new Intent(GuruActivity.this, MasukGuru.class);
            intent.putExtra(UserNama,name);
            intent.putExtra(UserNIP,nip);
            intent.putExtra(UserID,id);

            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GuruActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showGuru();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParamMail(konfigurasi.URL_GETDATA_GURU,nip,pass);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
