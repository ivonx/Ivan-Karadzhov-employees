package com.sirma.task;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static com.sirma.task.DateFormatters.DATE_FORMATTERS;

@Service
@NoArgsConstructor
public class DateService {

    public Long calculateDateOverlap(
            String firstEmplDateFrom,
            String firstEmplDateTo,
            String secondEmplDateFrom,
            String secondEmplDateTo) {
        LocalDate firstDateFrom = this.parseToLocalDate(firstEmplDateFrom);
        LocalDate firstDateTo = this.parseToLocalDate(firstEmplDateTo);
        LocalDate secondDateFrom = this.parseToLocalDate(secondEmplDateFrom);
        LocalDate secondDateTo = this.parseToLocalDate(secondEmplDateTo);
        final LocalDate from = firstDateFrom.isAfter(secondDateFrom) ? firstDateFrom : secondDateFrom;
        final LocalDate to = firstDateTo.isBefore(secondDateTo) ? firstDateTo : secondDateTo;
        return (to.isAfter(from) || to.isEqual(from)) ? ChronoUnit.DAYS.between(from, to) + 1 : 0;
    }

    private LocalDate parseToLocalDate(String dateToParse) {
        if (dateToParse == null || dateToParse.isBlank()) {
            return LocalDate.now();
        }

        // Not supported date format
        LocalDate parsedDate = null;

        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                parsedDate = LocalDate.parse(dateToParse, formatter);
            } catch (DateTimeParseException _) {
            }
        }
        return parsedDate;
    }
}
