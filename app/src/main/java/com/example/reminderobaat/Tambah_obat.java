package com.example.reminderobaat;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class Tambah_obat extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{

    private Toolbar mToolbar;
    private EditText mJudulText;
    private TextView mTanggalText, mWaktuText, mPengulanganText, mIntervalText, mTipePengulanganText;
    private FloatingActionButton mFAB1;
    private FloatingActionButton mFAB2;
    private Calendar mCalendar;
    private int mYear, mMonth, mHour, mMinute, mDay;
    private long mRepeatTime;
    private String mJudul;
    private String mWaktu;
    private String mTanggal;
    private String mPengulangan;
    private String mInterval;
    private String mTipePengulangan;
    private String mAktif;

    // Values for orientation change
    private static final String Kolom_judul = "judul";
    private static final String Kolom_tanggal = "tanggal";
    private static final String Kolom_waktu = "waktu";
    private static final String Kolom_pengulangan = "pengulangan";
    private static final String Kolom_interval = "interval";
    private static final String Kolom_tipe_pengulangan = "tipe_pengulangan";
    private static final String Kolom_aktif = "aktif";

    // Constant values in milliseconds
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_obat);

        // Initialize Views
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mJudulText = (EditText) findViewById(R.id.reminder_title);
        mTanggalText = (TextView) findViewById(R.id.set_tanggal);
        mWaktuText = (TextView) findViewById(R.id.set_time);
        mPengulanganText = (TextView) findViewById(R.id.set_repeat);
        mIntervalText = (TextView) findViewById(R.id.set_repeat_no);
        mTipePengulanganText = (TextView) findViewById(R.id.set_Tipe_pengulangan);
        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);

        // Setup Toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.title_activity_add_reminder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Initialize default values
        mAktif = "true";
        mPengulangan = "true";
        mInterval = Integer.toString(1);
        mTipePengulangan = "Jam";

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DATE);

        mTanggal = mDay + "/" + mMonth + "/" + mYear;
        mWaktu = mHour + ":" + mMinute;

        // Setup Reminder Title EditText
        mJudulText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mJudul = s.toString().trim();
                mJudulText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Setup TextViews using reminder values
        mTanggalText.setText(mTanggal);
        mWaktuText.setText(mWaktu);
        mIntervalText.setText(mInterval);
        mTipePengulanganText.setText(mTipePengulangan);
        mPengulanganText.setText("Akan Berulang Setiap " + mInterval + " " + mTipePengulangan + " ");

        // To save state on device rotation
        if (savedInstanceState != null) {
            String savedTitle = savedInstanceState.getString(Kolom_judul);
            mJudulText.setText(savedTitle);
            mJudul = savedTitle;

            String savedTime = savedInstanceState.getString(Kolom_waktu);
            mWaktuText.setText(savedTime);
            mWaktu = savedTime;

            String savedDate = savedInstanceState.getString(Kolom_tanggal);
            mTanggalText.setText(savedDate);
            mTanggal = savedDate;

            String saveRepeat = savedInstanceState.getString(Kolom_pengulangan);
            mPengulanganText.setText(saveRepeat);
            mPengulangan = saveRepeat;

            String savedRepeatNo = savedInstanceState.getString(Kolom_interval);
            mIntervalText.setText(savedRepeatNo);
            mInterval = savedRepeatNo;

            String savedRepeatType = savedInstanceState.getString(Kolom_tipe_pengulangan);
            mTipePengulanganText.setText(savedRepeatType);
            mTipePengulangan = savedRepeatType;

            mAktif = savedInstanceState.getString(Kolom_aktif);
        }

        // Setup up active buttons
        if (mAktif.equals("false")) {
            mFAB1.show();
            mFAB2.hide();

        } else if (mAktif.equals("true")) {
            mFAB1.hide();
            mFAB2.show();
        }
    }


    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(Kolom_judul, mJudulText.getText());
        outState.putCharSequence(Kolom_waktu, mWaktuText.getText());
        outState.putCharSequence(Kolom_tanggal, mTanggalText.getText());
        outState.putCharSequence(Kolom_pengulangan, mPengulanganText.getText());
        outState.putCharSequence(Kolom_interval, mIntervalText.getText());
        outState.putCharSequence(Kolom_tipe_pengulangan, mTipePengulanganText.getText());
        outState.putCharSequence(Kolom_aktif, mAktif);
    }

    // On clicking Time picker
    public void setWaktu(View v){
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.setThemeDark(false);
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    // On clicking Date picker
    public void setTanggal(View v){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    // Obtain time from time picker
    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;
        if (minute < 10) {
            mWaktu = hourOfDay + ":" + "0" + minute;
        } else {
            mWaktu = hourOfDay + ":" + minute;
        }
        mWaktuText.setText(mWaktu);
    }

    // Obtain date from date picker
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear ++;
        mDay = dayOfMonth;
        mMonth = monthOfYear;
        mYear = year;
        mTanggal = dayOfMonth + "/" + monthOfYear + "/" + year;
        mTanggalText.setText(mTanggal);
    }

    // On clicking the active button
    public void selectFab1(View v) {
        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
        mFAB1.hide();
        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
        mFAB2.show();
        mAktif = "true";
    }

    // On clicking the inactive button
    public void selectFab2(View v) {
        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
        mFAB2.hide();
        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
        mFAB1.show();
        mAktif = "false";
    }

    // On clicking the repeat switch
    public void onSwitchRepeat(View view) {
        boolean on = ((Switch) view).isChecked();
        if (on) {
            mPengulangan = "true";
            mPengulanganText.setText("Akan Berulang Setiap " + mInterval + " " + mTipePengulangan + " ");
        } else {
            mPengulangan = "false";
            mPengulanganText.setText(R.string.repeat_off);
        }
    }

    // On clicking repeat type button
    public void selectTipePengulangan(View v){
        final String[] items = new String[2];

        items[0] = "Menit";
        items[1] = "Jam";

        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Type");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                mTipePengulangan = items[item];
                mTipePengulanganText.setText(mTipePengulangan);
                mPengulanganText.setText("Akan Berulang Setiap " + mInterval + " " + mTipePengulangan + " ");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // On clicking repeat interval button
    public void setInterval(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Masukkan Angka");

// Create EditText box to input repeat number
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setView(input);
        alert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (input.getText().toString().length() == 0) {
                            mInterval = Integer.toString(1);
                            mIntervalText.setText(mInterval);
                            mPengulanganText.setText("Akan Berulang Setiap " + mInterval + " " + mTipePengulangan + " ");
                        }
                        else {
                            mInterval = input.getText().toString().trim();
                            mIntervalText.setText(mInterval);
                            mPengulanganText.setText("Akan Berulang Setiap " + mInterval + " " + mTipePengulangan + " ");
                        }
                    }
                });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // do nothing
            }
        });
        alert.show();
    }






    // On clicking the save button
    public void saveReminder(){
        ReminderDatabase rb = new ReminderDatabase(this);

        // Creating Reminder
        int ID = rb.addReminder(new Reminder(mJudul, mTanggal, mWaktu, mPengulangan, mInterval, mTipePengulangan,mAktif));

        // Set up calender for creating the notification
        mCalendar.set(Calendar.MONTH, --mMonth);
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);

        // Check repeat type
        if (mTipePengulangan.equals("Minute")) {
            mRepeatTime = Integer.parseInt(mInterval) * milMinute;
        } else if (mTipePengulangan.equals("Hour")) {
            mRepeatTime = Integer.parseInt(mInterval) * milHour;
        } else if (mTipePengulangan.equals("Day")) {
            mRepeatTime = Integer.parseInt(mInterval) * milDay;
        } else if (mTipePengulangan.equals("Week")) {
            mRepeatTime = Integer.parseInt(mInterval) * milWeek;
        } else if (mTipePengulangan.equals("Month")) {
            mRepeatTime = Integer.parseInt(mInterval) * milMonth;
        }

        // Create a new notification
        if (mAktif.equals("true")) {
            if (mPengulangan.equals("true")) {
                new AlarmReceiver().setRepeatAlarm(getApplicationContext(), mCalendar, ID, mRepeatTime);
            } else if (mPengulangan.equals("false")) {
                new AlarmReceiver().setAlarm(getApplicationContext(), mCalendar, ID);
            }
        }

        // Create toast to confirm new reminder
        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_SHORT).show();

        onBackPressed();
    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // Creating the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tambah_obat, menu);
        return true;
    }

    // On clicking menu buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // On clicking the back arrow
            // Discard any changes
            case android.R.id.home:
                onBackPressed();
                return true;

            // On clicking save reminder button
            // Update reminder
            case R.id.save_reminder:
                mJudulText.setText(mJudul);

                if (mJudulText.getText().toString().length() == 0)
                    mJudulText.setError("Judul jangan kosong");

                else {
                    saveReminder();
                }
                return true;

            // On clicking discard reminder button
            // Discard any changes
            case R.id.discard_reminder:
                Toast.makeText(getApplicationContext(), "Batal",
                        Toast.LENGTH_SHORT).show();

                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

