package com.meal.me.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.cloud.Timestamp;
import com.meal.me.Configurations.TimestampDeserializer;
import com.meal.me.Configurations.TimestampSerializer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Test {
    private String documentId;
    private String name;
    private String profession;

    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp localDate;
}
