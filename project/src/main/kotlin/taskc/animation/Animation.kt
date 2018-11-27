package taskc.animation

import taskc.property.Vector
import java.awt.Color

abstract class Animation<T> {

    abstract val value: T
    abstract val ticks: Int

    open val delay: Int = 0
}

class OpacityAnimation(override val value: Float,
                       override val ticks: Int,
                       override val delay: Int = 0) : Animation<Float>()

class PositionAnimation(override val value: Vector,
                        override val ticks: Int,
                        override val delay: Int = 0) : Animation<Vector>()

class ColorAnimation(override val value: Color,
                     override val ticks: Int,
                     override val delay: Int = 0) : Animation<Color>()

class ValueAnimation(override val value: Int,
                     override val ticks: Int,
                     override val delay: Int = 0) : Animation<Int>()
