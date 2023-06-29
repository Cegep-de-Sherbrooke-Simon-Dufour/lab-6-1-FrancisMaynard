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
    public void addUser(User user){
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insertUser((user));
        });
    }
    public void deleteUser(User user){
        Executors.newSingleThreadExecutor().execute(() -> {
           userDao.deleteUser(user);
        });
    }

    public LiveData<List<User>> getUsers(){
        return usersLiveData;
    }
}
