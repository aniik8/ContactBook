package com.example.contactbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class databaseclass extends SQLiteOpenHelper
{   private Context context;
    private static final String DATABASE_NAME = "ContactBook.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_contacts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "contact_name";
    private static final String COLUMN_NUMBER = "contact_number";
    private static final String COLUMN_DATE = "contact_dob";
    private static final String COLUMN_EMAIL = "contact_email";
    databaseclass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_NUMBER + " " +
                " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_EMAIL +" TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void add_contact(String name, String number, String dob, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_NUMBER, number);
        cv.put(COLUMN_DATE, dob);
        cv.put(COLUMN_EMAIL, email);
        long result = db.insert(TABLE_NAME, null, cv);

        if (result==-1){
            Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Contact added successfully", Toast.LENGTH_SHORT).show();

        }
    }
    Cursor read_data()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void update_data(String row_id ,String name, String number, String email, String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_NUMBER, number);
        cv.put(COLUMN_DATE, dob);
        cv.put(COLUMN_EMAIL, email);
        long results = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(results==-1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }
    void delete_data(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long results = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(results==-1)
        {
            Toast.makeText(context, "Sorry, FAILED TO DELETED.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully deleted.", Toast.LENGTH_SHORT).show();
        }
    }
    void delete_all_data(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME);
    }
}
