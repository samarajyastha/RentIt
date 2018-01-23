package com.example.samarajya.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Samarajya on 12/2/2017.
 */

public class RentServicesActivity extends AppCompatActivity {

    LinearLayout music, cafe, fashion, land;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_services_layout);
        music = findViewById(R.id.music);
        cafe = findViewById(R.id.cafe);
        fashion = findViewById(R.id.fashion);
        land = findViewById(R.id.land);

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentServicesActivity.this, RentMusicActivity.class);
                startActivity(intent);
            }
        });
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentServicesActivity.this, RentCafeActivity.class);
                startActivity(intent);
            }
        });
        fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentServicesActivity.this, RentFashionActivity.class);
                startActivity(intent);
            }
        });
        land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentServicesActivity.this, RentLandActivity.class);
                startActivity(intent);
            }
        });

    }
}
