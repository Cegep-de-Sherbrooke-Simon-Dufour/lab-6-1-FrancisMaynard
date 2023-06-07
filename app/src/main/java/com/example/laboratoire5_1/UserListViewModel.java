package com.example.laboratoire5_1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserListViewModel extends ViewModel {

    private final UserRepository userRepository;
    @Inject
    public UserListViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public LiveData<List<User>> getUsers(){
        return userRepository.getLiveDataUsers();
    }

    public void addUser(String name, String email){
        userRepository.addUser(new User(name, email));
    }

    public void deleteUser(User user){
        userRepository.deleteUser(user);
    }
}
