package taskc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import taskc.component.Histogram
import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel

class Timer(val ticksPerTime: Int, val ticksPerSecond: Int) {

    val waitTime: Long = 1000L / ticksPerSecond

    fun start(histogram: Histogram) {
        init(histogram)
        paint()
        GlobalScope.launch {
            delay(1000)

            while (histogram.hasNextTime()) {
                histogram.nextTime()
                var tick = 0
                while (tick < ticksPerTime) {
                    delay(waitTime)
                    histogram.tick()
                    tick++
                    paint()
                }
            }
        }
    }


    private lateinit var histogram: Histogram


    private fun paint() {
        histogram.clear()
        histogram.draw()
        panel.repaint()
    }

    private val frame = JFrame("Title")
    private val panel = object : JPanel() {
        override fun paint(g: Graphics?) {
            super.paint(g)
            if (!this@Timer::histogram.isInitialized) {
                return
            }

            val g2d = g as Graphics2D
            g2d.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY)
            g2d.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

            g2d.drawImage(histogram.canvas.image, 0, 0,
                    histogram.canvas.width, histogram.canvas.height, null)
        }
    }

    private fun init(histogram: Histogram) {
        this.histogram = histogram
        frame.contentPane.add(panel, BorderLayout.CENTER)
        panel.setSize(histogram.canvas.width, histogram.canvas.height)
        panel.preferredSize = Dimension(histogram.canvas.width, histogram.canvas.height)
        frame.pack()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
        frame.requestFocusInWindow()
    }

}