package taskc.component

import taskc.Canvas
import java.awt.Color
import java.awt.Font

class TitleComponent(private val title: String = "", fontSize: Int = 32) : Component() {

    private val font: Font = Font("Microsoft YaHei", Font.PLAIN, fontSize)

    override fun drawSelf(canvas: Canvas) {
        canvas.setFont(font)
        canvas.color = Color(90, 90, 90)
        canvas.text(0.0, 0.0, title)
    }

}