package com.task.vidhurvoora.neatnytviewer.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.Calendar;


/**
 * Created by vidhurvoora on 7/30/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public String existingDate;

    public DatePickerFragment(){

    }

    public interface CustomDateChooseListener {
        public void onDatePicked(String date);
    }

    CustomDateChooseListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        int year;
        int month;
        int day;

        final Calendar c = Calendar.getInstance();

        if (existingDate != null ) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            try {
                Date dateObj = dateFormat.parse(existingDate);
                c.setTime(dateObj);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        if (mListener != null ) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Calendar c = Calendar.getInstance();
            c.set(year,month,day);
            String selectedDate = dateFormat.format(c.getTime());
            mListener.onDatePicked(selectedDate);
        }
    }
}