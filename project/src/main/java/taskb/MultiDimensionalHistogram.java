package taskb;

public class MultiDimensionalHistogram extends Histogram {

    @Override
    protected void calculateChartSize() {
        chartXMin = -1;
        int items = 0;
        for (Group group : groups) {
            for (Item item : group.items) {
                items++;
            }
        }
        chartXMax = items + (groups.size() - 1) * formats.groupMargin;
        chartYMin = 0;
        double max = findMaxValue();

        double span = max - chartYMin;
        double factor = 1.0;

        int level = (int) Math.floor(Math.log10(span));
        span = span * Math.pow(10, -level);
        factor = factor * Math.pow(10, level);

        int nSpan = (int) Math.ceil(span);
        chartYMax = chartYMin + factor * nSpan;

        // TODO : 太难看了，应该重构
        switch (nSpan) {
            case 1:
                rulerGrade = 5;
                rulerStep = factor / 5;
                break;
            case 2:
                rulerGrade = nSpan * 3;
                rulerStep = factor / 3;
                break;
            case 3:
                rulerGrade = nSpan * 2;
                rulerStep = factor / 2;
                break;
            case 4:
                rulerGrade = nSpan * 3 / 2;
                rulerStep = factor / 1.5;
                break;
            default:
                rulerGrade = nSpan;
                rulerStep = factor;
                break;
        }
    }

    private double findMaxValue() {
        return groups.stream()
                .flatMap(it -> it.items.stream())
                .mapToDouble(it -> it.value)
                .max()
                .getAsDouble();
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

    @Override
    protected void plotKeys() {
        StdDraw.setFont(formats.keyFont);
        StdDraw.setPenColor(formats.keyColor);

        int left = 0;
        int right = 0;
        final double y = chartYMin - 0.5 * rulerStep;
        for (Group group : groups) {
            right = left + group.items.size() - 1;
            double x = (left + right) / 2.0;
            StdDraw.text(x, y, group.name);
            left = right + formats.groupMargin + 1;
        }

    }

    private void drawBar(int x, Item item) {
        StdDraw.setPenColor(item.property.color);
        if (formats.isBarFilled) {
            StdDraw.filledRectangle(x, item.value / 2, 0.5, item.value / 2);
        }
        if (formats.hasBarFrame) {
            StdDraw.rectangle(x, item.value / 2, 0.5, item.value / 2);
        }
    }

}
