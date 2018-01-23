package com.example.samarajya.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Samarajya on 12/2/2017.
 */

public class RentVehicleActivity extends AppCompatActivity {

    LinearLayout bike, car, bus, cycle, van;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_vehicle_layout);
        bike = findViewById(R.id.bike);
        car = findViewById(R.id.car);
        bus = findViewById(R.id.bus);
        cycle = findViewById(R.id.cycle);
        van = findViewById(R.id.van);
        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentVehicleActivity.this, RentBikeActivity.class);
                startActivity(intent);

            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentVehicleActivity.this, RentCarActivity.class);
                startActivity(intent);

            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentVehicleActivity.this, RentBusActivity.class);
                startActivity(intent);

            }
        });
        cycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentVehicleActivity.this, RentCycleActivity.class);
                startActivity(intent);

            }
        });

        van.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentVehicleActivity.this, RentVanActivity.class);
                startActivity(intent);

            }
        });
    }
}
