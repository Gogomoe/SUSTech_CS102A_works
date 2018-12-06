package taskc.component

import taskc.Canvas
import taskc.Theme
import java.awt.Color
import java.awt.Font

class RulerComponent(val value: Int, val maxGetter: () -> Int, private val theme: Theme) : Component() {

    override fun drawSelf(canvas: Canvas) {
        canvas.setFont(theme.rulerFont)
        canvas.color = theme.rulerColor.value

        val x = (value.toDouble() / maxGetter().toDouble()) * 100
        canvas.line(x, -0.3, x, 15.0)
        canvas.text(x, -1.0, value.toString())
    }

}