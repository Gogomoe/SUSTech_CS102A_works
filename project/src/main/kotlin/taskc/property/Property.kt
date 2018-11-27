package taskc.property

import taskc.animation.Animation

abstract class Property<T> {

    abstract var value: T

    var nowTick: Int = 0
        protected set
    var totalTicks: Int = 0
        protected set
    var startVal: T = value
        protected set
    var endValue: T? = null
        protected set
    var delay: Int = 0
        protected set

    open fun applyAnimation(animation: Animation<T>) =
            applyAnimation(animation.value, animation.ticks, animation.delay)

    open fun applyAnimation(value: T, ticks: Int, delay: Int = 0) {
        if (ticks <= 0) {
            throw IllegalArgumentException("ticks must be positive : $ticks ")
        }
        startVal = this.value
        endValue = value
        nowTick = 0
        totalTicks = ticks
        this.delay = delay
    }


    open fun tick() {
        if (endValue == null) {
            return
        }
        if (delay > 0) {
            delay--
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