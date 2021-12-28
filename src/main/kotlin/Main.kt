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

    fun linked(other: Cell?): Boolean {
        if (other == null) return false
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