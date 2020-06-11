package wr1ttenyu.f1nal.study.code.generator.web.utils;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    private static DateTimeFormatter YYYY_MM_DD =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        TemporalAccessor temporalAccessor = YYYY_MM_DD.parse(text);
        return LocalDate.from(temporalAccessor);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return YYYY_MM_DD.format(object);
    }
}
