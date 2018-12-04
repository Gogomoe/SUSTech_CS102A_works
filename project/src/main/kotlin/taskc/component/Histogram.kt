package taskc.component

import taskc.Canvas
import taskc.Data
import taskc.Timer
import taskc.property.Vector


class Histogram(val canvas: Canvas, val data: Data, val timer: Timer) : Component() {

    private val itemsComponent: ItemsComponent = ItemsComponent(data)

    private var title: TitleComponent = TitleComponent()

    init {
        canvas.setHistogram()
        components.add(itemsComponent)
        components.add(title)
        itemsComponent.update(data.getCurrentStatus(), timer.ticksPerTime)
    }

    fun setHistogram(marginTop: Double, marginRight: Double,
                     marginBottom: Double, marginLeft: Double,
                     itemCount: Int) {
        itemsComponent.itemCount = itemCount
        canvas.setHistogram(marginTop, marginRight, marginBottom, marginLeft, itemCount)
    }

    fun setTitle(title: TitleComponent) {
        components.remove(this.title)
        this.title = title
        title.position.value = Vector((canvas.chartXMin + canvas.chartXMax) / 2,
                (canvas.chartYMax + canvas.yMax) / 2)
        components.add(title)
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