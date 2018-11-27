package taskc.property

import kotlin.math.roundToInt

class IntProperty(override var value: Int = 0) : Property<Int>() {

    override fun interpolate(nowTick: Int): Int =
            (startVal * startWeight() + endValue!! * endWeight()).roundToInt()
}

class DoubleProperty(override var value: Double = 0.0) : Property<Double>() {

    override fun interpolate(nowTick: Int): Double =
            startVal * startWeight() + endValue!! * endWeight()

}

class FloatProperty(override var value: Float = 0f) : Property<Float>() {

    override fun interpolate(nowTick: Int): Float =
            (startVal * startWeight() + endValue!! * endWeight()).toFloat()

}

typealias ValueProperty = IntProperty
typealias OpacityProperty = FloatProperty