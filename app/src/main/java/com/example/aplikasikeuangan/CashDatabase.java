package com.example.aplikasikeuangan;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CashItem.class}, version = 1)
public abstract class CashDatabase extends RoomDatabase {
    private static CashDatabase instance;

    public abstract CashItemDao cashItemDao();

    public static synchronized CashDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CashDatabase.class, "cash_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CashItemDao cashItemDao;

        private PopulateDbAsyncTask(CashDatabase db) {
            cashItemDao = db.cashItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cashItemDao.insert(new CashItem(200000, "Diambil  Om", "26/10/2021", 0));
            cashItemDao.insert(new CashItem(300000, "Sangu Ditambahi Kakak", "23/10/2021", 1));
            cashItemDao.insert(new CashItem(100000, "Sangu Di Minta Ibu", "24/10/2021", 0));
            cashItemDao.insert(new CashItem(500000, "Dapet Sangu Dari Ayah", "25/10/2021", 1));
            cashItemDao.insert(new CashItem(200000, "Diambil  Adek", "26/10/2021", 0));
            return null;
        }
    }

}
