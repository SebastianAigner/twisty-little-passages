class Mask(val rows: Int, val columns: Int) {
    val bits = MutableList(rows) { MutableList(columns) { true } }
    operator fun get(row: Int, column: Int): Boolean {
        return bits.getOrNull(row)?.getOrNull(column) ?: false
    }

    operator fun set(row: Int, column: Int, isOn: Boolean) {
        bits[row][column] = isOn
    }

    fun count(): Int {
        return bits.flatten().count { it }
    }

    fun randomLocation(): Position {
        do {
            val row = bits.indices.random()
            val column = bits[0].indices.random()
            if (this[row, column]) return Position(row, column)
        } while (true)
    }
}

data class Position(val row: Int, val column: Int)