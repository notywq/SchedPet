package com.mobidev.carmelalouise.schedpet.controller;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Carmela Louise on 12/14/2016.
 */

public class CalendarUtils {

    private static String calendar_uri;

    public static long getNewEventId(ContentResolver cr, Uri cal_uri, Activity a){
        Uri local_uri = cal_uri;
        if(cal_uri == null){
            local_uri = Uri.parse(getCalendarUriBase(a)+"events");
        }
        Cursor cursor = cr.query(local_uri, new String [] {"MAX(_id) as max_id"}, null, null, "_id");
        cursor.moveToFirst();
        long max_val = cursor.getLong(cursor.getColumnIndex("max_id"));
        return max_val+1;
    }

    public static String getCalendarUriBase(Activity act) {
        String calendarUriBase = null;
        Uri calendars = Uri.parse("content://calendar/calendars");
        Cursor managedCursor = null;

        try {
            managedCursor = act.getContentResolver().query(calendars,
                    null, null, null, null);
        } catch (Exception e) {
        }

        if (managedCursor != null) {
            calendarUriBase = "content://calendar/";
        } else {
            calendars = Uri.parse("content://com.android.calendar/calendars");
            try {
                managedCursor = act.getContentResolver().query(calendars,
                        null, null, null, null);
            } catch (Exception e) {
            }
            if (managedCursor != null) {
                calendarUriBase = "content://com.android.calendar/";
            }
        }

        calendar_uri= calendarUriBase;
        return calendarUriBase;
    }
}
