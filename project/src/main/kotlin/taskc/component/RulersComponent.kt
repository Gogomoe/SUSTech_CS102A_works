package taskc.component

import taskc.Canvas
import taskc.animation.OpacityAnimation
import java.lang.Math.pow
import kotlin.math.floor
import kotlin.math.log10

class RulersComponent(val maxGetter: () -> Int) : Component() {

    private val rulers: MutableMap<Int, RulerComponent> = mutableMapOf()

    init {
        super.level = -5
    }

    override fun drawSelf(canvas: Canvas) {
        updateRulers()
    }

    private fun updateRulers() {
        val newRulers = calculateRulers()
        val intersection = rulers.keys.intersect(newRulers)
        val toRemove = rulers.keys - intersection
        val toAdd = newRulers - intersection
        removeRulers(toRemove.map { rulers[it]!! }.toSet())
        addRulers(toAdd)
    }

    private fun calculateRulers(): Set<Int> {
        val maxValue = maxGetter()
        val level = floor(log10(maxValue.toDouble()))

        val max = (maxValue * pow(10.0, -(level - 1))).toInt()

        val factor = when {
            max in 15..30 -> 2
            max in 31..70 -> 5
            max > 70 -> 10
            max <= 14 -> 1
            else -> throw RuntimeException("???")
        }

        return (factor..max step factor)
                .map { it * pow(10.0, (level - 1)).toInt() }
                .toSet()
    }

    private fun addRulers(toRemove: Set<Int>) {
        toRemove.map { RulerComponent(it, maxGetter) }
                .forEach {
                    it.opacity.value = 0f
                    it.applyAnimation(OpacityAnimation(1.0f, 20))
                    rulers[it.value] = it
                    components.add(it)
                }
    }

    private val toRemove: MutableSet<RulerComponent> = mutableSetOf()

    private fun removeRulers(toRemove: Set<RulerComponent>) {
        (toRemove - this.toRemove).forEach {
            it.applyAnimation(OpacityAnimation(0.0f, 20))
        }
        this.toRemove.addAll(toRemove)
        this.toRemove.filter { it.opacity.value <= 0.01f }
                .forEach {
                    components.remove(it)
                    rulers.remove(it.value)
                    this.toRemove.remove(it)
                }
    }

}