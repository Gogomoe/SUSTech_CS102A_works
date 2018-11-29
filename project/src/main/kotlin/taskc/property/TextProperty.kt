package taskc.property

open class TextProperty(override var value: String = "") : Property<String>() {

    override fun interpolate(nowTick: Int): String =
            if (nowTick < totalTicks / 2) startVal else endValue!!

}