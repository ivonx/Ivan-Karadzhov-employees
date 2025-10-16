package com.sirma.task;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class DateFormatters {
    public static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.GERMANY),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy"),
//          DateTimeFormatter.ofPattern("MM.dd.yyyy"),
//          DateTimeFormatter.ofPattern("MM-dd-yyyy"),
//          DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yy"),
            DateTimeFormatter.ofPattern("dd-MM-yy"),
            DateTimeFormatter.ofPattern("dd/MM/yy")
    );
}
