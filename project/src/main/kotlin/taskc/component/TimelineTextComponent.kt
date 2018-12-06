package taskc.component

import taskc.Canvas
import taskc.Theme
import taskc.animation.TextAnimation
import taskc.property.TextProperty
import java.awt.Color
import java.awt.Font

class TimelineTextComponent(time: String, private val theme: Theme) : Component() {

    val time: TextProperty = TextProperty()

    init {
        this.time.value = time
    }

    fun update(time: String, ticks: Int) {
        this.time.applyAnimation(TextAnimation(time, ticks))
    }

    override fun drawSelf(canvas: Canvas) {
        canvas.setFont(theme.timelineFont)
        canvas.color = theme.timelineColor.value
        canvas.textRight(100.0, 0.3, time.value)
    }

    override fun tick() {
        super.tick()
        time.tick()
    }
}