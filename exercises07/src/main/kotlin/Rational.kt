import kotlin.math.abs

class Rational {

    val numerator: Int
    val denominator: Int

    constructor(numerator: Int, denominator: Int) {
        if (denominator == 0) {
            throw IllegalArgumentException("the denominator cannot be zero")
        }
        val gcd = gcd(numerator, denominator)
        val signum = Integer.signum(numerator * denominator)
        this.numerator = signum * abs(numerator) / gcd
        this.denominator = abs(denominator) / gcd
    }

    constructor(number: Int) : this(number, 1)

    /**
     * Rational will normalize on init
     */
    fun normalize(): Rational = this

    operator fun unaryMinus(): Rational = Rational(-numerator, denominator)

    operator fun plus(other: Rational): Rational =
            Rational(numerator * other.denominator + other.numerator * denominator,
                    denominator * other.denominator)

    operator fun minus(other: Rational): Rational = this + (-other)

    operator fun times(other: Rational): Rational =
            Rational(numerator * other.numerator, denominator * other.denominator)

    operator fun div(other: Rational): Rational =
            Rational(numerator * other.denominator, denominator * other.numerator)

    override fun toString(): String = if (denominator == 1) "$numerator" else "$numerator/$denominator"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rational

        if (numerator != other.numerator) return false
        if (denominator != other.denominator) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        return result
    }

}

fun gcd(a: Int, b: Int): Int {
    var x = abs(a)
    var y = abs(b)
    while (y != 0) {
        val r = y
        y = x % y
        x = r
    }
    return x
}