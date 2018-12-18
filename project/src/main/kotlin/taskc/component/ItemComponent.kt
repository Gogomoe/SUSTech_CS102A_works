package taskc.component

import taskc.Canvas
import taskc.Item
import taskc.animation.Animation
import taskc.animation.ValueAnimation
import taskc.property.ValueProperty
import java.awt.Font

class ItemComponent(val item: Item, val maxGetter: () -> Int) : Component() {

    init {
        level = 10
        opacity.value = 0.9f
    }

    val value: ValueProperty = ValueProperty()

    override fun drawSelf(canvas: Canvas) {
        // TODO move font to other place
        canvas.setFont(Font("Microsoft YaHei Light", Font.PLAIN, 16))
        val width = value.value.toDouble() / maxGetter() * 100
        canvas.color = item.color
        canvas.filledRectangle(width / 2, 0.0, width / 2, 0.25)
        canvas.textRight(-1.0, 0.0, item.name)
        canvas.textLeft(width + 1.0, 0.0, value.value.toString())
    }

    override fun applyAnimation(it: Animation<*>) {
        super.applyAnimation(it)
        when (it) {
            is ValueAnimation -> value.applyAnimation(it)
        }
    }

    override fun tick() {
        super.tick()
        value.tick()
    }

}