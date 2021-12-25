package com.diplomado.userroomsqlite.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.diplomado.userroomsqlite.Model.Dao.UserDao;
import com.diplomado.userroomsqlite.Model.Entities.User;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> userList;

    public UserRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
        userList = userDao.getAllLiveData();
    }

    public LiveData<List<User>> getAllUser(){
        return userList;
    }

}
