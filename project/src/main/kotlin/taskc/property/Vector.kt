package taskc.property

class Vector(val x: Double = 0.0, val y: Double = 0.0) {

    operator fun times(times: Double): Vector = Vector(x * times, y * times)

    operator fun plus(other: Vector): Vector = Vector(x + other.x, y + other.y)
}