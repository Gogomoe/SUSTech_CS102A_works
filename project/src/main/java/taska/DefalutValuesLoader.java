package taska;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;

public class DefalutValuesLoader {

    final static String defaultFile = "default.json";

    public static ValueGetter CANVAS;
    public static ValueGetter FMTS;
    public static ValueGetter DATA;

    static {
        ClassLoader classLoader = DefalutValuesLoader.class.getClassLoader();

        try (
                InputStream is = classLoader.getResource(defaultFile).openStream();
                JsonReader rdr = Json.createReader(is)
        ) {
            JsonObject obj = rdr.readObject().getJsonObject("histograma");
            CANVAS = new ValueGetter(obj.getJsonObject("canvas"));
            FMTS = new ValueGetter(obj.getJsonObject("formats"));
            DATA = new ValueGetter(obj.getJsonObject("data"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static class ValueGetter {
        private JsonObject defaultValues;
        private JsonObject values;

        private ValueGetter(JsonObject defaultValues) {
            this.defaultValues = defaultValues;
        }

        public boolean getBoolean(String key) {
            return containsKey(key) ? values.getBoolean(key) : defaultValues.getBoolean(key);
        }

        public JsonNumber getJsonNumber(String key) {
            return containsKey(key) ? values.getJsonNumber(key) : defaultValues.getJsonNumber(key);
        }

        public JsonArray getJsonArray(String key) {
            return containsKey(key) ? values.getJsonArray(key) : defaultValues.getJsonArray(key);
        }

        public JsonObject getJsonObject(String key) {
            return containsKey(key) ? values.getJsonObject(key) : defaultValues.getJsonObject(key);
        }

        private boolean containsKey(String key2) {
            return values != null && values.containsKey(key2);
        }

        public JsonObject getValues() {
            return values;
        }

        public void setValues(JsonObject values) {
            this.values = values;
        }
    }
}
