package com.example.reminderobaat;

public class Reminder {
    private int mID;
    private String mJudul;
    private String mTanggal;
    private String mWaktu;
    private String mPengulangan;
    private String mInterval;
    private String mTipePengulangan;
    private String mAktif;


    public Reminder(int ID, String Judul, String Tanggal, String Waktu, String Pengulangan, String Interval, String TipePengulangan, String Aktif){
        mID = ID;
        mJudul = Judul;
        mTanggal = Tanggal;
        mWaktu = Waktu;
        mPengulangan = Pengulangan;
        mInterval = Interval;
        mTipePengulangan = TipePengulangan;
        mAktif = Aktif;
    }

    public Reminder(String Judul, String Tanggal, String Waktu, String Pengulangan, String Interval, String TipePengulangan, String Aktif){
        mJudul = Judul;
        mTanggal = Tanggal;
        mWaktu = Waktu;
        mPengulangan = Pengulangan;
        mInterval = Interval;
        mTipePengulangan = TipePengulangan;
        mAktif = Aktif;
    }

    public Reminder(){}

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getJudul() {
        return mJudul;
    }

    public void setJudul(String Judul) {
        mJudul = Judul;
    }

    public String getTanggal() {
        return mTanggal;
    }

    public void setTanggal (String Tanggal) {
        mTanggal = Tanggal;
    }

    public String getWaktu() {
        return mWaktu;
    }

    public void setWaktu(String Waktu) {
        mWaktu = Waktu;
    }

    public String getTipePengulangan() {
        return mTipePengulangan;
    }

    public void setTipePengulangan(String TipePengulangan) {
        mTipePengulangan = TipePengulangan;
    }

    public String getInterval() {
        return mInterval;
    }

    public void setInterval (String Interval) {
        mInterval = Interval;
    }

    public String getPengulangan() {
        return mPengulangan;
    }

    public void setPengulangan (String Pengulangan) {
        mPengulangan = Pengulangan;
    }

    public String getAktif() {
        return mAktif;
    }

    public void setAktif(String Aktif) {
        mAktif = Aktif;
    }
}
