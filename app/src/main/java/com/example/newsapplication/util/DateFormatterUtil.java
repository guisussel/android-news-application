package com.example.newsapplication.util;

import android.util.Log;


import androidx.core.net.ParseException;

import com.example.newsapplication.singleton.NewsSource;
import com.example.newsapplication.ui.sources.SourcesEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatterUtil {

    public static String format(String dateString) {

        //different news sources have different date formats "2023-04-18T21:00:42Z"
        if (NewsSource.getInstance().getSource().equals(SourcesEnum.Source.CNN.getValue())) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
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
        } else {
            if (NewsSource.getInstance().getSource().equals(SourcesEnum.Source.BBC_NEWS.getValue())) {
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
        return null;
    }
}
