package com.example.aplikasikeuangan;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CashRepository {
    private CashItemDao cashItemDao;
    private LiveData<List<CashItem>> allCashItem;

    private int totalPemasukan;
    private int totalPengeluaran;

    public CashRepository(Application application) {
        CashDatabase database = CashDatabase.getInstance(application);
        cashItemDao = database.cashItemDao();
        allCashItem = cashItemDao.getAllCash();

        totalPemasukan = cashItemDao.getJumlahPemasukan();
        totalPengeluaran = cashItemDao.getJumlahPengeluaran();
    }

    public void insert(CashItem cashItem) {
        new InsertCashAsyncTask(cashItemDao).execute(cashItem);
    }

    public void update(CashItem cashItem) {
        new UpdateNoteAsyncTask(cashItemDao).execute(cashItem);
    }

    public void delete(CashItem cashItem) {
        new DeleteCashAsyncTask(cashItemDao).execute(cashItem);
    }

    public void deleteAllCash() {
        new DeleteAllCashAsyncTask(cashItemDao).execute();
    }

    public int getJumlahPemasukan() {
        return totalPemasukan;
    }

    public int getJumlahPengeluaran() {
        return totalPengeluaran;
    }

    public LiveData<List<CashItem>> getAllCashItem() {
        return allCashItem;
    }

    private static class InsertCashAsyncTask extends AsyncTask<CashItem, Void, Void> {
        private CashItemDao cashItemDao;

        private InsertCashAsyncTask(CashItemDao cashItemDao) {
            this.cashItemDao = cashItemDao;
        }

        @Override
        protected Void doInBackground(CashItem... cashItems) {
            cashItemDao.insert(cashItems[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<CashItem, Void, Void> {
        private CashItemDao cashItemDao;

        private UpdateNoteAsyncTask(CashItemDao cashItemDao) {
            this.cashItemDao = cashItemDao;
        }

        @Override
        protected Void doInBackground(CashItem... cashItems) {
            cashItemDao.update(cashItems[0]);
            return null;
        }
    }

    private static class DeleteCashAsyncTask extends AsyncTask<CashItem, Void, Void> {
        private CashItemDao cashItemDao;

        private DeleteCashAsyncTask(CashItemDao cashItemDao) {
            this.cashItemDao = cashItemDao;
        }

        @Override
        protected Void doInBackground(CashItem... cashItems) {
            cashItemDao.delete(cashItems[0]);
            return null;
        }
    }

    private static class DeleteAllCashAsyncTask extends AsyncTask<Void, Void, Void> {
        private CashItemDao cashItemDao;

        private DeleteAllCashAsyncTask(CashItemDao cashItemDao) {
            this.cashItemDao = cashItemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cashItemDao.deleteAllCash();
            return null;
        }
    }

}
