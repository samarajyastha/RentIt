package com.example.samarajya.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Thread.sleep;

/**
 * Created by Samarajya on 11/30/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText  password;
    AutoCompleteTextView username;
    Button login, signup;
    CheckBox remember;
    DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        databaseHelper = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        remember = findViewById(R.id.remember);
        populateData();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        populateData();

    }
    public void populateData(){
        username.setAdapter(new AutocompleteActivity(this, databaseHelper.getUserNameList()));
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                if(usernameValue.trim().length()>0) {

                    if (databaseHelper.isValidLogin(usernameValue, passwordValue)) {
                        Intent intent = new Intent(LoginActivity.this, RentActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failure", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    username.setError("Enter Username!");
                }
            }

        });
        signup.setOnClickListener(this);
    }

}
