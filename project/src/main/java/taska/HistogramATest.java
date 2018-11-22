package taska;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HistogramATest {
    public static void main(String[] args) {
        HistogramA h = createHistogramAFrom(args[0]);
        h.draw();
    }

    private static HistogramA createHistogramAFrom(String fileName) {
        HistogramA h = null;
        try (
                InputStream is = new FileInputStream(new File(fileName));
                JsonReader rdr = Json.createReader(is)
        ) {
            JsonObject obj = rdr.readObject().getJsonObject("histograma");
            Canvas canvas = getCanvasFrom(obj.getJsonObject("canvas"));
            Formats fmts = getFormatsFrom(obj.getJsonObject("formats"));
            HistogramData data = getDataFrom(obj.getJsonObject("data"));
            h = new HistogramA(canvas, fmts, data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return h;
    }

    private static Canvas getCanvasFrom(JsonObject obj) {
        Canvas canvas = new Canvas();

        DefaultValuesLoader.ValueGetter getter = DefaultValuesLoader.CANVAS;
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

    private static Formats getFormatsFrom(JsonObject obj) {
        DefaultValuesLoader.ValueGetter getter = DefaultValuesLoader.FMTS;
        getter.setValues(obj);

        Formats fmts = new Formats();
        fmts.margins = toDoubleArray(getter.getJsonArray("margins"));
        fmts.isBarFilled = getter.getBoolean("isbarfilled");
        fmts.barFillColor = getColorFrom(getter.getJsonArray("barfillcolor"));
        fmts.hasBarFrame = getter.getBoolean("hasbarframe");
        fmts.barFrameColor = getColorFrom(getter.getJsonArray("barframecolor"));
        fmts.hasBorder = getter.getBoolean("hasborder");
        fmts.borderColor = getColorFrom(getter.getJsonArray("bordercolor"));

        fmts.rulerColor = getColorFrom(getter.getJsonArray("rulercolor"));
        fmts.rulerMarkColor = getColorFrom(getter.getJsonArray("rulermarkcolor"));
        fmts.hasRightRuler = getter.getBoolean("hasrightruler");
        fmts.rulerFont = getFontFrom(getter.getJsonObject("rulerfont"));

        fmts.keyColor = getColorFrom(getter.getJsonArray("keycolor"));
        fmts.keyFont = getFontFrom(getter.getJsonObject("keyfont"));

        fmts.hasHeader = getter.getBoolean("hasheader");
        fmts.headerColor = getColorFrom(getter.getJsonArray("headercolor"));
        fmts.headerFont = getFontFrom(getter.getJsonObject("headerfont"));
        fmts.headerOffset = toDoubleArray(getter.getJsonArray("headeroffset"));

        fmts.hasFooter = getter.getBoolean("hasfooter");
        fmts.footerColor = getColorFrom(getter.getJsonArray("footercolor"));
        fmts.footerFont = getFontFrom(getter.getJsonObject("footerfont"));
        fmts.footerOffset = toDoubleArray(getter.getJsonArray("footeroffset"));

        return fmts;
    }

    private static HistogramData getDataFrom(JsonObject obj) {
        DefaultValuesLoader.ValueGetter getter = DefaultValuesLoader.DATA;
        getter.setValues(obj);

        HistogramData data = new HistogramData();
        data.header = obj.getString("header", "");
        data.footer = obj.getString("footer", "");
        data.minValue = getter.getJsonNumber("minvalue").doubleValue();
        data.keys = toStringArray(obj.getJsonArray("keys"));
        data.values = toDoubleArray(obj.getJsonArray("values"));
        return data;
    }
}
