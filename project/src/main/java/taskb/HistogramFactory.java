package taskb;

public class HistogramFactory {

    static Histogram getHistogram(ChartType type) {
        switch (type) {
            case MULTI_DIMENSIONAL:
                return new MultiDimensionalHistogram();
            case STACKED:
                return new StackedHistogram();
            default:
                throw new RuntimeException("Chart Type Error:" + type);
        }
    }

    enum ChartType {
        MULTI_DIMENSIONAL, STACKED
    }

}
