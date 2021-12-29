import java.awt.Color
import kotlin.math.roundToInt

class ColoredGrid(rows: Int, columns: Int) : Grid(rows, columns) {
    var maximum = 0
    var distances: Distances? = null
        set(value) {
            field = value
            if (value != null) {
                val (farthest, maximum) = value.max()
                this.maximum = maximum
            }
        }

    override fun backgroundColorFor(cell: Cell): Color? {
        val distance = distances?.get(cell) ?: return null
        val intensity = (maximum - distance).toDouble() / maximum
        val dark = (255 * intensity).roundToInt()
        val bright = 128 + (127 * intensity).roundToInt()
        return Color(dark, bright, dark)
    }
}