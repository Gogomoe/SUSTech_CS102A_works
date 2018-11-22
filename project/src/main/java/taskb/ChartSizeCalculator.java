package taskb;

import kotlin.Pair;

class ChartSizeCalculator {

    double chartXMin;
    double chartXMax;
    double chartYMin;
    double chartYMax;

    int rulerGrade;
    double rulerStep;

    ChartSizeCalculator(Histogram histogram, int itemsCount, double maxValue) {
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

        if (span < 5) {
            double power = Math.pow(10, level - 1);

            Pair[] map = new Pair[]{
                    new Pair<>(1.2, new Pair<>(6, 2 * power)),
                    new Pair<>(1.5, new Pair<>(5, 3 * power)),
                    new Pair<>(1.8, new Pair<>(6, 3 * power)),
                    new Pair<>(2.0, new Pair<>(5, 4 * power)),
                    new Pair<>(2.4, new Pair<>(6, 4 * power)),
                    new Pair<>(2.5, new Pair<>(5, 5 * power)),
                    new Pair<>(3.0, new Pair<>(6, 5 * power)),
                    new Pair<>(3.6, new Pair<>(6, 6 * power)),
                    new Pair<>(4.2, new Pair<>(6, 7 * power)),
                    new Pair<>(4.8, new Pair<>(6, 8 * power)),
            };
            for (Pair pair : map) {
                if (span < (Double) pair.getFirst()) {
                    return (Pair<Integer, Double>) pair.getSecond();
                }
            }
            return new Pair<>(5, 10 * power);
        }

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
