package taskc

class Timeline(val timeline: Array<String>) {


    var now: Int = 0
        private set

    val time
        get() = timeline[now]

    fun hasNext(): Boolean = now < timeline.size - 1
    fun next(): String = timeline[++now]
}