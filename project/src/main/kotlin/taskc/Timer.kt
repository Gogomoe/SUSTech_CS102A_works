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

    private val waitTime: Long = 1000L / ticksPerSecond

    private lateinit var histogram: Histogram

    fun start(histogram: Histogram) {
        this.histogram = histogram
        histogram.init()
        histogram.paint()
        GlobalScope.launch {
            delay(1000)

            var tick = ticksPerTime
            while (true) {
                if (tick >= ticksPerTime && histogram.hasNextTime()) {
                    histogram.nextTime()
                    tick = 0
                }
                delay(waitTime)
                histogram.tick()
                tick++
                histogram.paint()
            }
        }
    }

}