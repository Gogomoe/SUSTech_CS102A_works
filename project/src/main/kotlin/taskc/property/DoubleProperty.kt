package taskc.property

class DoubleProperty(override var value: Double = 0.0) : Property<Double>() {

    override var nowTick: Int = 0
    override var totalTicks: Int = 0
    override var startVal: Double = value
    override var endValue: Double? = null

    override fun interpolate(nowTick: Int): Double =
            value * startWeight() + endValue!! * endWeight()

}

typealias OpacityProperty = DoubleProperty