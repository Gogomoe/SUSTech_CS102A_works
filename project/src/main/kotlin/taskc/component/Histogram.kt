package taskc.component

import taskc.Canvas
import taskc.Data
import taskc.Theme
import taskc.Timer
import taskc.action.ClickAction
import taskc.property.Vector
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import javax.swing.JPanel


class Histogram(val canvas: Canvas, val data: Data, val timer: Timer) : Component() {

    val theme: Theme = Theme()

    private val items: ItemsComponent = ItemsComponent(data, theme)
    private val background: BackgroundComponent = BackgroundComponent(theme)
    private var title: TitleComponent = TitleComponent("", theme)
    private val themeSelector: ThemeComponent = ThemeComponent(theme)
    private val timelineController: TimelineController = TimelineController(this, theme)

    init {
        canvas.setHistogram()
        components.add(background)
        components.add(items)
        components.add(title)
        components.add(themeSelector)
        components.add(timelineController)
        update()
    }

    fun setHistogram(marginTop: Double, marginRight: Double,
                     marginBottom: Double, marginLeft: Double,
                     itemCount: Int) {
        items.itemCount = itemCount
        canvas.setHistogram(marginTop, marginRight, marginBottom, marginLeft, itemCount)
        themeSelector.initPosition(canvas)
        timelineController.initPosition(canvas)
    }

    fun setTitle(title: TitleComponent) {
        components.remove(this.title)
        this.title = title
        title.position.value = Vector((canvas.chartXMin + canvas.chartXMax) / 2,
                (canvas.chartYMax + canvas.yMax) / 2)
        components.add(title)
    }

    fun enableContorller(enable: Boolean) {
        if (enable) {
            components.add(timelineController)
        } else {
            components.remove(timelineController)
        }
    }

    fun hasNextTime(): Boolean = data.hasNextTime()

    fun nextTime() {
        data.nextTime()
        update()
    }

    fun update() {
        items.update(timer.ticksPerTime)
    }

    fun paint() {
        draw(canvas)
        panel.repaint()
    }

    private val frame = JFrame("Graph")
    private val panel = object : JPanel() {
        override fun paint(g: Graphics?) {
            super.paint(g)

            val g2d = g as Graphics2D
            g2d.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY)
            g2d.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

            g2d.drawImage(canvas.image, 0, 0,
                    canvas.width, canvas.height, null)
        }

        init {
            this.addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent?) {
                    e!!
                    receiveAction(ClickAction(
                            canvas.xMin + canvas.offsetX + (canvas.xMax - canvas.xMin) * e.x / canvas.width,
                            canvas.yMax + canvas.offsetY - (canvas.yMax - canvas.yMin) * e.y / canvas.height))
                }
            })
        }
    }

    fun init() {
        draw(canvas)

        frame.contentPane.add(panel, BorderLayout.CENTER)
        panel.setSize(canvas.width, canvas.height)
        panel.preferredSize = Dimension(canvas.width, canvas.height)
        frame.pack()
        frame.isResizable = false
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
        frame.requestFocusInWindow()
    }
}