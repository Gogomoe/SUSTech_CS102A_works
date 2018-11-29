package taskc.component

import taskc.Canvas
import taskc.animation.TextAnimation
import taskc.property.TextProperty
import java.awt.Color
import java.awt.Font

class TimelineTextComponent(time: String) : Component() {

    val time: TextProperty = TextProperty()

    init {
        this.time.value = time
    }

    fun update(time: String, ticks: Int) {
        this.time.applyAnimation(TextAnimation(time, ticks))
    }

    override fun drawSelf(canvas: Canvas) {
        // TODO move font to other place
        canvas.setFont(Font("Microsoft YaHei Light", Font.PLAIN, 64))
        canvas.color = Color(60, 60, 60)
        canvas.textRight(100.0, 0.3, time.value)
    }

    override fun tick() {
        super.tick()
        time.tick()
    }
}