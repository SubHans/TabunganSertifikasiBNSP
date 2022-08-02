package com.example.tabungan.javaclass;

import android.app.DatePickerDialog;
import android.content.Context;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePicker {
    static DatePickerDialog datePickerDialog;

    public static void showDateDialog(Context context, TextInputLayout inputLayout, SimpleDateFormat dateFormat) {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                inputLayout.getEditText().setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
