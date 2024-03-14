package com.example.contactapp;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {


    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;

    @ColumnInfo
    private String mobile;

    @ColumnInfo
    private String gmail;

    public Contact(String name, String mobile, String gmail) {
        this.name = name;
        this.mobile = mobile;
        this.gmail = gmail;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
