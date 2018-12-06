package taskc.component

import taskc.Canvas
import taskc.Theme
import taskc.property.ColorProperty
import java.awt.Color

class BackgroundComponent(private val theme: Theme) : Component() {

    init {
        level = -10
    }

    override fun drawSelf(canvas: Canvas) {
        canvas.color = theme.background.value
        canvas.filledRectangle((canvas.xMin + canvas.xMax) / 2, (canvas.yMin + canvas.yMax) / 2,
                (canvas.xMax - canvas.xMin) / 2, (canvas.yMax - canvas.yMin) / 2)
    }
}