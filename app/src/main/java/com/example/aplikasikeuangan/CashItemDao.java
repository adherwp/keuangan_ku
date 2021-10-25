package com.example.aplikasikeuangan;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CashItemDao {

    @Insert
    void insert(CashItem cashItem);

    @Update
    void update(CashItem cashItem);

    @Delete
    void delete(CashItem cashItem);

    @Query("DELETE FROM cash_table")
    void deleteAllCash();

    @Query("SELECT sum(nominal) FROM cash_table WHERE income = 1")
    int getJumlahPemasukan();

    @Query("SELECT sum(nominal) FROM cash_table WHERE income = 0")
    int getJumlahPengeluaran();

    @Query("SELECT * FROM cash_table ORDER BY tanggal DESC")
    LiveData<List<CashItem>> getAllCash();

}
