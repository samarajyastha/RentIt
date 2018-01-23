package com.example.samarajya.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Samarajya on 12/1/2017.
 */

public class RentActivity extends AppCompatActivity {

    LinearLayout room, vehicle, services, electro;
    ImageView search;
    EditText searchbox;
    RelativeLayout title;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_layout);
        room = findViewById(R.id.rent_room);
        vehicle = findViewById(R.id.rent_vehicle);
        services = findViewById(R.id.rent_services);
        electro = findViewById(R.id.rent_electro);
        search = findViewById(R.id.search);
        searchbox = findViewById(R.id.search_box);
        title = findViewById(R.id.title);
        databaseHelper = new DatabaseHelper(this);
        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentActivity.this, RentRoomActivity.class);
                startActivity(intent);
            }
        });
        vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentActivity.this, RentVehicleActivity.class);
                startActivity(intent);
            }
        });
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentActivity.this, RentServicesActivity.class);
                startActivity(intent);
            }
        });
        electro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentActivity.this, RentElectroActivity.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setVisibility(View.INVISIBLE);
                searchbox.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        searchbox.setVisibility(View.INVISIBLE);
    }

}
