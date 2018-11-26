package taskc.property

import java.awt.Color
import kotlin.math.roundToInt

class ColorProperty(override var value: Color = Color.BLACK) : Property<Color>() {

    override var nowTick: Int = 0
    override var totalTicks: Int = 0
    override var startVal: Color = value
    override var endValue: Color? = null

    override fun interpolate(nowTick: Int): Color =
            value * startWeight() + endValue!! * endWeight()

}

operator fun Color.times(times: Double) = Color(
        (red * times).roundToInt(),
        (green * times).roundToInt(),
        (blue * times).roundToInt(),
        (alpha * times).roundToInt()
)

operator fun Color.plus(other: Color) = Color(
        red + other.red,
        green + other.green,
        blue + other.blue,
        alpha + other.alpha
)
