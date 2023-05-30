package com.meal.me.Configurations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.cloud.Timestamp;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TimestampSerializer extends JsonSerializer<Timestamp> {

    @Override
    public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Converte o Timestamp para java.util.Date
        Date date = value.toDate();

        // Converte java.util.Date para LocalDate
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Escreve o LocalDate como uma string no formato ISO 8601
        gen.writeString(localDate.toString());
    }
}
