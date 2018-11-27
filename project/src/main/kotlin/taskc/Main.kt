package taskc

import taskc.component.Histogram
import java.awt.Color
import javax.json.Json
import javax.json.JsonArray
import javax.json.JsonObject
import javax.swing.JFrame

const val fileName = "data-c.json"

fun main(args: Array<String>) {
    val canvas = Canvas()
    canvas.setSize(1280, 720)

    val reader = Json.createReader(Data::class.java.classLoader.getResourceAsStream(fileName)).readObject()

    val timeline = readTimeline(reader)
    val data = readData(timeline, reader)
    val timer = readTimer(reader)

    val histogram = readHistogram(canvas, data, timer, reader)

    timer.start(histogram)
}


private fun readTimeline(reader: JsonObject) = Timeline(reader.getJsonArray("timeline").toStringArray())

private fun readData(timeline: Timeline, reader: JsonObject): Data {
    val data = Data(timeline)

    val allItems = reader.getJsonArray("data")
    allItems.indices.forEach { i ->
        val itemReader = allItems.getJsonObject(i)
        val name = itemReader.getString("name")
        val color = itemReader.getJsonArray("color").toColor()

        val item = Item(name, color)

        val info = itemReader.getJsonObject("data")
        info.keys.forEach {
            item.data[it] = info.getInt(it)
        }

        data.items.add(item)
    }
    return data
}

fun readTimer(reader: JsonObject): Timer {
    val timer = reader.getJsonObject("timer")
    return Timer(timer.getInt("ticks-per-time"), timer.getInt("ticks-per-second"))
}

private fun readHistogram(canvas: Canvas, data: Data, timer: Timer, reader: JsonObject): Histogram {
    val r = reader.getJsonObject("histogram")
    val margin = r.getJsonArray("margin").toDoubleArray()
    val histogram = Histogram(canvas, data, timer)
    histogram.setHistogram(margin[0], margin[1], margin[2], margin[3], r.getInt("item-count"))
    return histogram
}


private fun JsonArray.toStringArray(): Array<String> {
    val list = mutableListOf<String>()
    indices.forEach {
        list.add(getString(it))
    }
    return list.toTypedArray()
}

private fun JsonArray.toColor(): Color {
    val c = this.toIntArray()
    return Color(c[0], c[1], c[2])
}

private fun JsonArray.toIntArray(): IntArray {
    val array = IntArray(size)
    indices.forEach {
        array[it] = this.getInt(it)
    }
    return array
}

private fun JsonArray.toDoubleArray(): DoubleArray {
    val array = DoubleArray(size)
    indices.forEach {
        array[it] = this.getJsonNumber(it).doubleValue()
    }
    return array
}
