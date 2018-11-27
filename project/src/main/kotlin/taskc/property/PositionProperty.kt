package taskc.property

class PositionProperty(override var value: Vector = Vector()) : Property<Vector>() {

    override fun interpolate(nowTick: Int): Vector =
            startVal * startWeight() + endValue!! * endWeight()

}