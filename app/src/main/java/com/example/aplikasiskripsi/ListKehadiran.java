package com.example.aplikasiskripsi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListKehadiran extends AppCompatActivity {
// implements ListView.OnItemClickListener
    private ListView listhadir;

    private String JSON_STRING;
    private ImageView backtomasukguru;
    private String nipHolder;
//    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kehadiran);

        Intent intent = getIntent();
        nipHolder = intent.getStringExtra(konfigurasi.GURU_NIP);
 //       id = intent.getStringExtra(konfigurasi.KLS_ID);


        backtomasukguru = (ImageView) findViewById(R.id.backtomasukguru);
        backtomasukguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMasukGuru();
            }
        });

        listhadir = (ListView) findViewById(R.id.listhadir);
//        listKelas.setOnItemClickListener(this);
        getJSON();
    }


    private void showHadir(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String nis = jo.getString(konfigurasi.TAG_siswa_NIS);
                String nama = jo.getString(konfigurasi.TAG_siswa_NAMA);
               // String kegiatan = jo.getString(konfigurasi.TAG_kelas_NO);

                HashMap<String, String> hadir = new HashMap<>();
                hadir.put(konfigurasi.TAG_siswa_NIS,nis);
                hadir.put(konfigurasi.TAG_siswa_NAMA,nama);
              //  hadir.put(konfigurasi.TAG_kelas_NO,kegiatan);
                list.add(hadir);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ListKehadiran.this, list, R.layout.list_hadir,
                new String[]{konfigurasi.TAG_siswa_NIS,konfigurasi.TAG_siswa_NAMA},
                new int[]{R.id.nissiswalist, R.id.namasiswalist});

        listhadir.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ListKehadiran.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showHadir();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_List_Kehadiran);
//                String s = rh.sendGetRequestParamId(konfigurasi.URL_List_Kehadiran,id);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    public void openMasukGuru(){
        Intent intent = new Intent(this, MasukGuru.class);
//        Intent intent = new Intent(this, tampil_kelas.class);
        intent.putExtra(konfigurasi.GURU_NIP,nipHolder);
        startActivity(intent);
    }
}

