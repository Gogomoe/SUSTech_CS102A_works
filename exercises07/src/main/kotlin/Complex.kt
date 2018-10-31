import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

fun pow2(x: Double): Double = x.pow(2.0)

class Complex(val real: Double, val imaginary: Double) {

    private val realStr = String.format("%.2f", real)
    private val imaginaryStr = String.format("%.2f", imaginary)

    operator fun unaryMinus(): Complex = Complex(-real, -imaginary)

    operator fun plus(other: Complex): Complex =
            Complex(real + other.real, imaginary + other.imaginary)

    operator fun minus(other: Complex): Complex = this + (-other)

    operator fun times(multiplier: Double): Complex =
            Complex(real * multiplier, imaginary * multiplier)

    operator fun times(other: Complex): Complex =
            Complex(real * other.real - imaginary * other.imaginary,
                    real * other.imaginary + imaginary * other.real)

    fun length() = sqrt(pow2(real) + pow2(imaginary))

    operator fun div(divisor: Double): Complex = this * (1 / divisor)

    operator fun div(other: Complex): Complex =
            this * Complex(other.real, -other.imaginary) / pow2(other.length())

    override fun toString(): String {
        val realNumber = if (real != 0.0) realStr else ""

        val operator = when {
            imaginary == 0.0 -> ""
            imaginary < 0 -> "-"
            real != 0.0 -> "+"
            else -> ""
        }
        val imaginaryNumber =
                if (imaginary != 0.0) String.format("%.2fi", (abs(imaginary)))
                else ""

        return "$realNumber$operator$imaginaryNumber"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Complex

        if (realStr != other.realStr) return false
        if (imaginaryStr != other.imaginaryStr) return false

        return true
    }

    override fun hashCode(): Int {
        var result = realStr.hashCode()
        result = 31 * result + imaginaryStr.hashCode()
        return result
    }


}