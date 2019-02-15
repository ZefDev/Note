package com.mandriklab.notes.Model;

import android.arch.persistence.room.TypeConverter;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Converter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @TypeConverter
    public static OffsetDateTime toOffsetDateTime(String value) {
        return value == null ? null : (OffsetDateTime) formatter.parse(value);
    }

    @TypeConverter
    public static String fromOffsetDateTime(OffsetDateTime date) {
        return date == null ? null : date.format(formatter);
    }
}