package com.example.practicalwork13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private String selectedDate;
    private String selectedTime;
    private Button openDatePickerButton;
    private Button openTimePickerButton;
    private Calendar calendar;
    private Button openConfirmationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDatePickerButton = findViewById(R.id.openDatePickerButton);
        openTimePickerButton = findViewById(R.id.openTimePickerButton);
        calendar = Calendar.getInstance();

        openDatePickerButton.setOnClickListener(v -> showDatePickerDialog());

        openTimePickerButton.setOnClickListener(v -> showTimePickerDialog());

        openConfirmationButton = findViewById(R.id.openConfirmationButton);

        openConfirmationButton.setOnClickListener(v -> showConfirmationDialog());
    }

    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, monthOfYear, dayOfMonth1) -> {
                    selectedDate = dayOfMonth1 + "/" + (monthOfYear + 1) + "/" + year1;
                    Toast.makeText(MainActivity.this, "Выбрана дата: " + selectedDate, Toast.LENGTH_SHORT).show();
                },
                year,
                month,
                dayOfMonth
        );
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay1, minute1) -> {
                    selectedTime = hourOfDay1 + ":" + minute1;
                    Toast.makeText(MainActivity.this, "Выбрано время: " + selectedTime, Toast.LENGTH_SHORT).show();
                },
                hourOfDay,
                minute,
                true
        );
        timePickerDialog.show();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialogStyle);
        View customView = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
        builder.setView(customView);

        ImageView alertIcon = customView.findViewById(R.id.alertIcon);
        TextView alertTitle = customView.findViewById(R.id.alertTitle);
        TextView alertMessage = customView.findViewById(R.id.alertMessage);

        alertIcon.setImageResource(R.drawable.ic_alert);
        alertTitle.setText("Вы записались на встречу!");
        alertMessage.setText("Вы записались на встречу на " + selectedDate + " в " + selectedTime);

        builder.setPositiveButton("OK", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}