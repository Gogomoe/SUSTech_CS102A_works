package taskc

import java.awt.Color

class ItemStatus(val item: Item, val time: String) {

    val name: String = item.name

    val value: Int = item.data[time]!!
    
}