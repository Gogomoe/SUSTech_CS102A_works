package taskc.component

import taskc.Canvas
import taskc.Data
import taskc.Timer


class Histogram(val canvas: Canvas, val data: Data, val timer: Timer) : Component() {

    private val itemsComponent = ItemsComponent(data)

    init {
        canvas.setHistogram()
        components.add(itemsComponent)
        itemsComponent.update(data.getCurrentStatus(), timer.ticksPerTime)
    }

    fun setHistogram(marginTop: Double, marginRight: Double,
                     marginBottom: Double, marginLeft: Double,
                     itemCount: Int) {
        itemsComponent.itemCount = itemCount
        canvas.setHistogram(marginTop, marginRight, marginBottom, marginLeft, itemCount)
    }


    fun draw() {
        draw(canvas)
    }

    fun hasNextTime(): Boolean = data.hasNextTime()

    fun nextTime() {
        data.nextTime()
        itemsComponent.update(data.getCurrentStatus(), timer.ticksPerTime)
    }

    fun clear() {
        canvas.clear()
    }

}