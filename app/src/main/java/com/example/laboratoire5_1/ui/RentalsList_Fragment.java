package com.example.laboratoire5_1.ui;

import android.os.Bundle;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.laboratoire5_1.R;
import com.example.laboratoire5_1.UserListViewModel;
import com.example.laboratoire5_1.adapter.RentalAdapter;
import com.example.laboratoire5_1.data.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalsList_Fragment extends Fragment {

    RentalAdapter rentalAdapter = new RentalAdapter();
    TextView txtVw_userEmail;
    TextView txtVw_userName;
    String userName;
    String userEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_rental, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userName = getArguments().getString("userName");
        userEmail = getArguments().getString("userEmail");

        txtVw_userName = view.findViewById((R.id.textView_UserName));
        txtVw_userName.setText(userName);

        txtVw_userEmail = view.findViewById((R.id.textView_rentalUserEmail));
        txtVw_userEmail.setText(userEmail);

        UserListViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserListViewModel.class);
        viewModel.getRentals(txtVw_userEmail.getText().toString()).observe(getViewLifecycleOwner(), new Observer<List<Rental>>(){
            @Override
            public void onChanged(List<Rental> rentals) { rentalAdapter.submitList(new ArrayList<>(rentals));}
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_rentalsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(rentalAdapter);
        rentalAdapter.callback = (rental) -> {
            viewModel.deleteRental(rental);
        };

        Button addRental = view.findViewById(R.id.btn_addRental);
        addRental.setOnClickListener(v -> {
            EditText rentalName = view.findViewById(R.id.editText_rentalName);
            String newRentalName = rentalName.getText().toString();
            viewModel.addRental(userEmail,newRentalName);
        });

        Button deleteUser = view.findViewById((R.id.btn_deleteUser));
        deleteUser.setOnClickListener(v -> {
            viewModel.deleteUser(userEmail);
            Navigation.findNavController(view).navigateUp();
        });
    }
}