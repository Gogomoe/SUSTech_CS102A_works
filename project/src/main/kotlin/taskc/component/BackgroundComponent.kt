package taskc.component

import taskc.Canvas
import taskc.property.ColorProperty
import java.awt.Color

class BackgroundComponent : Component() {

    private val color: ColorProperty = ColorProperty(Color(224, 224, 224))

    init {
        level = -10
    }

    override fun drawSelf(canvas: Canvas) {
        canvas.color = color.value
        canvas.filledRectangle((canvas.xMin + canvas.xMax) / 2, (canvas.yMin + canvas.yMax) / 2,
                (canvas.xMax - canvas.xMin) / 2, (canvas.yMax - canvas.yMin) / 2)
    }
}