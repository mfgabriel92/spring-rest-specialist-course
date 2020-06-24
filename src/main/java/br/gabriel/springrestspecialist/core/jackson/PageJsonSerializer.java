package br.gabriel.springrestspecialist.core.jackson;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {
    @Override
    public void serialize(Page<?> page, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        generator.writeObjectField("content", page.getContent());
        generator.writeNumberField("pageSize", page.getSize());
        generator.writeNumberField("totalElements", page.getTotalElements());
        generator.writeNumberField("totalPages", page.getTotalPages());
        generator.writeNumberField("currentPage", page.getNumber());
        generator.writeEndObject();
    }
}
