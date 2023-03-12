/**
 * @Author HuyVu
 * @CreatedDate 3/1/2023 10:43 AM
 */
package io.huyvu.springbootsocketio.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class JsonUtils {
    private static final ObjectMapper om = new ObjectMapper();

    public static JSONObject toJsonObj(Object obj) {
        try {
            String s = om.writeValueAsString(obj);
            return new JSONObject(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toPojoObj(JSONObject jo, Class<T> clazz) {
        try {
            return om.readValue(jo.toString(), clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
