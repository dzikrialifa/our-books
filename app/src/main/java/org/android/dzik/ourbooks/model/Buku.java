package org.android.dzik.ourbooks.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Buku implements Serializable {

    private String key;
    private String namaFilm,namaPemesan,email;
    private int jumlah;

    public Buku() {
    }

    public Buku(String namaFilm, String namaPemesan, String email, int jumlah) {
        this.namaFilm = namaFilm;
        this.namaPemesan = namaPemesan;
        this.email = email;
        this.jumlah = jumlah;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamaFilm() {
        return namaFilm;
    }

    public void setNamaFilm(String namaFilm) {
        this.namaFilm = namaFilm;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return " "+namaFilm+" "+namaPemesan+" "+email+" "+jumlah+" ";
    }
}
