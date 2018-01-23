package com.example.samarajya.rentit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Samarajya on 12/14/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "RentIt";
    static int version = 1;
    String createItemTable = "CREATE TABLE if not exists `rent`  (\n" +
            "\t `id`\t INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
            "\t `item`\t TEXT, \n" +
            "\t `title`\t TEXT, \n" +
            "\t `description`\t LONGTEXT,\n" +
            "\t `facilities`\t LONGTEXT,\n" +
            "\t `location`\t TEXT,\n" +
            "\t `contact`\t TEXT,\n" +
            "\t `image`\t BLOB\n" +
            " )";
    String createUserTable = "CREATE TABLE if not exists `user`  (\n" +
            "\t `id`\t INTEGER PRIMARY KEY AUTOINCREMENT,\n " +
            "\t `name`\t TEXT,\n" +
            "\t `email`\t TEXT,\n" +
            "\t `address`\t TEXT,\n" +
            "\t `phone`\t TEXT,\n" +
            "\t `username`\t TEXT, \n" +
            "\t `password`\t TEXT\n" +
            " )";

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createUserTable);
        getWritableDatabase().execSQL(createItemTable);

    }

    public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("user", "", contentValues);
    }
    public void insertItem(ContentValues contentValues) {
        getWritableDatabase().insert("rent", "", contentValues);
    }

    public void deleteItem(String id) {
        getWritableDatabase().delete("rent", "id=" + id, null);
    }


    public ArrayList<AddInfo> getAdd(String item) {
        String sql = "Select * from rent where item ='" + item + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        ArrayList<AddInfo> list = new ArrayList<AddInfo>();
        while (c.moveToNext()) {
            AddInfo info = new AddInfo();
            info.id = c.getString(c.getColumnIndex("id"));
            info.item = c.getString(c.getColumnIndex("item"));
            info.title = c.getString(c.getColumnIndex("title"));
            info.description = c.getString(c.getColumnIndex("description"));
            info.facilities = c.getString(c.getColumnIndex("facilities"));
            info.location = c.getString(c.getColumnIndex("location"));
            info.contact = c.getString(c.getColumnIndex("contact"));
            info.image = c.getBlob(c.getColumnIndex("image"));
            list.add(info);
        }
        c.close();
        return list;

    }

    public AddInfo getAddInfo(String id) {
        String sql = "Select * from rent where id=" + id;
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        AddInfo info = new AddInfo();
        while (c.moveToNext()) {
            info.id = c.getString(c.getColumnIndex("id"));
            info.item = c.getString(c.getColumnIndex("item"));
            info.title = c.getString(c.getColumnIndex("title"));
            info.description = c.getString(c.getColumnIndex("description"));
            info.facilities = c.getString(c.getColumnIndex("facilities"));
            info.location = c.getString(c.getColumnIndex("location"));
            info.contact = c.getString(c.getColumnIndex("contact"));
            info.image = c.getBlob(c.getColumnIndex("image"));
        }
        c.close();
        return info;
    }

    public ArrayList<UserInfo> getUser() {
        String sql = "Select * from user";
        ArrayList<UserInfo> list = new ArrayList<UserInfo>();
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()) {
            UserInfo info = new UserInfo();
            info.id = c.getString(c.getColumnIndex("id"));
            info.name = c.getString(c.getColumnIndex("name"));
            info.email = c.getString(c.getColumnIndex("email"));
            info.address = c.getString(c.getColumnIndex("address"));
            info.phone = c.getString(c.getColumnIndex("phone"));
            info.username = c.getString(c.getColumnIndex("username"));
            info.password = c.getString(c.getColumnIndex("password"));
            list.add(info);
        }
        c.close();
        return list;

    }

    public ArrayList<String> getUserNameList() {
        String sql = "Select * from user";
        ArrayList<String> list = new ArrayList<String>();
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()) {

            list.add(c.getString(c.getColumnIndex("username")));
        }
        c.close();
        return list;

    }

    public boolean isValidLogin(String username, String password) {
        String sql = "Select count(*) from user where username='" + username + "' and password='" + password + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        if (l == 1) {
            return true;
        }
        return false;
    }

    public UserInfo getUserInfo(String id) {
        String sql = "Select * from user where id=" + id;
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        UserInfo info = new UserInfo();
        while (c.moveToNext()) {
            info.id = c.getString(c.getColumnIndex("id"));
            info.name = c.getString(c.getColumnIndex("name"));
            info.email = c.getString(c.getColumnIndex("email"));
            info.address = c.getString(c.getColumnIndex("address"));
            info.phone = c.getString(c.getColumnIndex("phone"));
            info.username = c.getString(c.getColumnIndex("username"));
            info.password = c.getString(c.getColumnIndex("password"));
        }
        c.close();
        return info;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
