data class Grid(val rows: Int, val columns: Int) {
    val grid: MutableList<MutableList<Cell>> = MutableList(rows) { row ->
        MutableList(columns) { column ->
            Cell(row, column)
        }
    }

    init {
        for(cell in cells()) {
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
        for(row in grid) yield(row)
    }

    fun cells() = sequence<Cell> {
        for(row in rows()) {
            for(cell in row) {
                yield(cell)
            }
        }
    }

    override fun toString(): String {
        return buildString {
            appendLine("+" + "———+".repeat(columns))
            for(row in rows()) {
                var top = "|"
                var bottom = "+"
                for(cell in row) {
                    val body = "   "
                    val eastBound = cell.east?.let { if(cell.linked(it)) " " else null } ?: "|"
                    val southBound = cell.south?.let { if(cell.linked(it)) "   " else null } ?: "———"
                    top += body + eastBound
                    val corner = "+"
                    bottom += southBound + corner
                }
                appendLine(top)
                appendLine(bottom)
            }
        }
    }

}

class Cell(
    val row: Int,
    val column: Int,
    var north: Cell? = null,
    var south: Cell? = null,
    var east: Cell? = null,
    var west: Cell? = null,
    val links: MutableMap<Cell, Boolean> = mutableMapOf(),
) {
    fun link(other: Cell, bidirectional: Boolean = true) {
        links[other] = true
        if (bidirectional) {
            other.link(this, false)
        }
    }

    fun unlink(other: Cell, bidirectional: Boolean = true) {
        links.remove(other)
        if (bidirectional) {
            other.unlink(this, false)
        }
    }

    fun links(): MutableSet<Cell> {
        return links.keys
    }

    fun linked(other: Cell): Boolean {
        return links.containsKey(other)
    }

    fun neighbors(): List<Cell> {
        return listOfNotNull(north, south, east, west)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cell

        if (row != other.row) return false
        if (column != other.column) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + column
        return result
    }
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}