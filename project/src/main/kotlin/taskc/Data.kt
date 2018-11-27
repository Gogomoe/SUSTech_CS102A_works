package taskc

class Data(val timeline: Timeline) {

    val items: MutableList<Item> = mutableListOf()

    fun hasNextTime(): Boolean = timeline.hasNext()

    fun nextTime() {
        timeline.next()
    }

    fun getCurrentStatus(): List<ItemStatus> = mutableListOf<ItemStatus>().also { list ->
        items.forEach {
            list.add(ItemStatus(it, timeline.time))
        }
        list.sortByDescending { it.value }
    }

}