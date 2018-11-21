package taskb;

import javax.json.*;
import java.io.IOException;

public class DefalutValuesLoader {

    final static String defaultFile = "default-b.json";

    static ValueGetter CANVAS;
    static ValueGetter FMTS;
    static ValueGetter DATA;

    static {
        ClassLoader classLoader = DefalutValuesLoader.class.getClassLoader();

        try (
                JsonReader rdr = Json.createReader(classLoader.getResource(defaultFile).openStream())
        ) {
            JsonObject obj = rdr.readObject().getJsonObject("histograma");
            CANVAS = new ValueGetter(obj.getJsonObject("canvas"));
            FMTS = new ValueGetter(obj.getJsonObject("formats"));
            DATA = new ValueGetter(obj.getJsonObject("value"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static class ValueGetter {
        private JsonObject defaultValues;
        private JsonObject values;

        private ValueGetter(JsonObject defaultValues) {
            this.defaultValues = defaultValues;
        }

        boolean getBoolean(String key) {
            return containsKey(key) ? values.getBoolean(key) : defaultValues.getBoolean(key);
        }

        JsonNumber getJsonNumber(String key) {
            return containsKey(key) ? values.getJsonNumber(key) : defaultValues.getJsonNumber(key);
        }

        JsonArray getJsonArray(String key) {
            return containsKey(key) ? values.getJsonArray(key) : defaultValues.getJsonArray(key);
        }

        JsonObject getJsonObject(String key) {
            return containsKey(key) ? values.getJsonObject(key) : defaultValues.getJsonObject(key);
        }

        private boolean containsKey(String key2) {
            return values != null && values.containsKey(key2);
        }

        JsonObject getValues() {
            return values;
        }

        void setValues(JsonObject values) {
            this.values = values;
        }
    }
}
