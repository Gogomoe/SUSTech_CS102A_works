package taskc.component

import taskc.Canvas
import taskc.action.Action
import taskc.action.ClickAction
import taskc.animation.Animation
import taskc.animation.OpacityAnimation
import taskc.animation.PositionAnimation
import taskc.property.OpacityProperty
import taskc.property.PositionProperty

abstract class Component {

    var level: Int = 0
    val position: PositionProperty = PositionProperty()
    val opacity: OpacityProperty = OpacityProperty(1f)

    protected val components: MutableList<Component> = mutableListOf()

    fun draw(canvas: Canvas) {
        canvas.opacity = opacity.value
        components.sortedBy { it.level }.forEach {
            val v = it.position.value
            canvas.moveCenter(v.x, v.y)
            it.draw(canvas)
            canvas.moveCenter(-v.x, -v.y)
        }
        drawSelf(canvas)
        canvas.opacity = 1f
    }

    open fun drawSelf(canvas: Canvas) {}

    open fun applyAnimations(animations: List<Animation<*>>) {
        animations.forEach {
            applyAnimation(it)
        }
    }

    open fun applyAnimation(it: Animation<*>) {
        when (it) {
            is OpacityAnimation -> opacity.applyAnimation(it)
            is PositionAnimation -> position.applyAnimation(it)
        }
    }

    open fun tick() {
        position.tick()
        opacity.tick()
        components.forEach { it -> it.tick() }
    }

    fun receiveAction(action: Action) {
        components.sortedByDescending { it.level }.forEach {
            offsetAction(action, it) {
                it.handleAction(action)
            }
            if (action.done) {
                return
            }
        }
        handleAction(action)
    }

    open fun handleAction(action: Action) {}

    private inline fun offsetAction(action: Action, component: Component, todo: () -> Unit) {
        if (action is ClickAction) {
            action.x -= component.position.value.x
            action.y -= component.position.value.y
        }
        todo()
        if (action is ClickAction) {
            action.x += component.position.value.x
            action.y += component.position.value.y
        }
    }

}