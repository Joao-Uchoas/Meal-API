package com.meal.me.Configurations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.cloud.Timestamp;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimestampDeserializer extends JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String str = p.getText();

        // Converte a string para LocalDate
        LocalDate localDate = LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);

        // Converte LocalDate para LocalDateTime
        LocalDateTime localDateTime = localDate.atStartOfDay();

        // Converte LocalDateTime para java.util.Date
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // Converte java.util.Date para Timestamp
        return Timestamp.of(date);
    }
}
