package com.example.aplikasikeuangan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<UserItem>> allUserItem;

    private String username;
    private String password;
    private int id;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUserItem = repository.getAllUserItem();
        username = repository.getUsername();
        password = repository.getPassword();
        id = repository.getId();
    }

    public void insert(UserItem userItem) { repository.insert(userItem); }

    public void update(UserItem  userItem)  { repository.update(userItem); }

    public int getId() {
        return id;
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
}
