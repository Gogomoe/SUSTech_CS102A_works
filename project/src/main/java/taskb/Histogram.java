package taskb;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public abstract class Histogram {

    protected Canvas canvas;
    protected Formats formats;
    protected String header;

    protected List<Group> groups;
    protected List<Property> properties;

    protected double chartXMin;
    protected double chartXMax;
    protected double chartYMin;
    protected double chartYMax;
    protected double windowXMin;
    protected double windowXMax;
    protected double windowYMin;
    protected double windowYMax;

    protected double rulerStep;
    protected int rulerGrade;


    public void init(Canvas canvas, Formats formats, String header, List<Group> groups, List<Property> properties) {
        this.canvas = canvas;
        this.formats = formats;
        this.header = header;
        this.groups = groups;
        this.properties = properties;
        setHistogramParameters();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void setHistogramParameters() {
        StdDraw.setCanvasSize(canvas.x, canvas.y);
        StdDraw.clear(canvas.bgColor);

        calculateChartSize();

        double ySpacing = (chartYMax - chartYMin) / (1 - formats.topMargin - formats.bottomMargin);
        windowYMin = chartYMin - formats.bottomMargin * ySpacing;
        windowYMax = chartYMax + formats.topMargin * ySpacing;

        double xSpacing = (chartXMax - chartXMin) / (1 - formats.leftMargin - formats.rightMargin);
        windowXMin = chartXMin - formats.leftMargin * xSpacing;
        windowXMax = chartXMax + formats.rightMargin * xSpacing;

        StdDraw.setYscale(windowYMin, windowYMax);
        StdDraw.setXscale(windowXMin, windowXMax);

    }

    // TODO set chart values and rulerStep rulerGrade
    protected abstract void calculateChartSize();

    public void draw() {
        plotRuler();
        plotKeys();
        if (formats.hasBorder) plotBorder();
        if (formats.hasHeader) plotHeader();
        plotBars();
    }

    protected void plotRuler() {
        StdDraw.setFont(formats.rulerFont);
        StdDraw.setPenColor(formats.rulerColor);

        double x0 = chartXMin;
        double x1 = chartXMax;

        String[] mark = new String[rulerGrade + 1];
        for (int i = 0; i <= rulerGrade; i++) {
            double y = chartYMin + i * rulerStep;
            mark[i] = numberForRuler(y);
            StdDraw.line(x0, y, x1, y);
        }

        StdDraw.setPenColor(formats.rulerMarkColor);
        int len = maxMarkLength(mark);
        final double xs = windowXMin + 0.7 * (chartXMin - windowXMin);
        for (int i = 0; i <= rulerGrade; i++) {
            double y = chartYMin + i * rulerStep;
            StdDraw.text(xs, y, String.format("%" + len + "s", mark[i]));
        }
    }

    private String numberForRuler(double x) {
        if (chartYMax >= 5 && rulerStep > 1) return "" + (int) x;
        if (rulerStep > 0.1) return String.format("%.1f", x);
        if (rulerStep > 0.01) return String.format("%.2f", x);
        if (rulerStep > 0.001) return String.format("%.3f", x);
        if (rulerStep > 0.0001) return String.format("%.4f", x);
        if (rulerStep > 0.00001) return String.format("%.5f", x);
        return String.format("%g", x);
    }

    private int maxMarkLength(String[] sa) {
        return Arrays.stream(sa)
                .mapToInt(String::length)
                .max()
                .getAsInt();
    }

    protected void plotKeys() {
        StdDraw.setFont(formats.keyFont);
        StdDraw.setPenColor(formats.keyColor);
        final double y = chartYMin - 0.5 * rulerStep;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).name.trim().length() == 0) {
                continue;
            }
            double x = i;
            StdDraw.text(x, y, groups.get(i).name);
        }
    }

    protected void plotBorder() {
        double x = .5 * (chartXMin + chartXMax);
        double y = .5 * (chartYMin + chartYMax);
        double halfWidth = .5 * (chartXMax - chartXMin);
        double halfHeight = .5 * (chartYMax - chartYMin);
        StdDraw.setPenColor(formats.borderColor);
        StdDraw.rectangle(x, y, halfWidth, halfHeight);
    }

    protected void plotHeader() {
        StdDraw.setFont(formats.headerFont);
        double x = formats.headerOffsetX * (windowXMin + windowXMax);
        double y = chartYMax + formats.headerOffsetY * (windowYMax - chartYMax);
        StdDraw.setPenColor(formats.headerColor);
        StdDraw.text(x, y, header);
    }

    protected abstract void plotBars();

}
