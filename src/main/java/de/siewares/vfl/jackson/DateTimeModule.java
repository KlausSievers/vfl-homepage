package de.siewares.vfl.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Jackson module to convert java.time objects to iso strings.
 */
public class DateTimeModule extends SimpleModule {

  public DateTimeModule() {
    super("DateTimeModule");

    addSerializer(LocalTime.class, new LocalTimeSerializer());
    addSerializer(LocalDate.class, new LocalDateSerializer());
    addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

    addDeserializer(LocalTime.class, new LocalTimeDeserializer());
    addDeserializer(LocalDate.class, new LocalDateDeserializer());
    addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
  }

  private static class LocalTimeSerializer extends JsonSerializer<LocalTime> {

    public LocalTimeSerializer() {
    }

    @Override
    public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
      gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

  }

  private static class LocalDateSerializer extends JsonSerializer<LocalDate> {

    public LocalDateSerializer() {
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
      gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

  }

  private static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    public LocalDateTimeSerializer() {
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
      gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

  }

  private static class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    public LocalTimeDeserializer() {
    }

    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      final String value = p.getValueAsString();
      if (value.length() > 15) {
        final OffsetTime offsetTime = OffsetTime.parse(value, DateTimeFormatter.ISO_OFFSET_TIME);
        return offsetTime.toLocalTime();
      } else {
        return LocalTime.parse(value, DateTimeFormatter.ISO_LOCAL_TIME);
      }
    }

  }

  private static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    public LocalDateDeserializer() {
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      return LocalDate.parse(p.getValueAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
    }

  }

  public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    public LocalDateTimeDeserializer() {
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      final String value = p.getValueAsString();
      return deserialize(value);
    }

    public LocalDateTime deserialize(String value) {
      if (value.endsWith("Z")) {
        Instant instant = Instant.parse(value);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
      } else if (value.lastIndexOf(':') > 25) {//there is a timezone in the string
        final OffsetDateTime offsetDateTime = OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return offsetDateTime.toLocalDateTime();
      } else {
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      }
    }

  }

}
