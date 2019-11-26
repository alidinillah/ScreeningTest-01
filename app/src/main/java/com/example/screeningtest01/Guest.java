package com.example.screeningtest01;

import com.google.gson.annotations.*;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Guest extends RealmObject{
    @SerializedName("id")
    @PrimaryKey
    private int id;
    @SerializedName("name")
    private String nama;
    @SerializedName("birthdate")
    private String birthDate;

    public Guest() {
        nama = "";
    }

    public Guest(int id, String nama, String birthDate) {
        this.id = id;
        this.nama = nama;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
