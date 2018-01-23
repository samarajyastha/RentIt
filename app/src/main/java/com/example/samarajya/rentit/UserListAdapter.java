package com.example.samarajya.rentit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samarajya on 12/26/2017.
 */

public class UserListAdapter extends ArrayAdapter<AddInfo> {
    Context context;
    DatabaseHelper databaseHelper;


    public UserListAdapter(@NonNull Context context, ArrayList<AddInfo> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        databaseHelper = new DatabaseHelper(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        TextView title = view.findViewById(R.id.title);
        TextView location = view.findViewById(R.id.location);
        ImageView imageView = view.findViewById(R.id.image);
        final AddInfo info = getItem(position);
        if (info.image != null)
            imageView.setImageBitmap(AddActivity.getBitmap(info.image));
        title.setText(info.title);
        location.setText(info.location);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", info.id);
                context.startActivity(intent);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                showDialog(info.id);
                return false;
            }
        });
        return view;
    }

    public void showDialog(final String idel) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Are you sure you want to delete?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteItem(idel);
                if (context instanceof RentRoomActivity)
                    ((RentRoomActivity) context).refreshlist();

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
