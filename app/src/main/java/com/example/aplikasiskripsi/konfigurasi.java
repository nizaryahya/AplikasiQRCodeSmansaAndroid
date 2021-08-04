package com.example.aplikasiskripsi;

public class konfigurasi {
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD_SISWA="http://192.168.100.212/smansa-android/database/addsiswa.php";
    public static final String URL_LOGIN_SISWA="http://192.168.100.212/smansa-android/database/loginsiswa.php";
    public static final String URL_ADD_GURU="http://192.168.100.212/smansa-android/database/addGuru.php";
    public static final String URL_LOGIN_GURU="http://192.168.100.212/smansa-android/database/loginGuru.php";
    public static final String URL_Absen="http://192.168.100.212/smansa-android/database/Absen.php";
    public static final String URL_GETDATA_GURU="http://192.168.100.212/smansa-android/database/dataGuru.php";
    public static final String URL_Qr_nis="http://192.168.100.212/smansa-android/database/temp/";
    public static final String URL_List_Kehadiran="http://192.168.100.212/smansa-android/database/listHadir.php/";
    public static final String URL_List_Kelas="http://192.168.100.212/smansa-android/database/listKelas.php/";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    //siswasiswa
    public static final String KEY_siswa_ID = "id";
    public static final String KEY_siswa_NIS = "nis";
    public static final String KEY_siswa_NAMA = "nama";
    public static final String KEY_siswa_TTL = "ttl";
    public static final String KEY_siswa_JENKEL = "jeniskelamin";
    public static final String KEY_siswa_ALAMAT = "alamat";
    public static final String KEY_siswa_EMAIL = "email";
    public static final String KEY_siswa_PASS = "pass";
    //petugas
    public static final String KEY_guru_ID = "id";
    public static final String KEY_guru_NIP = "nip";
    public static final String KEY_guru_NAMA = "nama";
    public static final String KEY_guru_JABATAN = "jabatan";
    public static final String KEY_guru_EMAIL = "email";
    public static final String KEY_guru_PASS = "pass";
    //kelas
    public static final String KEY_kls_ID = "idkls";
    public static final String KEY_kls_NO = "nokls";
    public static final String KEY_kls_NAMA = "namakls";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";

    public static final String TAG_siswa_ID = "id";
    public static final String TAG_siswa_NIS = "nis";
    public static final String TAG_siswa_NAMA = "nama";
    public static final String TAG_siswa_EMAIl = "email";
    public static final String TAG_siswa_PASS = "pass";

    public static final String TAG_guru_ID = "id";
    public static final String TAG_guru_NIP = "nip";
    public static final String TAG_guru_NAMA = "nama";
    public static final String TAG_guru_JABATAN = "jabatan";
    public static final String TAG_guru_EMAIl = "email";
    public static final String TAG_guru_PASS = "pass";

    public static final String TAG_kelas_NAMA = "namakls";
    public static final String TAG_kelas_NO = "nokls";
    public static final String TAG_kelas_ID = "idkls";

    public static final String GURU_NIP = "guru_nip";
    public static final String KLS_ID = "kls_id";

}
