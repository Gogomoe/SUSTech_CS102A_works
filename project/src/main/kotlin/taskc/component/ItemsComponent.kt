package taskc.component

import taskc.Data
import taskc.ItemStatus
import taskc.animation.Animation
import taskc.animation.OpacityAnimation
import taskc.animation.PositionAnimation
import taskc.animation.ValueAnimation
import taskc.property.Vector
import kotlin.math.max

class ItemsComponent(val data: Data) : Component() {

    var itemCount: Int = 16

    val items: MutableMap<String, ItemComponent> = mutableMapOf()

    private lateinit var status: List<ItemStatus>

    var maxValue: Int = 0
        private set

    init {
        val list: List<ItemStatus> = data.getCurrentStatus()
        data.getCurrentStatus().forEachIndexed { index, itemStatus ->
            val c = ItemComponent(itemStatus.item, this)
            items[itemStatus.name] = c
            c.position.value = Vector(0.0, (itemCount - index - 1).toDouble())
            c.value.value = c.item.data[data.timeline.time]!!
            if (itemCount - index - 1 < 0) {
                c.opacity.value = 0f
            }
            this.components.add(c)
        }
    }

    fun update(currentStatus: List<ItemStatus>, ticks: Int) {
        currentStatus.forEachIndexed { index, itemStatus ->
            val animations = mutableListOf<Animation<*>>(
                    PositionAnimation(Vector(0.0, (itemCount - index - 1).toDouble()),
                            ticks / 2, 10),
                    ValueAnimation(itemStatus.value, ticks)
            )
            val y = items[itemStatus.name]!!.position.value.y
            if (y < 0 && itemCount - index - 1 >= 0) {
                animations.add(OpacityAnimation(1f, ticks / 2, 10))
            } else if (y >= 0 && itemCount - index - 1 < 0) {
                animations.add(OpacityAnimation(0f, ticks / 2, 10))
            }
            items[itemStatus.name]!!.applyAnimations(animations)
        }
        status = currentStatus
        maxValue = currentStatus[0].value
    }

    override fun tick() {
        super.tick()
        maxValue = max(items[status[0].name]!!.value.value, items[status[1].name]!!.value.value)
    }
}