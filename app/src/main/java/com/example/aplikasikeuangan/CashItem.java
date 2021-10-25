package com.example.aplikasikeuangan;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cash_table")
public class CashItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int nominal;
    private String keterangan;
    private String tanggal;
    private int income;
    public CashItem(int nominal, String keterangan, String tanggal, int income) {
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
        this.income = income;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getNominal() {
        return nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getIncome() {
        return income;
    }

}
