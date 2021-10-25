package com.example.aplikasikeuangan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CashViewModel extends AndroidViewModel {
    private CashRepository repository;
    private LiveData<List<CashItem>> allCashItem;

    private int totalPemasukan;
    private int totalPengeluaran;

    public CashViewModel(@NonNull Application application) {
        super(application);
        repository = new CashRepository(application);
        allCashItem = repository.getAllCashItem();
        totalPemasukan = repository.getJumlahPemasukan();
        totalPengeluaran = repository.getJumlahPengeluaran();
    }

    public void insert(CashItem cashItem)  {
        repository.insert(cashItem);
    }

    public void update(CashItem  cashItem)  {
        repository.update(cashItem);
    }

    public void delete(CashItem cashItem) {
        repository.delete(cashItem);
    }

    public void deleteAllCash(){
        repository.deleteAllCash();
    }

    public int getJumlahPemasukan(){ return totalPemasukan; }

    public int getJumlahPengeluaran(){ return totalPengeluaran; }

    public LiveData<List<CashItem>> getAllCashItem() {
        return allCashItem;
    }
}
