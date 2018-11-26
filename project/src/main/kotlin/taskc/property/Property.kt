package taskc.property

abstract class Property<T> {

    abstract var value: T

    abstract var nowTick: Int
        protected set
    abstract var totalTicks: Int
        protected set
    abstract var startVal: T
        protected set
    abstract var endValue: T?
        protected set

    open fun applyAnimation(value: T, ticks: Int) {
        if (ticks <= 0) {
            throw IllegalArgumentException("ticks must be positive : $ticks ")
        }
        startVal = value
        endValue = value
        nowTick = 0
        totalTicks = ticks
    }


    open fun tick() {
        if (endValue == null) {
            return
        }
        nowTick++
        value = interpolate(nowTick)
        if (nowTick == totalTicks) {
            endValue = null
        }
    }

    abstract fun interpolate(nowTick: Int): T
}

fun Property<*>.startWeight(): Double = (totalTicks - nowTick).toDouble() / totalTicks.toDouble()
fun Property<*>.endWeight(): Double = (nowTick).toDouble() / totalTicks.toDouble()