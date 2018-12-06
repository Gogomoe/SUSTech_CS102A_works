package taskc.component

import taskc.Canvas
import taskc.Theme
import java.awt.Color
import java.awt.Font

class TitleComponent(private val title: String = "", private val theme: Theme) : Component() {

    override fun drawSelf(canvas: Canvas) {
        canvas.setFont(theme.titleFont)
        canvas.color = theme.titleColor.value
        canvas.text(0.0, 0.0, title)
    }

}