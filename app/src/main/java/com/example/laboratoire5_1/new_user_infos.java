package com.example.laboratoire5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class new_user_infos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_infos);

        Button btnAdd = findViewById(R.id.btn_addNewUser);
        btnAdd.setOnClickListener(sendNewUserInfos);
        Button btnCancel = findViewById(R.id.btn_cancelAdd);
        btnCancel.setOnClickListener(cancelNewUser);
    }

    View.OnClickListener sendNewUserInfos = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            EditText userName = findViewById(R.id.txtVw_newUserName);
            String newUserName = userName.getText().toString();
            EditText userEmail = findViewById(R.id.txtVw_newUserEmail);
            String newUserEmail = userEmail.getText().toString();

            Intent resultUserInfos = new Intent();
            resultUserInfos.putExtra("newUserName", newUserName);
            resultUserInfos.putExtra("newUserEmail", newUserEmail);
            setResult(RESULT_OK, resultUserInfos);

            finish();
        }
    };
    View.OnClickListener cancelNewUser = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();
        }
    };

}