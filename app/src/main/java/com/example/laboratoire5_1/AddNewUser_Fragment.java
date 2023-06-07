package com.example.laboratoire5_1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AddNewUser_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_user_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserListViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserListViewModel.class);

        Button btnAdd = view.findViewById(R.id.btn_addNewUser);
        btnAdd.setOnClickListener(v -> {
            EditText userName = view.findViewById(R.id.txtVw_newUserName);
            String newUserName = userName.getText().toString();
            EditText userEmail = view.findViewById(R.id.txtVw_newUserEmail);
            String newUserEmail = userEmail.getText().toString();

            viewModel.addUser(newUserName, newUserEmail);
            Navigation.findNavController(view).navigateUp();
        });

        Button btnCancel = view.findViewById(R.id.btn_cancelAdd);
        btnCancel.setOnClickListener(v -> {
            Navigation.findNavController(view).navigateUp();
        });

    }
}