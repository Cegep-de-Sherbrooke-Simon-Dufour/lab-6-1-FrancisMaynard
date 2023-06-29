package com.example.laboratoire5_1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.laboratoire5_1.data.Rental;
import com.example.laboratoire5_1.data.User;
import com.example.laboratoire5_1.data.UserRepository;

import java.util.List;
import java.util.Map;

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
    public void deleteUser(String email){
        userRepository.deleteUser(email);
    }

    public LiveData<List<Rental>> getRentals(String userEmail){
        return userRepository.getRentals(userEmail);
    }
    public void addRental(String userEmail, String newRentalName){

        userRepository.addRental(new Rental(userEmail, newRentalName));
    }
    public void deleteRental (Rental rental){
        userRepository.deleteRental(rental);
    }
}
