package taskc

import taskc.property.ColorProperty
import java.awt.Color
import java.awt.Font

class Theme {
    val background: ColorProperty = ColorProperty(Color(250, 250, 250))

    var titleFont: Font = Font("Microsoft YaHei", Font.PLAIN, 32)
    var titleColor: ColorProperty = ColorProperty(Color(72, 72, 72))

    var rulerFont: Font = Font("Microsoft YaHei Light", Font.PLAIN, 16)
    val rulerColor: ColorProperty = ColorProperty(Color(160, 160, 160, 93))

    var timelineFont: Font = Font("Microsoft YaHei Light", Font.PLAIN, 64)
    var timelineColor: ColorProperty = ColorProperty(Color(60, 60, 60))
}