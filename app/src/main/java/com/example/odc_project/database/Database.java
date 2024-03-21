package com.example.odc_project.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.odc_project.Contact;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, address TEXT, email TEXT, addedDate TEXT, isFavorite INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", contact.getName());
        values.put("phone", contact.getPhone());
        values.put("address", contact.getAddress());
        values.put("email", contact.getEmail());
        values.put("isFavorite", contact.isFavorite());

        long rowId = db.insert("contacts", null, values);
        db.close();

        return rowId;
    }

    @SuppressLint("Range")
    public List<Contact> getAll() {
        ArrayList<Contact> contacts = null;
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query("contacts", null, null, null, null, null, null);

        if (cursor != null && cursor.getColumnCount() > 0) {
            contacts = new ArrayList<>();
            while(cursor.moveToNext()) {
                Contact contact = new Contact();

                contact.setName(cursor.getString(cursor.getColumnIndex("name")));
                contact.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                contact.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                contact.setAddedDate(cursor.getString(cursor.getColumnIndex("addedDate")));
                contact.setIsFavorite(cursor.getInt(cursor.getColumnIndex("isFavorite")) != 0);

                if (cursor.getString(cursor.getColumnIndex("address")) != null) {
                    contact.setAddress(cursor.getColumnName(cursor.getColumnIndex("address")));
                }

                contacts.add(contact);
            }
        } else {
            Log.e("DATABASE", "ERREUR CURSOR");
        }

        return contacts;
    }
}
