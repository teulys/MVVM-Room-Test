package com.diplomado.userroomsqlite.ViewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.diplomado.userroomsqlite.Model.AppDatabase;
import com.diplomado.userroomsqlite.Model.Dao.UserDao;
import com.diplomado.userroomsqlite.Model.Entities.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private LiveData<List<User>> userList;
    private UserDao db;

    public UserViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application).userDao();
        userList = db.getAllLiveData();
    }

    public LiveData<List<User>> getAllUser(){
        return  userList;
    }

    public LiveData<List<User>> getUserByName(String name){
        userList = db.getUserByName(name);
        return  userList;
    }

    private void addUser(User user){
        new insertAsyncTask(db).execute(user);
    }

    public void addUser(String name, String lastName){
        addUser(new User(name, lastName));
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao asyncTaskDao;

        insertAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.insertAll(users[0]);
            return null;
        }
    }
}
