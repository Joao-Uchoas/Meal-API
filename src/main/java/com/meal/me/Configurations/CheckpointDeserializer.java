package com.meal.me.Configurations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.cloud.Timestamp;
import com.meal.me.entity.Checkpoint;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckpointDeserializer extends JsonDeserializer<List<Checkpoint>> {
    @Override
    public List<Checkpoint> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        List<Checkpoint> checkpoints = new ArrayList<>();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        for (JsonNode innerNode : node) {
            if (innerNode.has(0)) {
                String timestampStr = innerNode.get(0).asText();
                Instant instant = Instant.parse(timestampStr);
                Timestamp timestamp = Timestamp.of(Date.from(instant));
                Checkpoint checkpoint = new Checkpoint();
                checkpoint.setTimestamp(timestamp.toString());
                checkpoints.add(checkpoint);
            }
        }
        return checkpoints;
    }
}


