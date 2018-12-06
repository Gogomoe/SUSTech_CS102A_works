package taskc.action

class ClickAction(var x: Double, var y: Double) : Action() {
    override fun toString(): String {
        return "ClickAction(x=$x, y=$y)"
    }
}