package com.example.samarajya.rentit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by Samarajya on 12/2/2017.
 */

public class RentRoomActivity extends AppCompatActivity {
    FloatingActionButton addBtn;
    LinearLayout container;
    DatabaseHelper databaseHelper;
    String id;
    ListView listView;
    String item="room";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_room_layout);
        addBtn = findViewById(R.id.add_btn);
        container = findViewById(R.id.container);
        databaseHelper = new DatabaseHelper(this);
        listView=findViewById(R.id.listview);
        UserListAdapter adapter=new UserListAdapter(this,databaseHelper.getAdd(item));
        listView.setAdapter(adapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RentRoomActivity.this, AddActivity.class);
                intent.putExtra("item","room");
                intent.putExtra("header","Add a Room");
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
public void refreshlist(){
    UserListAdapter adapter = new UserListAdapter(this, databaseHelper.getAdd(item));
    listView.setAdapter(adapter);
}
    /*public void populateData() {
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
                    Intent intent = new Intent(RentRoomActivity.this, DetailActivity.class);
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
    }*/

    public void showDialog(final String idel) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Are you sure you want to delete?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteItem(idel);
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

