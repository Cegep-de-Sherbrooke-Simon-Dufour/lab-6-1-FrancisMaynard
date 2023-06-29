package com.example.laboratoire5_1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laboratoire5_1.data.User;

import java.util.List;
import java.util.Map;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    LiveData<List<User>> getUsers();

    @Insert
    void insertUser (User user);

    @Query("DELETE FROM User WHERE email = :email")
    void deleteUser(String email);

    @Query("SELECT * FROM Rental WHERE userEmail = :userEmail")
    public LiveData<List<Rental>> loadUserAndRentals(String userEmail);

    @Insert
    void insertRental (Rental rental);

    @Delete
    void deleteRental (Rental rental);
}

