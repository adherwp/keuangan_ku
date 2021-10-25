package com.example.aplikasikeuangan;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserItemDao userItemDao;
    private LiveData<List<UserItem>> allUserItem;

    private String username;
    private String password;
    private int id;

    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);
        userItemDao = database.userItemDao();
        allUserItem = userItemDao.getAllUser();

        username = userItemDao.getUsername();
        password = userItemDao.getPassword();
        id = userItemDao.getId();
    }

    public void insert(UserItem userItem) {
        new InsertUserAsyncTask(userItemDao).execute(userItem);
    }

    public void update(UserItem userItem) {
        new UpdateUserAsyncTask(userItemDao).execute(userItem);
    }

    public int getId() {
        return  id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LiveData<List<UserItem>> getAllUserItem() {
        return allUserItem;
    }

    public static class InsertUserAsyncTask extends AsyncTask<UserItem, Void, Void> {
        private UserItemDao userItemDao;

        private InsertUserAsyncTask(UserItemDao userItemDao) {
            this.userItemDao = userItemDao;
        }

        @Override
        protected Void doInBackground(UserItem... userItems) {
            userItemDao.insert(userItems[0]);
            return null;
        }
    }

    public static class UpdateUserAsyncTask extends AsyncTask<UserItem, Void, Void> {
        private UserItemDao userItemDao;

        private UpdateUserAsyncTask(UserItemDao userItemDao) {
            this.userItemDao = userItemDao;
        }

        @Override
        protected Void doInBackground(UserItem... userItems) {
            userItemDao.update(userItems[0]);
            return null;
        }
    }

}
