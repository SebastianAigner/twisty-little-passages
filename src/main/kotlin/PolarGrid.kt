import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class PolarGrid(rows: Int, columns: Int) : Grid(rows, columns) {
    override fun toImage(cellSize: Int): BufferedImage {
        val imgSize = 2 * rows * cellSize
        val background = Color.WHITE
        val wall = Color.BLACK
        val img = BufferedImage(imgSize + 1, imgSize + 1, BufferedImage.TYPE_INT_RGB)
        val center = imgSize / 2
        val g2d = img.createGraphics()
        g2d.color = background
        g2d.fillRect(0, 0, img.width, img.height)
        g2d.color = wall
        for (cell in cells()) {
            val theta = 2 * PI / grid[cell.row].size
            val innerRadius = cell.row * cellSize
            val outerRadius = (cell.row + 1) * cellSize
            val thetaCCW = cell.column * theta
            val thetaCW = (cell.column + 1) * theta

            val ax = center + (innerRadius * cos(thetaCCW)).toInt()
            val ay = center + (innerRadius * sin(thetaCCW)).toInt()
            val bx = center + (outerRadius * cos(thetaCCW)).toInt()
            val by = center + (outerRadius * sin(thetaCCW)).toInt()
            val cx = center + (innerRadius * cos(thetaCW)).toInt()
            val cy = center + (innerRadius * sin(thetaCW)).toInt()
            val dx = center + (outerRadius * cos(thetaCW)).toInt()
            val dy = center + (outerRadius * sin(thetaCW)).toInt()
            if (!cell.linked(cell.north)) {
//                g2d.drawLine(ax, ay, cx, cy)
                println("($ax|$ay) ($cx|$cy)")
                println("$cx - ")
                val startX = imgSize / 2 - innerRadius
                val startY = imgSize / 2 - innerRadius
                val width = 2 * innerRadius
                val height = 2 * innerRadius
                val startAngle = (thetaCCW * 180 / PI).toInt()
                val arcAngle = ((thetaCW - thetaCCW) * 180 / PI).toInt()
                g2d.drawArc(startX, startY, width, height, startAngle, arcAngle)
            }
            if (!cell.linked(cell.east)) {
                g2d.drawLine(cx, cy, dx, dy)
            }
        }
        g2d.drawOval(0, 0, rows * cellSize * 2, columns * cellSize * 2)
        return img
    }
}

fun main() {
    val grid = PolarGrid(8, 8)
//    RecursiveBacktracker().on(grid)
    grid.toImage().savePng("PolarGrid.png")
}