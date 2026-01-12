package br.luis.httpserver.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class json {

    private static ObjectMapper objectMapper = getObjectMapper();

    public static ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    // I convert this string into JsonNode
    public static JsonNode parse(String jsonSrc) throws IOException {
        return objectMapper.readTree(jsonSrc);
    }

    /**
     * This methods converts an Object into a JsonNode
     */
    public static JsonNode fromObject(Object object) {
        return objectMapper.valueToTree(object);
    }

    /**
     *
     * this methods returns convert a Json node into a class
     */
    public static <A> A fromJson(JsonNode jsonNode, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(jsonNode, clazz);
    }

    public static String jsonString(JsonNode jsonNode, boolean trueOrFalse) throws JsonProcessingException {
        return generateJson(jsonNode, trueOrFalse);
    }

    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if (pretty)
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        return objectWriter.writeValueAsString(o);

    }
}
