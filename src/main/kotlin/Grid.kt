import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB

data class Grid(val rows: Int, val columns: Int) {
    val grid: MutableList<MutableList<Cell>> = MutableList(rows) { row ->
        MutableList(columns) { column ->
            Cell(row, column)
        }
    }

    init {
        for (cell in cells()) {
            val row = cell.row
            val col = cell.column
            cell.north = this[row - 1, col]
            cell.south = this[row + 1, col]
            cell.west = this[row, col - 1]
            cell.east = this[row, col + 1]
        }
    }

    operator fun get(row: Int, column: Int): Cell? {
        return grid.getOrNull(row)?.getOrNull(column)
    }

    fun randomCell(): Cell {
        return grid.random().random()
    }

    val size get() = rows * columns

    fun rows() = sequence<MutableList<Cell>> {
        for (row in grid) yield(row)
    }

    fun cells() = sequence<Cell> {
        for (row in rows()) {
            for (cell in row) {
                yield(cell)
            }
        }
    }

    override fun toString(): String {
        return buildString {
            appendLine("+" + "———+".repeat(columns))
            for (row in rows()) {
                var top = "|"
                var bottom = "+"
                for (cell in row) {
                    val body = "   "
                    val eastBound = cell.east?.let { if (cell.linked(it)) " " else null } ?: "|"
                    val southBound = cell.south?.let { if (cell.linked(it)) "   " else null } ?: "———"
                    top += body + eastBound
                    val corner = "+"
                    bottom += southBound + corner
                }
                appendLine(top)
                appendLine(bottom)
            }
        }
    }

    fun toImage(cellSize: Int): BufferedImage {
        val width = cellSize * columns
        val height = cellSize * rows
        val background = Color.WHITE
        val wall = Color.BLACK
        val img = BufferedImage(width + 1, height + 1, TYPE_INT_RGB)
        val g2d = img.createGraphics()
        g2d.color = background
        g2d.fillRect(0, 0, img.width, img.height)
        for (cell in cells()) {
            val x1 = cell.column * cellSize
            val y1 = cell.row * cellSize
            val x2 = (cell.column + 1) * cellSize
            val y2 = (cell.row + 1) * cellSize
            g2d.color = wall
            if (cell.north == null) {
                g2d.drawLine(x1, y1, x2, y1)
            }
            if (cell.west == null) {
                g2d.drawLine(x1, y1, x1, y2)
            }

            if (!cell.linked(cell.east)) {
                g2d.drawLine(x2, y1, x2, y2)
            }
            if (!cell.linked(cell.south)) {
                g2d.drawLine(x1, y2, x2, y2)
            }

        }
        return img
    }
}