package taskb;

public class MultiDimensionalHistogram extends Histogram {

    @Override
    protected void calculateChartSize() {

        int itemCount = groups.stream()
                .mapToInt(it -> it.items.size())
                .sum();
        double maxValue = groups.stream()
                .flatMap(it -> it.items.stream())
                .mapToDouble(it -> it.value)
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
        int x = 0;
        for (Group group : groups) {
            for (Item item : group.items) {
                drawBar(x, item);
                x++;
            }
            x += formats.groupMargin;
        }
    }

    private void drawBar(int x, Item item) {
        GoDraw.setPenColor(item.property.color);
        if (formats.isBarFilled) {
            GoDraw.filledRectangle(x, item.value / 2, 0.5, item.value / 2);
        }
        if (formats.hasBarFrame) {
            GoDraw.rectangle(x, item.value / 2, 0.5, item.value / 2);
        }
    }

    @Override
    protected void plotKeys() {
        GoDraw.setFont(formats.keyFont);
        GoDraw.setPenColor(formats.keyColor);

        int left = 0;
        int right;
        final double y = chartYMin - 0.5 * rulerStep;
        for (Group group : groups) {
            right = left + group.items.size() - 1;
            double x = (left + right) / 2.0;
            GoDraw.text(x, y, group.name);
            left = right + formats.groupMargin + 1;
        }

    }


}
