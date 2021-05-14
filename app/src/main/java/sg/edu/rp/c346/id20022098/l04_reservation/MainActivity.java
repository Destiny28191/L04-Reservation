package sg.edu.rp.c346.id20022098.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText fnText;
    EditText lnText;
    EditText phoneText;
    EditText gs;
    DatePicker dp;
    TimePicker tp;
    CheckBox smoke;
    Button reserve;
    Button reset;
    String sa ="";
    String timeString ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fnText = findViewById(R.id.editTextNameFirst);
        lnText = findViewById(R.id.editTextNameLast);
        phoneText = findViewById(R.id.editTextPhoneNumber);
        gs = findViewById(R.id.editTextGroupSize);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        smoke = findViewById(R.id.checkBoxSmokingArea);
        reserve = findViewById(R.id.buttonReserve);
        reset = findViewById(R.id.buttonReset);




        smoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(smoke.isChecked()){
                    sa ="Table in Smoking Area.";
                }else{
                    sa ="Table in Non-Smoking Area.";
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Input Cleared!", Toast.LENGTH_SHORT).show();
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5, 1);
                fnText.setText("");
                lnText.setText("");
                phoneText.setText("");
                gs.setText("");
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(tp.getCurrentHour() >= 12){
                    timeString = "PM";
                }else{
                    timeString = "AM";
                }
            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn = fnText.getText().toString();
                String ln = lnText.getText().toString();
                String pn = phoneText.getText().toString();
                String GS = gs.getText().toString();
                if(TextUtils.isEmpty(fn)) {
                    Toast.makeText(MainActivity.this, "You have not filled First Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ln)) {
                    Toast.makeText(MainActivity.this, "You have not filled Last Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pn)) {
                    Toast.makeText(MainActivity.this, "You have not filled Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(GS)) {
                    Toast.makeText(MainActivity.this, "You have not filled Group Size!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String message ="Name: " + fn + " " + ln + "\n" + "Phone Number: " + pn + "\n" + "Group Size: " + GS + "\n"
                        + "Date of Reservation: " + dp.getDayOfMonth() + "/" + dp.getMonth() + "/" + dp.getYear() + "\n" + "Reservation Time: " + tp.getCurrentHour() + ":" + tp.getCurrentMinute() + timeString + "\n";
                message += sa;
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}