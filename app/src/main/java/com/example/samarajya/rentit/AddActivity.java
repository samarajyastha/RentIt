package com.example.samarajya.rentit;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by Samarajya on 12/14/2017.
 */

public class AddActivity extends AppCompatActivity {

    EditText title, description, facilities, location, contact;
    Button uploadBtn, submit;
    ImageView image;
    DatabaseHelper databaseHelper;
    LinearLayout addPhoto;
    TextView header;
    TextView gallery, camera, cancel;
    int id;
    String gotItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);
        databaseHelper = new DatabaseHelper(this);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        facilities = findViewById(R.id.facilities);
        location = findViewById(R.id.location);
        contact = findViewById(R.id.contact);
        header = findViewById(R.id.header);
        image = findViewById(R.id.image);
        uploadBtn = findViewById(R.id.upload_btn);
        submit = findViewById(R.id.submit);
        image = findViewById(R.id.image);

        gotItem = getIntent().getStringExtra("item");
        header.setText(getIntent().getStringExtra("header"));
        AddInfo info = databaseHelper.getAddInfo(id + "");
        title.setText(info.title);
        description.setText(info.description);
        facilities.setText(info.facilities);
        location.setText(info.location);
        contact.setText(info.contact);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleValue = title.getText().toString();
                String descriptionValue = description.getText().toString();
                String facilitiesValue = facilities.getText().toString();
                String locationValue = location.getText().toString();
                String contactValue = contact.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put("title", titleValue);
                contentValues.put("description", descriptionValue);
                contentValues.put("facilities", facilitiesValue);
                contentValues.put("location", locationValue);
                contentValues.put("contact", contactValue);
                contentValues.put("image", getBlob(bitmap));
                contentValues.put("item", gotItem);

                databaseHelper.insertItem(contentValues);
                Toast.makeText(AddActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 137) {
                bitmap = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bitmap);
            } else if (requestCode == 138) {
                Uri uri = data.getData();
                try {
                    bitmap = getBitmapFromUri(uri);
                    image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static byte[] getBlob(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    public static Bitmap getBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialogue_layout, null);
        LinearLayout camera = view.findViewById(R.id.camera);
        LinearLayout gallery = view.findViewById(R.id.gallery);
        TextView message =view.findViewById(R.id.message);
        ImageView cancel = view.findViewById(R.id.cancel);
        message.setText("Add Photo!");

        camera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 137);
                dialog.dismiss();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 138);
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setContentView(view);
        dialog.show();
    }

}
