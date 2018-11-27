package taskc

import java.awt.Color

class Item(val name: String, val color: Color) {
    val data: MutableMap<String, Int> = mutableMapOf()
}