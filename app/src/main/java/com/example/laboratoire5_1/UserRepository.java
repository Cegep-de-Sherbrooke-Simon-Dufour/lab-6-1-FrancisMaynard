package com.example.laboratoire5_1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {
    @Inject
    public UserRepository(){}

    private final ArrayList<User> users = new ArrayList<>();
    private final MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>(users);

    public LiveData<List<User>> getLiveDataUsers(){
        return usersLiveData;
    }

    public void addUser(User user){
        users.add(user);
        usersLiveData.setValue(users);
    }

    public void deleteUser(User user){
        users.remove(user);
        usersLiveData.setValue(users);
    }

    public List<User> getUsers(){
        return users;
    }

}
