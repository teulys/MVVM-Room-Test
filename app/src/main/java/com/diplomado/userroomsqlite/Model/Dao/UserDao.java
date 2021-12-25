package com.diplomado.userroomsqlite.Model.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.diplomado.userroomsqlite.Model.Entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllLiveData();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);


    @Query("SELECT * FROM user WHERE first_name LIKE :first ")
    LiveData<List<User>> getUserByName(String first);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

}
