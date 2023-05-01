package com.example.newsapplication.util;

import android.util.Log;


import androidx.core.net.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatterUtil {

    public static String format(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());

        try {
            Date date = inputFormat.parse(dateString);
            String formattedDate = outputFormat.format(date);
            Log.d("TAG", "Formatted Date: " + formattedDate);
            return formattedDate;
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
            return dateString;
        }
    }

}
