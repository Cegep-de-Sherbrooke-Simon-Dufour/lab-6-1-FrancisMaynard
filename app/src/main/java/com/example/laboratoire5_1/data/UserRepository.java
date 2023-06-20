package com.example.laboratoire5_1.data;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private final UserDao userDao;

    private final LiveData<List<User>> usersLiveData;

    @Inject
    public UserRepository(UserDatabase userDatabase){
        userDao = userDatabase.getUsersDao();
        usersLiveData = userDao.getUsers();
    }

    public LiveData<List<User>> getLiveDataUsers(){
        return usersLiveData;
    }

    public LiveData<List<User>> getUsers(){
        return usersLiveData;
    }
    public void addUser(User user){
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insertUser((user));
        });
    }
    public void deleteUser(String email){
        Executors.newSingleThreadExecutor().execute(() -> {
           userDao.deleteUser(email);
        });
    }

    public LiveData<List<Rental>> getRentals(String userEmail){
       return userDao.loadUserAndRentals(userEmail);
    }
    public void addRental(Rental rental){
        Executors.newSingleThreadExecutor().execute(()-> {
            userDao.insertRental(rental);
        });
    }
    public void deleteRental (Rental rental){
        Executors.newSingleThreadExecutor().execute(()-> {
            userDao.deleteRental(rental);
        });
    }
}
