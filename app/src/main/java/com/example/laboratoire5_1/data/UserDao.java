package com.example.laboratoire5_1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laboratoire5_1.data.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    LiveData<List<User>> getUsers();

    @Insert
    void insertUser (User user);

    @Delete
    void deleteUser(User user);
}

