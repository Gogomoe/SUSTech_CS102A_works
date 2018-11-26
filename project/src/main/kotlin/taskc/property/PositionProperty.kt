package taskc.property

class PositionProperty(override var value: Vector = Vector()) : Property<Vector>() {

    override var nowTick: Int = 0
    override var totalTicks: Int = 0
    override var startVal: Vector = value
    override var endValue: Vector? = null

    override fun interpolate(nowTick: Int): Vector =
            value * startWeight() + endValue!! * endWeight()

}