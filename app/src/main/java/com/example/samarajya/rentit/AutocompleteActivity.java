package com.example.samarajya.rentit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samarajya on 12/19/2017.
 */

public class AutocompleteActivity extends ArrayAdapter<String> {
    Context context;

    public AutocompleteActivity(Context context, ArrayList<String> list) {
        super(context, 0, list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setText(getItem(position));
        view.setPadding(10, 10, 10, 10);
        return view;
    }
}
