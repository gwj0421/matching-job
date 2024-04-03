package kimchisoup.matchingjob.utils.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import kimchisoup.matchingjob.error.EnumConvertException;

import java.io.IOException;
import java.util.List;

public class EnumConverter<T> implements AttributeConverter<List<T>, String> {
    private final ObjectMapper objectMapper;

    public EnumConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(List<T> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new EnumConvertException(e);
        }
    }

    @Override
    public List<T> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<>() {

            });
        } catch (IOException e) {
            throw new EnumConvertException(e);
        }
    }
}


