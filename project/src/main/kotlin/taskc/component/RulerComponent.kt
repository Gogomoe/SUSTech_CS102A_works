package taskc.component

import taskc.Canvas
import java.awt.Color
import java.awt.Font

class RulerComponent(val value: Int, val maxGetter: () -> Int) : Component() {

    override fun drawSelf(canvas: Canvas) {
        canvas.setFont(Font("Microsoft YaHei Light", Font.PLAIN, 16))
        canvas.color = Color(160, 160, 160, 93)

        val x = (value.toDouble() / maxGetter().toDouble()) * 100
        canvas.line(x, -0.3, x, 15.0)
        canvas.text(x, -1.0, value.toString())
    }

}