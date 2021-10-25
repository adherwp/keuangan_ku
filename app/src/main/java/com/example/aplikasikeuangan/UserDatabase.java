package com.example.aplikasikeuangan;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {UserItem.class} , version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;
    public abstract UserItemDao userItemDao();

    public static synchronized UserDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private  static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserItemDao userItemDao;

        private PopulateDbAsyncTask(UserDatabase db){
            userItemDao = db.userItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userItemDao.insert(new UserItem("username", "password"));
            return null;
        }
    }



}
