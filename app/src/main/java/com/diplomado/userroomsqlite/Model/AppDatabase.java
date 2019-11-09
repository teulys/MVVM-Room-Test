package com.diplomado.userroomsqlite.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.diplomado.userroomsqlite.Model.Dao.UserDao;
import com.diplomado.userroomsqlite.Model.Entities.User;

@Database(entities = { User.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "user_database")
                    .build();
        }
        return INSTANCE;
    }
}
