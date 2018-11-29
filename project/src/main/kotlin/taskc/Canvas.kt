package taskc

import java.awt.*
import java.awt.geom.*
import java.awt.image.BufferedImage
import java.net.MalformedURLException
import java.net.URL
import javax.swing.ImageIcon
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

class Canvas {

    var width: Int = 1280
        private set
    var height: Int = 720
        private set

    var color = Color.BLACK
        set(value) {
            field = value
            graphics.color = Color(field.red, field.green, field.blue,
                    (255 * opacity).roundToInt())
        }
    val clearColor = Color.WHITE

    var opacity: Float = 1f
        set(value) {
            field = value
            graphics.color = Color(color.red, color.green, color.blue,
                    (255 * opacity).roundToInt())
        }

    var penRadius = 0.004
        set(value) {
            field = value
            val scaledPenRadius = (value * width.toDouble() * scale).toFloat()
            val stroke = BasicStroke(scaledPenRadius, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND)
            graphics.stroke = stroke
        }
    private var font = Font("Microsoft YaHei Light", Font.PLAIN, 32)

    fun setFont(value: Font) {
        font = value.deriveFont(value.size.toFloat() * 2)
    }

    fun getFont(): Font = font.deriveFont(font.size.toFloat() / 2)

    var xMin: Double = 0.0
        private set
    var xMax: Double = 1.0
        private set
    var yMin: Double = 0.0
        private set
    var yMax: Double = 1.0
        private set

    var scale: Double = 2.0

    var image: BufferedImage = BufferedImage(
            (width * scale).toInt(), (height * scale).toInt(), BufferedImage.TYPE_4BYTE_ABGR)
        private set(value) {
            field = value
            graphics = value.createGraphics()
        }
    private var graphics: Graphics2D = image.createGraphics()

    var marginTop: Double = 0.2
        private set
    var marginRight: Double = 0.1
        private set
    var marginBottom: Double = 0.15
        private set
    var marginLeft: Double = 0.1
        private set

    var chartXMin: Double = 0.0
        private set
    var chartXMax: Double = 100.0
        private set
    var chartYMin: Double = 0.0
        private set
    var chartYMax: Double = 15.0
        private set

    fun setHistogram(marginTop: Double = 0.2, marginRight: Double = 0.1,
                     marginBottom: Double = 0.15, marginLeft: Double = 0.1,
                     itemCount: Int = 16) {
        this.marginTop = marginTop
        this.marginRight = marginRight
        this.marginBottom = marginBottom
        this.marginLeft = marginLeft

        chartXMin = 0.0
        chartXMax = 100.0
        chartYMin = 0.0
        chartYMax = (itemCount - 1).toDouble()

        val ySpacing = (chartYMax - chartYMin) / (1.0 - marginTop - marginBottom)
        val windowYMin = chartYMin - marginBottom * ySpacing
        val windowYMax = chartYMax + marginTop * ySpacing

        val xSpacing = (chartXMax - chartXMin) / (1.0 - marginLeft - marginRight)
        val windowXMin = chartXMin - marginLeft * xSpacing
        val windowXMax = chartXMax + marginRight * xSpacing

        setScale(windowXMin, windowXMax, windowYMin, windowYMax)
    }

    fun setSize(width: Int, height: Int) {
        this.width = width
        this.height = height
        init()
    }

