package com.example.samarajya.rentit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity{

    LinearLayout container;
    DatabaseHelper databaseHelper;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        container = findViewById(R.id.container);
        databaseHelper = new DatabaseHelper(this);
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        populateData();
    }

    public void populateData() {
        final AddInfo info = databaseHelper.getAddInfo(id + "");
        TextView title = findViewById(R.id.title);
        title.setText(info.title);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail, null);
        TextView location = view.findViewById(R.id.location);
        final TextView description = view.findViewById(R.id.description);
        final TextView facilities = view.findViewById(R.id.facilities);
        final TextView text = view.findViewById(R.id.text);
        ImageView imageView = view.findViewById(R.id.image);
        @SuppressLint("WrongViewCast")
        Button contact = view.findViewById(R.id.contact);
        imageView.setImageBitmap(AddActivity.getBitmap(info.image));
        location.setText(info.location);
        text.setText(info.description);
        description.setBackgroundColor(Color.WHITE);
        facilities.setBackgroundColor(Color.WHITE);
        description.setBackgroundResource(R.drawable.detail_btn);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setBackgroundResource(R.drawable.detail_btn);
                facilities.setBackgroundResource(0);
                text.setText(info.description);
            }
        });
        facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facilities.setBackgroundResource(R.drawable.detail_btn);
                description.setBackgroundResource(0);
                text.setText(info.facilities);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+info.contact));
                startActivity(intent);
            }
        });
        container.addView(view);
    }

}
