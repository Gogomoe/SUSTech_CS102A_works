package taskb;

import kotlin.Pair;

class ChartCalculator {

    double chartXMin;
    double chartXMax;
    double chartYMin;
    double chartYMax;

    int rulerGrade;
    double rulerStep;

    ChartCalculator(Histogram histogram, int itemsCount, double maxValue) {
        chartXMin = -1;
        chartXMax = itemsCount + (histogram.groups.size() - 1) * histogram.formats.groupMargin;
        chartYMin = 0;

        /**
         * {@code span} 指的是把标尺分成多少段 [1, 10)
         * {@code factor} 指每段长度多少
         */
        Pair<Integer, Double> spanAndFactor = calculateSpanAndFactor(maxValue);
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

    private void defineRuler(int span, double factor) {
        if (span <= 5) {
            rulerGrade = 5;
            rulerStep = factor * span / 5.0;
        } else {
            rulerGrade = span;
            rulerStep = factor;
        }
    }

}
