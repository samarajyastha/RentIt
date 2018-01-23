package com.example.samarajya.rentit;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by Samarajya on 12/1/2017.
 */

public class SignUpActivity extends AppCompatActivity {
    EditText name, email, address, username, password, phone;
    Button signup;
    ImageView back;
    DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        databaseHelper = new DatabaseHelper(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        signup = findViewById(R.id.signup);
        back = findViewById(R.id.back_btn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameValue = name.getText().toString();
                String emailValue = email.getText().toString();
                String addressValue = address.getText().toString();
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String phoneValue = phone.getText().toString();
                if (nameValue.matches("") || emailValue.matches("") || usernameValue.matches("") || passwordValue.matches("")) {

                    Toast.makeText(SignUpActivity.this, "Field Empty", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", nameValue);
                    contentValues.put("email", emailValue);
                    contentValues.put("address", addressValue);
                    contentValues.put("phone", phoneValue);
                    contentValues.put("username", usernameValue);
                    contentValues.put("password", passwordValue);
                    databaseHelper.insertUser(contentValues);

                    Toast.makeText(SignUpActivity.this, "User saved", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
