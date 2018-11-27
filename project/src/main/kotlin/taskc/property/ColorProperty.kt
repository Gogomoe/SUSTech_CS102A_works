package taskc.property

import java.awt.Color
import kotlin.math.roundToInt

class ColorProperty(override var value: Color = Color.BLACK) : Property<Color>() {

    override fun interpolate(nowTick: Int): Color =
            startVal * startWeight() + endValue!! * endWeight()

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
