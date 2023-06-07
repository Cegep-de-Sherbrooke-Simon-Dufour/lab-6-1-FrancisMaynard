package com.example.laboratoire5_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private String userName;
    private String userEmail;
    private UserAdapter adapter = new UserAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserListViewModel viewModel = new ViewModelProvider(this).get(UserListViewModel.class);
        viewModel.getUsers().observe(this,new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.submitList(new ArrayList<>(users));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView_Users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.callback = (user) -> {
            viewModel.deleteUser(user);
        };

        ActivityResultLauncher<Intent> _getUsersInfo = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            userName = data.getStringExtra("newUserName");
                            userEmail = data.getStringExtra("newUserEmail");
                            viewModel.addUser(userName, userEmail);
                        }
                    }
                });

        FloatingActionButton goAddUser = findViewById(R.id.fltActBtn_goAddUser);
        goAddUser.setOnClickListener(v -> {
            Intent newUser = new Intent(MainActivity.this, new_user_infos.class);
            _getUsersInfo.launch(newUser);
        });
    }
}