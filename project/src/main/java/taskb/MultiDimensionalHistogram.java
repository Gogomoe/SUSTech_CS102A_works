package taskb;

import kotlin.Pair;

public class MultiDimensionalHistogram extends Histogram {

    @Override
    protected void calculateChartSize() {
        chartXMin = -1;
        int items = groups.stream()
                .mapToInt(it -> it.items.size())
                .sum();
        chartXMax = items + (groups.size() - 1) * formats.groupMargin;
        chartYMin = 0;
        double max = findMaxValue();

        /**
         * {@code span} 指的是把标尺分成多少段 [1, 10)
         * {@code factor} 指每段长度多少
         */
        Pair<Integer, Double> spanAndFactor = calculateSpanAndFactor(max - chartYMin);
        int span = spanAndFactor.getFirst();
        double factor = spanAndFactor.getSecond();

        chartYMax = chartYMin + factor * span;
        defineRuler(span, factor);

    }

    private Pair<Integer, Double> calculateSpanAndFactor(double span) {
        /**
         * 处理当 {@code span} 为 0 时的问题
         * @thanks TallWall
         */
        if (span == 0) {
            return new Pair<>(1, 1.0);
        }

        double factor = 1.0;

        int level = (int) Math.floor(Math.log10(span));
        span = span * Math.pow(10, -level);
        factor = factor * Math.pow(10, level);

        return new Pair<>((int) Math.ceil(span), factor);
    }

    private double findMaxValue() {
        return groups.stream()
                .flatMap(it -> it.items.stream())
                .mapToDouble(it -> it.value)
                .max()
                .getAsDouble();
    }

    private void defineRuler(int span, double factor) {
        if (span <= 5) {
            rulerGrade = 5;
            rulerStep = factor * span / 5.0;
        } else {
            rulerGrade = span;
            rulerStep = factor;
        }
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
        int right;
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
