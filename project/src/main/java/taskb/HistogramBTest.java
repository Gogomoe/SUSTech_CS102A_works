package taskb;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HistogramBTest {
    public static void main(String[] args) {
        Histogram h = createHistogramFrom(args[0]);
        h.draw();
    }

    private static Histogram createHistogramFrom(String fileName) {
        Histogram histogram = null;
        try (
                InputStream is = new FileInputStream(new File(fileName));
                JsonReader rdr = Json.createReader(is)
        ) {
            JsonObject obj = rdr.readObject().getJsonObject("histograma");
            Canvas canvas = getCanvasFrom(obj.getJsonObject("canvas"));
            Formats fmts = getFormatsFrom(obj.getJsonObject("formats"));

            JsonObject data = obj.getJsonObject("value");
            String header = data.getString("header");
            List<Property> properties = getProperties(data);
            List<Group> groups = getGroups(data, properties);

            HistogramFactory.ChartType type = HistogramFactory.ChartType
                    .valueOf(obj.getString("type"));
            histogram = HistogramFactory.getHistogram(type);
            histogram.init(canvas, fmts, header, groups, properties);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return histogram;
    }

    private static List<Property> getProperties(JsonObject data) {
        JsonArray jsa = data.getJsonArray("properties");
        List<Property> properties = new ArrayList<>();

        for (int i = 0; i < jsa.size(); i++) {
            JsonObject property = jsa.getJsonObject(i);
            properties.add(
                    new Property(
                            property.getString("name"),
                            getColorFrom(property.getJsonArray("color"))
                    )
            );
        }

        return properties;
    }

    private static List<Group> getGroups(JsonObject data, List<Property> properties) {
        JsonArray jsa = data.getJsonArray("groups");
        List<Group> groups = new ArrayList<>();

        for (int i = 0; i < jsa.size(); i++) {
            JsonObject jsonObject = jsa.getJsonObject(i);
            Group group = new Group(jsonObject.getString("name"));
            JsonArray values = jsonObject.getJsonArray("values");
            for (int j = 0; j < values.size(); j++) {
                group.items.add(
                        new Item(
                                values.getJsonNumber(j).doubleValue(),
                                properties.get(j)
                        ));
            }
        }

        return groups;
    }

    private static Canvas getCanvasFrom(JsonObject obj) {
        Canvas canvas = new Canvas();

        DefalutValuesLoader.ValueGetter getter = DefalutValuesLoader.CANVAS;
        getter.setValues(obj);

        int[] size = toIntArray(getter.getJsonArray("size"));
        canvas.x = size[0];
        canvas.y = size[1];

        canvas.xScale = toDoubleArray(getter.getJsonArray("xscale"));
        canvas.yScale = toDoubleArray(getter.getJsonArray("yscale"));
        canvas.bgColor = getColorFrom(getter.getJsonArray("bgcolor"));
        canvas.color = getColorFrom(getter.getJsonArray("color"));

        return canvas;
    }

    private static Formats getFormatsFrom(JsonObject obj) {
        DefalutValuesLoader.ValueGetter getter = DefalutValuesLoader.FMTS;
        getter.setValues(obj);

        Formats fmts = new Formats();
        fmts.margins = toDoubleArray(getter.getJsonArray("margins"));
        fmts.isBarFilled = getter.getBoolean("is_bar_filled");
        fmts.barFillColor = getColorFrom(getter.getJsonArray("bar_fill_color"));
        fmts.hasBarFrame = getter.getBoolean("has_bar_frame");
        fmts.barFrameColor = getColorFrom(getter.getJsonArray("bar_frame_color"));
        fmts.hasBorder = getter.getBoolean("has_border");
        fmts.borderColor = getColorFrom(getter.getJsonArray("border_color"));

        fmts.rulerColor = getColorFrom(getter.getJsonArray("ruler_color"));
        fmts.rulerMarkColor = getColorFrom(getter.getJsonArray("ruler_mark_color"));
        fmts.hasRightRuler = getter.getBoolean("has_right_ruler");
        fmts.rulerFont = getFontFrom(getter.getJsonObject("ruler_font"));

        fmts.keyColor = getColorFrom(getter.getJsonArray("key_color"));
        fmts.keyFont = getFontFrom(getter.getJsonObject("key_font"));

        fmts.hasHeader = getter.getBoolean("has_header");
        fmts.headerColor = getColorFrom(getter.getJsonArray("header_color"));
        fmts.headerFont = getFontFrom(getter.getJsonObject("header_font"));
        fmts.headerOffset = toDoubleArray(getter.getJsonArray("header_offset"));

        return fmts;
    }

    private static int[] toIntArray(JsonArray jsa) {
        int[] a = new int[jsa.size()];
        for (int i = 0; i < jsa.size(); i++)
            a[i] = jsa.getInt(i);
        return a;
    }

    private static double[] toDoubleArray(JsonArray jsa) {
        double[] a = new double[jsa.size()];
        for (int i = 0; i < jsa.size(); i++)
            a[i] = jsa.getJsonNumber(i).doubleValue();
        return a;
    }

    private static String[] toStringArray(JsonArray jsa) {
        String[] s = new String[jsa.size()];
        for (int i = 0; i < jsa.size(); i++)
            s[i] = jsa.getString(i);
        return s;
    }

    private static Color getColorFrom(JsonArray jsa) {
        int[] c = toIntArray(jsa);
        return new Color(c[0], c[1], c[2]);
    }

    private static Font getFontFrom(JsonObject jsa) {
        return new Font(jsa.getString("font"), Font.PLAIN, jsa.getInt("size"));
    }


}
