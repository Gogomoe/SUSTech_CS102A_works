package taskc.component

import taskc.Canvas
import taskc.Theme
import taskc.action.Action
import taskc.action.ClickAction
import taskc.property.Vector
import kotlin.math.abs
import kotlin.math.round

class TimelineController(private val histogram: Histogram, val theme: Theme) : Component() {

    private val data = histogram.data
    private val timeline = data.timeline
    private val timer = histogram.timer

    private val buttonSize = 2.0
    private var buttonHeight = buttonSize
    private val buttonOffset = -4.0

    private val start = 2.0
    private val end = 98.0

    fun initPosition(canvas: Canvas) {
        this.position.value = Vector(canvas.chartXMin, (canvas.yMin + canvas.chartYMin - 1) / 2)
        buttonHeight = canvas.widthToHeight(buttonSize)
    }

    override fun drawSelf(canvas: Canvas) {
        canvas.color = theme.titleColor.value
        canvas.filledCircle(buttonOffset, 0.0, buttonSize)
        canvas.color = theme.background.value
        canvas.filledCircle(buttonOffset, 0.0, buttonSize * 0.8)
        canvas.color = theme.titleColor.value
        if (timer.paused) {
            canvas.filledPolygon(
                    arrayOf(buttonOffset - buttonSize * 0.4,
                            buttonOffset - buttonSize * 0.4,
                            buttonOffset + buttonSize * 0.5),
                    arrayOf(buttonHeight * 0.5,
                            buttonHeight * -0.5,
                            0.0
                    ))
        } else {
            canvas.filledRectangle(buttonOffset - buttonSize * 0.2, 0.0,
                    buttonSize * 0.1, buttonHeight * 0.5)
            canvas.filledRectangle(buttonOffset + buttonSize * 0.2, 0.0,
                    buttonSize * 0.1, buttonHeight * 0.5)
        }

        canvas.filledRectangle((start + end) / 2, 0.0, (end - start) / 2, 0.1)

        val size = timeline.timeline.size
        val x = start + (end - start) * (timeline.now.toDouble() / (size - 1))
        canvas.color = theme.titleColor.value
        canvas.filledCircle(x, 0.0, buttonSize)
        canvas.color = theme.background.value
        canvas.filledCircle(x, 0.0, buttonSize * 0.8)
    }

    override fun handleAction(action: Action) {
        if (action !is ClickAction) {
            return
        }
        if (abs(action.x - buttonOffset) <= buttonSize && abs(action.y) <= buttonHeight) {
            timer.paused = !timer.paused
            action.done()
        }
        if (action.x in start..end && abs(action.y) <= buttonHeight / 2) {
            val percent = (action.x - start) / (end - start)
            timer.paused = true
            timeline.now = round(percent * (timeline.timeline.size - 1)).toInt()
            histogram.update()
            action.done()
        }
    }

}