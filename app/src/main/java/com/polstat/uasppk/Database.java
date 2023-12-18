package com.polstat.uasppk;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE users(username text, email text, password text, full_name text, nip text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "CREATE TABLE appPlace(username text, fullName text, nim text, roll text, contact text, ipk text, date text, time text)";
        sqLiteDatabase.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;
    }

    public String[] getUserInfo(String username) {
        String[] userInfo = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username, email, full_name, nip FROM users WHERE username = ?", new String[]{username});

        if (cursor.getCount() > 0) {
            userInfo = new String[4];
            cursor.moveToFirst();

            int usernameIndex = cursor.getColumnIndex("username");
            int emailIndex = cursor.getColumnIndex("email");
            int fullNameIndex = cursor.getColumnIndex("full_name");
            int nipIndex = cursor.getColumnIndex("nip");

            userInfo[0] = (usernameIndex != -1) ? cursor.getString(usernameIndex) : "";
            userInfo[1] = (emailIndex != -1) ? cursor.getString(emailIndex) : "";
            userInfo[2] = (fullNameIndex != -1) ? cursor.getString(fullNameIndex) : "";
            userInfo[3] = (nipIndex != -1) ? cursor.getString(nipIndex) : "";
        }

        cursor.close();
        db.close();

        return userInfo;
    }

    public String getPassword(String username) {
        String password = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT password FROM users WHERE username = ?", new String[]{username});

        if (cursor.moveToFirst()) {
            int passwordIndex = cursor.getColumnIndex("password");
            password = (passwordIndex != -1) ? cursor.getString(passwordIndex) : "";
        }

        cursor.close();
        db.close();

        return password;
    }

    public void updateUserProfile(String username, String newFullName, String newNip) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("full_name", newFullName);
        cv.put("nip", newNip);
        db.update("users", cv, "username=?", new String[]{username});
        db.close();
    }

    public void updateUsernameAndEmail(String oldUsername, String newUsername, String newEmail) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", newUsername);
        cv.put("email", newEmail);

        db.update("users", cv, "username=?", new String[]{oldUsername});
        db.close();
    }

    public void addAppointment(String username, String fullName, String nim, String roll, String contact, String ipk, String date, String time) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullName", fullName);
        cv.put("nim", nim);
        cv.put("roll", roll);
        cv.put("contact", contact);
        cv.put("ipk", ipk);
        cv.put("date", date);
        cv.put("time", time);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("appPlace", null, cv);
        db.close();
    }

    public ArrayList getAppointmentData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("SELECT * FROM appPlace WHERE username = ?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" + c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6)+ "$" + c.getString(7));
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExists(String username, String fullName, String nim, String roll, String contact, String ipk, String date, String time) {
        int result = 0;
        String str[] = new String[8];
        str[0] = username;
        str[1] = fullName;
        str[2] = nim;
        str[3] = roll;
        str[4] = contact;
        str[5] = ipk;
        str[6] = date;
        str[7] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM appPlace WHERE username = ? AND fullName = ? AND nim = ? AND roll = ? AND contact = ? AND ipk = ? AND date = ? AND time = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }

}
