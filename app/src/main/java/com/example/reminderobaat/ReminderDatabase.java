package com.example.reminderobaat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class ReminderDatabase extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ReminderDatabase";

    // Table name
    private static final String TABLE_REMINDERS = "ReminderTable";

    // Table Columns names
    private static final String Kolom_id = "id";
    private static final String Kolom_judul = "judul";
    private static final String Kolom_tanggal = "tanggal";
    private static final String Kolom_waktu = "waktu";
    private static final String Kolom_pengulangan = "pengulangan";
    private static final String Kolom_interval = "interval";
    private static final String Kolom_tipe_pengulangan = "tipe_pengulangan";
    private static final String Kolom_aktif = "aktif";

    public ReminderDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REMINDERS_TABLE = "CREATE TABLE " + TABLE_REMINDERS +
                "("
                + Kolom_id + " INTEGER PRIMARY KEY,"
                + Kolom_judul+ " TEXT,"
                + Kolom_tanggal + " TEXT,"
                + Kolom_waktu + " INTEGER,"
                + Kolom_pengulangan + " BOOLEAN,"
                + Kolom_interval+ " INTEGER,"
                + Kolom_tipe_pengulangan + " TEXT,"
                + Kolom_aktif + " BOOLEAN" + ")";
        db.execSQL(CREATE_REMINDERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDERS);

        // Create tables again
        onCreate(db);
    }

    // Adding new Reminder
    public int addReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Kolom_judul , reminder.getJudul());
        values.put(Kolom_tanggal , reminder.getTanggal());
        values.put(Kolom_waktu , reminder.getWaktu());
        values.put(Kolom_pengulangan , reminder.getPengulangan());
        values.put(Kolom_interval, reminder.getInterval());
        values.put(Kolom_tipe_pengulangan, reminder.getTipePengulangan());
        values.put(Kolom_aktif, reminder.getAktif());

        // Inserting Row
        long Id = db.insert(TABLE_REMINDERS, null, values);
        db.close();
        return (int) Id;
    }

    // Getting single Reminder
    public Reminder getReminder(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REMINDERS, new String[]
                        {
                                Kolom_id,
                                Kolom_judul,
                                Kolom_tanggal,
                                Kolom_waktu,
                                Kolom_pengulangan,
                                Kolom_interval,
                                Kolom_tipe_pengulangan,
                                Kolom_aktif
                        }, Kolom_id + "=?",

                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Reminder reminder = new Reminder(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7));

        return reminder;
    }

    // Getting all Reminders
    public List<Reminder> getAllReminders(){
        List<Reminder> reminderList = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + TABLE_REMINDERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Reminder reminder = new Reminder();
                reminder.setID(Integer.parseInt(cursor.getString(0)));
                reminder.setJudul(cursor.getString(1));
                reminder.setTanggal(cursor.getString(2));
                reminder.setWaktu(cursor.getString(3));
                reminder.setPengulangan(cursor.getString(4));
                reminder.setInterval(cursor.getString(5));
                reminder.setTipePengulangan(cursor.getString(6));
                reminder.setAktif(cursor.getString(7));

                // Adding Reminders to list
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }
        return reminderList;
    }

    // Getting Reminders Count
    public int getRemindersCount(){
        String countQuery = "SELECT * FROM " + TABLE_REMINDERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single Reminder
    public int updateReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Kolom_judul , reminder.getJudul());
        values.put(Kolom_tanggal , reminder.getTanggal());
        values.put(Kolom_waktu , reminder.getWaktu());
        values.put(Kolom_pengulangan , reminder.getPengulangan());
        values.put(Kolom_interval , reminder.getInterval());
        values.put(Kolom_tipe_pengulangan, reminder.getTipePengulangan());
        values.put(Kolom_aktif, reminder.getAktif());

        // Updating row
        return db.update(TABLE_REMINDERS, values, Kolom_id+ "=?",
                new String[]{String.valueOf(reminder.getID())});
    }

    // Deleting single Reminder
    public void deleteReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REMINDERS, Kolom_id + "=?",
                new String[]{String.valueOf(reminder.getID())});
        db.close();
    }
}
