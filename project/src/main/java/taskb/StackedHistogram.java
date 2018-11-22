package taskb;

public class StackedHistogram extends Histogram {
    @Override
    protected void calculateChartSize() {

        int itemCount = groups.size();
        double maxValue = groups.stream()
                .mapToDouble(group ->
                        group.items.stream().mapToDouble(item -> item.value).sum())
                .max()
                .getAsDouble();

        ChartSizeCalculator calculator = new ChartSizeCalculator(this, itemCount, maxValue);
        this.chartXMin = calculator.chartXMin;
        this.chartXMax = calculator.chartXMax;
        this.chartYMin = calculator.chartYMin;
        this.chartYMax = calculator.chartYMax;

        this.rulerStep = calculator.rulerStep;
        this.rulerGrade = calculator.rulerGrade;

    }

    @Override
    protected void plotBars() {
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            double lastTop = 0;
            for (Item item : group.items) {
                drawBar(i + i * formats.groupMargin, lastTop + item.value / 2, item);
                lastTop += item.value;
            }
        }
    }

    private void drawBar(int x, double y, Item item) {
        StdDraw.setPenColor(item.property.color);
        if (formats.isBarFilled) {
            StdDraw.filledRectangle(x, y, 0.5, item.value / 2);
        }
        if (formats.hasBarFrame) {
            StdDraw.rectangle(x, y, 0.5, item.value / 2);
        }
    }

    @Override
    protected void plotKeys() {
        StdDraw.setFont(formats.keyFont);
        StdDraw.setPenColor(formats.keyColor);

        final double y = chartYMin - 0.5 * rulerStep;
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            StdDraw.text(i + i * formats.groupMargin, y, group.name);
        }
    }
}
