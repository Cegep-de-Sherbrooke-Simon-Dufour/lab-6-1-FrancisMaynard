package com.example.laboratoire5_1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UsersList_Fragment extends Fragment {
    private String userName;
    private String userEmail;
    private UserAdapter adapter = new UserAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users_list_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserListViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserListViewModel.class);
        viewModel.getUsers().observe(getViewLifecycleOwner(),new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.submitList(new ArrayList<>(users));
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.users_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.callback = (user) -> {
            viewModel.deleteUser(user);
        };

        FloatingActionButton goAddUser = view.findViewById(R.id.fab_goToAddNewUser);
        goAddUser.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_usersList_Fragment_to_addNewUser_Fragment);
        });

    }
}