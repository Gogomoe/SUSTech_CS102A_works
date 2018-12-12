package taskc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import taskc.component.Histogram
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import javax.swing.JPanel

class Timer(val ticksPerTime: Int, val ticksPerSecond: Int) {

    var paused = false

    private val waitTime: Long = 1000L / ticksPerSecond

    private lateinit var histogram: Histogram

    fun start(histogram: Histogram) {
        this.histogram = histogram
        histogram.init()
        histogram.paint()
        GlobalScope.launch {
            histogram.timer.paused = true
            histogram.paint()
            delay(1000)
            histogram.timer.paused = false

            var tick = ticksPerTime
            while (true) {
                if (tick >= ticksPerTime && histogram.hasNextTime() && !paused) {
                    histogram.nextTime()
                    tick = 0
                }
                delay(waitTime - 6)
                //TODO Notice 6ms 是每次绘图大概需要的时间
                histogram.tick()
                tick++
                histogram.paint()
            }
        }
    }

}