package com.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.Assert;

import java.io.IOException;

public class JsonUtil {

    private static Logger logger = Logger.getLogger(JsonUtil.class);
    private static com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

    static {
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static String writeValueAsString(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            logger.error("json序列化错误", e);
        }
        return null;
    }

    public static <T> T readObject(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("json反序列化错误", e);
        }
        return null;
    }

    /**
     * 将对象转换为JSONObject对象
     *
     * @param object
     *            对象
     */
    public static JSONObject toJSONObject (Object object) {
        if (object instanceof String) {
            return toObject(object.toString(), JSONObject.class);
        }
        return toObject(toJson(object), JSONObject.class);
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json
     *            JSON字符串
     * @param valueType
     *            对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        Assert.hasText(json);
        Assert.notNull(valueType);
        try {
            return mapper.readValue(json, valueType);
        } catch (IOException  e) {
            logger.error(""+ e);
        }
        return null;
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param value
     *            对象
     * @return JSOn字符串
     */
    public static String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error(""+ e);
        }
        return null;
    }
}
