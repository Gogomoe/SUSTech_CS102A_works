import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class RationalTest : StringSpec() {
    init {
        "test equals and hashcode"{
            Rational(5) shouldBe Rational(5)
            Rational(-8, 3) shouldBe Rational(-8, 3)
            Rational(555666, 111) shouldBe Rational(5006)

            Rational(5).hashCode() shouldBe Rational(5).hashCode()
            Rational(-8, 3).hashCode() shouldBe Rational(-8, 3).hashCode()
            Rational(555666, 111).hashCode() shouldBe Rational(5006).hashCode()
        }
        "test toString"{
            Rational(5).toString() shouldBe "5"
            Rational(5, 3).toString() shouldBe "5/3"
            Rational(9, 3).toString() shouldBe "3"
            Rational(-6, 10).toString() shouldBe "-3/5"
            Rational(-6, -12).toString() shouldBe "1/2"
        }
        "test plus"{
            Rational(1, 3) + Rational(1, 3) shouldBe Rational(2, 3)
            Rational(1, 3) + Rational(2, 3) shouldBe Rational(1)
            Rational(1, 5) + Rational(1, 6) shouldBe Rational(11, 30)
        }
        "test minus"{
            Rational(1, 3) - Rational(1, 3) shouldBe Rational(0)
            Rational(1, 3) - Rational(2, 3) shouldBe Rational(-1, 3)
            Rational(1, 5) - Rational(1, 6) shouldBe Rational(1, 30)
        }
        "test times"{
            Rational(1, 3) * Rational(1, 3) shouldBe Rational(1, 9)
            Rational(1, 3) * Rational(2, 3) shouldBe Rational(2, 9)
            Rational(1, 5) * Rational(1, 6) shouldBe Rational(1, 30)
        }
        "test div"{
            Rational(1, 3) / Rational(1, 3) shouldBe Rational(1)
            Rational(1, 3) / Rational(2, 3) shouldBe Rational(1, 2)
            Rational(1, 5) / Rational(1, 6) shouldBe Rational(6, 5)
        }
        "test normalize"{
            Rational(36,1) shouldBe Rational(36)
            Rational(36,2) shouldBe Rational(18)
            Rational(36,3) shouldBe Rational(12)
            Rational(36,4) shouldBe Rational(9)
            Rational(25,15) shouldBe Rational(5,3)
            Rational(25,20) shouldBe Rational(5,4)
        }
    }
}