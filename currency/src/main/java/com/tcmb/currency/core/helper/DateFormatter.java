package com.tcmb.currency.core.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    public static LocalDate dateConverter(String date){
        DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toFormatter();
        LocalDate datetime = null;
        try {
            datetime = LocalDate.parse(date, f);
            System.out.println(datetime); // 2019-11-12
        } catch (DateTimeParseException e) {
            e.printStackTrace();// Exception handling message/mechanism/logging as per company standard
        }
        return datetime;
    }
}
