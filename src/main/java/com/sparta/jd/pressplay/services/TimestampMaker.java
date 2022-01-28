package com.sparta.jd.pressplay.services;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimestampMaker {
    public static Timestamp getCurrentDate() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public static Timestamp getCurrentDatePlusDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().plusDays(days));
    }
}
