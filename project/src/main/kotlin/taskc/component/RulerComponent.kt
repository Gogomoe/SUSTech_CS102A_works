package taskc.component

import taskc.Canvas
import java.awt.Color
import java.awt.Font
import java.lang.Math.pow
import kotlin.math.floor
import kotlin.math.log10

class RulerComponent(val maxGetter: () -> Int) : Component() {

    init {
        super.level = -5
    }

    override fun drawSelf(canvas: Canvas) {
        canvas.setFont(Font("Microsoft YaHei Light", Font.PLAIN, 16))
        canvas.color = Color(160, 160, 160, 93)

        val maxValue = maxGetter()
        val level = floor(log10(maxValue.toDouble()))

        val max = (maxValue * pow(10.0, -(level - 1))).toInt()

        val factor = when {
            max in 15..30 -> 2
            max in 31..70 -> 5
            max > 70 -> 10
            max <= 14 -> 1
            else -> throw RuntimeException("???")
        }

        for (i in (factor..max step factor)) {
            val value = i * pow(10.0, (level - 1)).toInt()
            val x = (value.toDouble() / maxValue.toDouble()) * 100
            canvas.line(x, -0.3, x, 15.0)
            canvas.text(x, -1.0, value.toString())
        }
    }
}