    private fun init() {
        image = BufferedImage((width * scale).toInt(), (height * scale).toInt(),
                BufferedImage.TYPE_INT_ARGB)
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON)
        graphics.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY)
        graphics.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
        clear()
    }

    fun setScale(xMin: Double, xMax: Double, yMin: Double, yMax: Double) {
        this.xMin = xMin
        this.xMax = xMax
        this.yMin = yMin
        this.yMax = yMax
    }

    fun moveCenter(xOffset: Double, yOffset: Double) {
        xMin -= xOffset
        xMax -= xOffset
        yMin -= yOffset
        yMax -= yOffset
    }

    private fun scaleX(x: Double): Double {
        return scale * width.toDouble() * (x - xMin) / (xMax - xMin)
    }

    private fun scaleY(y: Double): Double {
        return scale * height.toDouble() * (yMax - y) / (yMax - yMin)
    }

    private fun factorX(w: Double): Double {
        return scale * w * width.toDouble() / Math.abs(xMax - xMin)
    }

    private fun factorY(h: Double): Double {
        return scale * h * height.toDouble() / Math.abs(yMax - yMin)
    }

    fun clear() {
        graphics.color = clearColor
        graphics.fillRect(0, 0, (width * scale).toInt(), (height * scale).toInt())
    }

    fun line(x0: Double, y0: Double, x1: Double, y1: Double) {
        graphics.draw(Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)))
    }

    private fun pixel(x: Double, y: Double) {
        graphics.fillRect(scaleX(x).roundToInt(), scaleY(y).roundToInt(), 1, 1)
    }

    fun point(x: Double, y: Double) {
        val xs = scaleX(x)
        val ys = scaleY(y)
        val r = penRadius
        val scaledPenRadius = (r * width).toFloat()

        if (scaledPenRadius <= 1)
            pixel(x, y)
        else
            graphics.fill(Ellipse2D.Double(xs - scaledPenRadius / 2, ys - scaledPenRadius / 2,
                    scaledPenRadius.toDouble(), scaledPenRadius.toDouble()))
    }

    fun circle(x: Double, y: Double, radius: Double) {
        if (radius < 0) throw IllegalArgumentException("radius must be nonnegative")
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * radius)
        val hs = factorY(2 * radius)
        if (ws <= 1 && hs <= 1)
            pixel(x, y)
        else
            graphics.draw(Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs))
    }

    fun filledCircle(x: Double, y: Double, radius: Double) {
        if (radius < 0) throw IllegalArgumentException("radius must be nonnegative")
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * radius)
        val hs = factorY(2 * radius)
        if (ws <= 1 && hs <= 1)
            pixel(x, y)
        else
            graphics.fill(Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs))
    }


    fun ellipse(x: Double, y: Double, semiMajorAxis: Double, semiMinorAxis: Double) {
        if (semiMajorAxis < 0)
            throw IllegalArgumentException("ellipse semimajor axis must be nonnegative")
        if (semiMinorAxis < 0)
            throw IllegalArgumentException("ellipse semiminor axis must be nonnegative")
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * semiMajorAxis)
        val hs = factorY(2 * semiMinorAxis)
        if (ws <= 1 && hs <= 1)
            pixel(x, y)
        else
            graphics.draw(Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs))
    }

    fun filledEllipse(x: Double, y: Double, semiMajorAxis: Double, semiMinorAxis: Double) {
        if (semiMajorAxis < 0)
            throw IllegalArgumentException("ellipse semimajor axis must be nonnegative")
        if (semiMinorAxis < 0)
            throw IllegalArgumentException("ellipse semiminor axis must be nonnegative")
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * semiMajorAxis)
        val hs = factorY(2 * semiMinorAxis)
        if (ws <= 1 && hs <= 1)
            pixel(x, y)
        else
            graphics.fill(Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs))
    }


    fun arc(x: Double, y: Double, radius: Double, angle1: Double, angle2: Double) {
        var angle2 = angle2
        if (radius < 0) throw IllegalArgumentException("arc radius must be nonnegative")
        while (angle2 < angle1) angle2 += 360.0
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * radius)
        val hs = factorY(2 * radius)
        if (ws <= 1 && hs <= 1)
            pixel(x, y)
        else
            graphics.draw(Arc2D.Double(xs - ws / 2, ys - hs / 2, ws, hs, angle1,
                    angle2 - angle1, Arc2D.OPEN))
    }

    fun square(x: Double, y: Double, halfLength: Double) {
        if (halfLength < 0) throw IllegalArgumentException("half length must be nonnegative")
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * halfLength)
        val hs = factorY(2 * halfLength)
        if (ws <= 1 && hs <= 1)
            pixel(x, y)
        else
            graphics.draw(Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs))
    }

    fun filledSquare(x: Double, y: Double, halfLength: Double) {
        if (halfLength < 0) throw IllegalArgumentException("half length must be nonnegative")
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * halfLength)
        val hs = factorY(2 * halfLength)
        if (ws <= 1 && hs <= 1)
            pixel(x, y)
        else
            graphics.fill(Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs))
    }

    fun rectangle(x: Double, y: Double, halfWidth: Double, halfHeight: Double) {
        if (halfWidth < 0) throw IllegalArgumentException("half width must be nonnegative")
        if (halfHeight < 0) throw IllegalArgumentException("half height must be nonnegative")
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * halfWidth)
        val hs = factorY(2 * halfHeight)
        if (ws <= 1 && hs <= 1)
            pixel(x, y)
        else
            graphics.draw(Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs))
    }

    fun filledRectangle(x: Double, y: Double, halfWidth: Double, halfHeight: Double) {
        if (halfWidth < 0) throw IllegalArgumentException("half width must be nonnegative")
        if (halfHeight < 0) throw IllegalArgumentException("half height must be nonnegative")
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * halfWidth)
        val hs = factorY(2 * halfHeight)
        if (ws <= 1 && hs <= 1)
            pixel(x, y)
        else
            graphics.fill(Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs))
    }

    fun polygon(x: DoubleArray?, y: DoubleArray?) {
        if (x == null) throw IllegalArgumentException("x-coordinate array is null")
        if (y == null) throw IllegalArgumentException("y-coordinate array is null")
        val n1 = x.size
        val n2 = y.size
        if (n1 != n2) throw IllegalArgumentException("arrays must be of the same length")
        if (n1 == 0) return

        val path = GeneralPath()
        path.moveTo(scaleX(x[0]).toFloat(), scaleY(y[0]).toFloat())
        for (i in 0 until n1)
            path.lineTo(scaleX(x[i]).toFloat(), scaleY(y[i]).toFloat())
        path.closePath()
        graphics.draw(path)
    }

    fun filledPolygon(x: DoubleArray?, y: DoubleArray?) {
        if (x == null) throw IllegalArgumentException("x-coordinate array is null")
        if (y == null) throw IllegalArgumentException("y-coordinate array is null")
        val n1 = x.size
        val n2 = y.size
        if (n1 != n2) throw IllegalArgumentException("arrays must be of the same length")
        if (n1 == 0) return

        val path = GeneralPath()
        path.moveTo(scaleX(x[0]).toFloat(), scaleY(y[0]).toFloat())
        for (i in 0 until n1)
            path.lineTo(scaleX(x[i]).toFloat(), scaleY(y[i]).toFloat())
        path.closePath()
        graphics.fill(path)
    }

    private fun getImage(filename: String): Image {
        var icon: ImageIcon? = ImageIcon(filename)

        if (icon == null || icon.imageLoadStatus != MediaTracker.COMPLETE) {
            try {
                val url = URL(filename)
                icon = ImageIcon(url)
            } catch (e: MalformedURLException) {
            }
        }

        if (icon == null || icon.imageLoadStatus != MediaTracker.COMPLETE) {
            val url = Canvas::class.java.getResource(filename)
            if (url != null)
                icon = ImageIcon(url)
        }

        if (icon == null || icon.imageLoadStatus != MediaTracker.COMPLETE) {
            val url = Canvas::class.java.getResource("/$filename")
                    ?: throw IllegalArgumentException("image $filename not found")
            icon = ImageIcon(url)
        }

        return icon.image
    }

    fun picture(x: Double, y: Double, filename: String) = picture(x, y, getImage(filename))

    fun picture(x: Double, y: Double, filename: String, degrees: Double) =
            picture(x, y, getImage(filename), degrees = degrees)

    fun picture(x: Double, y: Double,
                filename: String,
                scaledWidth: Double,
                scaledHeight: Double,
                degrees: Double = 0.0) =
            picture(x, y, getImage(filename), scaledWidth, scaledHeight, degrees)

    fun picture(x: Double, y: Double,
                image: Image,
                scaledWidth: Double = image.getWidth(null).toDouble(),
                scaledHeight: Double = image.getHeight(null).toDouble(),
                degrees: Double = 0.0) {
        if (scaledWidth < 0) throw IllegalArgumentException("width is negative: $scaledWidth")
        if (scaledHeight < 0) throw IllegalArgumentException("height is negative: $scaledHeight")
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(scaledWidth)
        val hs = factorY(scaledHeight)
        if (ws <= 1 && hs <= 1) pixel(x, y)

        graphics.rotate(Math.toRadians(-degrees), xs, ys)
        graphics.drawImage(image,
                (xs - ws / 2.0).roundToInt(),
                (ys - hs / 2.0).roundToInt(),
                ws.roundToInt(),
                hs.roundToInt(), null)
        graphics.rotate(Math.toRadians(+degrees), xs, ys)

    }

    fun text(x: Double, y: Double, text: String, degrees: Double = 0.0) {
        val xs = scaleX(x)
        val ys = scaleY(y)
        graphics.rotate(Math.toRadians(-degrees), xs, ys)
        val metrics = graphics.fontMetrics
        val ws = metrics.stringWidth(text)
        val hs = metrics.descent
        graphics.drawString(text, (xs - ws / 2.0).toFloat(), (ys + hs).toFloat())
        graphics.rotate(Math.toRadians(+degrees), xs, ys)
    }

    fun textLeft(x: Double, y: Double, text: String?) {
        if (text == null) throw IllegalArgumentException()
        graphics.font = font
        val metrics = graphics.fontMetrics
        val xs = scaleX(x)
        val ys = scaleY(y)
        val hs = metrics.descent
        graphics.drawString(text, xs.toFloat(), (ys + hs).toFloat())
    }

    fun textRight(x: Double, y: Double, text: String?) {
        if (text == null) throw IllegalArgumentException()
        graphics.font = font
        val metrics = graphics.fontMetrics
        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = metrics.stringWidth(text)
        val hs = metrics.descent
        graphics.drawString(text, (xs - ws).toFloat(), (ys + hs).toFloat())
    }

}