package taskc.component

import taskc.Canvas
import taskc.Theme
import taskc.action.Action
import taskc.action.ClickAction
import taskc.property.Vector
import java.awt.Color
import kotlin.math.abs

class ThemeComponent(val theme: Theme) : Component() {

    private val radius = 2.0

    private var width = 0.0
    private var height = 0.0

    private var now = "WHITE"

    fun initPosition(canvas: Canvas) {
        width = radius
        height = canvas.widthToHeight(radius)
        this.position.value = Vector(canvas.xMin + width * 2,
                canvas.yMax - height * 2)
    }

    override fun drawSelf(canvas: Canvas) {
        canvas.color = Color(51, 51, 51)
        canvas.filledCircle(0.0, 0.0, radius)
        canvas.color = Color(250, 250, 250)
        canvas.filledCircle(0.0, 0.0, radius * 0.8)
    }

    override fun handleAction(action: Action) {
        if (action !is ClickAction) {
            return
        }
        if (abs(action.x) <= width && abs(action.y) <= height) {
            if (now == "WHITE") {
                now = "BlACK"
                theme.background.applyAnimation(Color(36, 36, 36), 10)
                theme.titleColor.applyAnimation(Color(172, 172, 172), 10)
                theme.timelineColor.applyAnimation(Color(196, 196, 196), 10)
            } else if (now == "BlACK") {
                now = "WHITE"
                theme.background.applyAnimation(Color(250, 250, 250), 10)
                theme.titleColor.applyAnimation(Color(72, 72, 72), 10)
                theme.timelineColor.applyAnimation(Color(60, 60, 60), 10)
            }
            action.done()
        }
    }

    override fun tick() {
        theme.background.tick()
        theme.titleColor.tick()
        theme.rulerColor.tick()
        theme.timelineColor.tick()
    }
}