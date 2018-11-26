package taskc.component

import taskc.Canvas
import taskc.property.OpacityProperty
import taskc.property.PositionProperty

abstract class Component {

    var level: Int = 0
    val position: PositionProperty = PositionProperty()
    val opacity: OpacityProperty = OpacityProperty(1.0)

    protected val components: MutableList<Component> = mutableListOf()

    open fun draw(canvas: Canvas) {
        components.sortedBy { it.level }.forEach {
            val v = it.position.value
            canvas.moveCenter(v.x, v.y)
            it.draw(canvas)
            canvas.moveCenter(-v.x, -v.y)
        }
    }


}