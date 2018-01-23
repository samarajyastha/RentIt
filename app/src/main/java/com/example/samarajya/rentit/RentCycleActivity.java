package com.example.samarajya.rentit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samarajya on 12/2/2017.
 */

public class RentCycleActivity extends AppCompatActivity {
    FloatingActionButton addBtn;
    LinearLayout container;
    DatabaseHelper databaseAdd;
    String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_cycle_layout);
        addBtn = findViewById(R.id.add_btn);
        container = findViewById(R.id.container);
        databaseAdd = new DatabaseHelper(this);
        populateData();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentCycleActivity.this, AddActivity.class);
                intent.putExtra("item", "cycle");
                intent.putExtra("header", "Add a Cycle");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }

    public void populateData() {
        String item = "cycle";
        ArrayList<AddInfo> list = databaseAdd.getAdd(item);
        container.removeAllViews();
        for (final AddInfo info : list
                ) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
            TextView title = view.findViewById(R.id.title);
            TextView location = view.findViewById(R.id.location);
            ImageView imageView = view.findViewById(R.id.image);
            if (info.image != null)
                imageView.setImageBitmap(AddActivity.getBitmap(info.image));
            title.setText(info.title);
            location.setText(info.location);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RentCycleActivity.this, DetailActivity.class);
                    intent.putExtra("id", info.id);
                    startActivity(intent);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    showDialog(info.id);
                    return false;
                }
            });
            container.addView(view);
        }
    }

    public void showDialog(final String idel) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Are you sure you want to delete?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //databaseAdd.deleteUser(idel);
                populateData();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
