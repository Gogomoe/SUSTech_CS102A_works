import io.kotlintest.matchers.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ComplexTest : StringSpec() {
    init {
        "test equals and hashcode"{
            Complex(3.0, 4.0) shouldBe Complex(3.0, 4.0)
            Complex(1.2, -100.7) shouldBe Complex(1.2, -100.7)
            Complex(65535.111, -2345.789) shouldBe Complex(65535.111, -2345.789)

            Complex(1.2, -100.7).hashCode() shouldBe Complex(1.2, -100.7).hashCode()
            Complex(65535.111, -2345.789).hashCode() shouldBe Complex(65535.111, -2345.789).hashCode()
        }
        "test toString"{
            Complex(3.0, 4.0).toString() shouldBe "3.00+4.00i"
            Complex(3.0, -4.0).toString() shouldBe "3.00-4.00i"
            Complex(47.0, 0.0).toString() shouldBe "47.00"
            Complex(0.0, 23.5).toString() shouldBe "23.50i"
            Complex(0.0, -23.5).toString() shouldBe "-23.50i"
        }
        "test real and imaginary"{
            Complex(1.2, 34.0).real shouldBe (1.2 plusOrMinus 0.0001)
            Complex(456.78, -2.0).real shouldBe (456.78 plusOrMinus 0.0001)

            Complex(0.5, -12.7).imaginary shouldBe (-12.7 plusOrMinus 0.0001)
            Complex(3.0, 4.0).imaginary shouldBe (4.0 plusOrMinus 0.0001)
        }
        "test unary minus"{
            -Complex(7.0, 17.0) shouldBe Complex(-7.0, -17.0)
            -Complex(-3.0, 5.0) shouldBe Complex(3.0, -5.0)
        }
        "test plus"{
            Complex(12.0, 8.0) + Complex(0.0, 0.0) shouldBe Complex(12.0, 8.0)
            Complex(1.2, 2.0) + Complex(4.4, 5.6) shouldBe Complex(5.6, 7.6)
            Complex(999.0, -2.0) + Complex(-999.0, 5.6) shouldBe Complex(0.0, 3.6)
        }
        "test minus"{
            Complex(1.2, 2.0) - Complex(4.4, 5.6) shouldBe Complex(-3.2, -3.6)
            Complex(-27.5, -2.0) - Complex(-82.1, 20.0) shouldBe Complex(54.6, -22.0)

        }
        "test times a real number"{
            Complex(2.5, 3.0) * 2.0 shouldBe Complex(5.0, 6.0)
        }
        "test times a complex number"{
            Complex(1.0, 3.0) * Complex(5.0, -4.0) shouldBe Complex(17.0, 11.0)
            Complex(-2.0, 2.0) * Complex(4.0, 2.0) shouldBe Complex(-12.0, 4.0)
        }
        "test div a real number"{
            Complex(5.0, 6.0) / 2.0 shouldBe Complex(2.5, 3.0)
        }
        "test div a complex number"{
            Complex(1.0, 3.0) / Complex(2.0, 1.0) shouldBe Complex(1.0, 1.0)
        }
        "test length"{
            Complex(3.0, 4.0).length() shouldBe (5.0 plusOrMinus 0.0001)
            Complex(7.0, 24.0).length() shouldBe (25.0 plusOrMinus 0.0001)
        }
    }
}