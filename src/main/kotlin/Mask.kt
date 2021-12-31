import java.awt.image.BufferedImage


class Mask(val rows: Int, val columns: Int) {
    companion object {
        fun fromText(lines: List<String>): Mask {
            val m = Mask(lines.size, lines[0].length)
            for ((row, line) in lines.withIndex()) {
                for ((column, char) in line.withIndex()) {
                    // TODO: this is equivalent to the code in the book, but since `bits` gets init'd to `true`, can be simplified?
                    m[row, column] = char != 'X'
                }
            }
            return m
        }

        fun fromImage(img: BufferedImage): Mask {
            val m = Mask(img.height, img.width)
            for (row in 0 until img.height) {
                for (column in 0 until img.width) {
                    val (r, g, b) = img.getRGB(column, row).colorComponents
                    println("$r $g $b")
                    m[row, column] = (r + g + b) != 0
                }
            }
            return m
        }
    }

    fun invert() {
        for ((rowIdx, row) in bits.withIndex()) {
            for (colIdx in row.indices) {
                this[rowIdx, colIdx] = !this[rowIdx, colIdx]
            }
        }
    }

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

data class ColorComponents(val red: Int, val green: Int, val blue: Int)

val Int.colorComponents: ColorComponents
    get() {
        val blue: Int = this and 0xff
        val green: Int = this and 0xff00 shr 8
        val red: Int = this and 0xff0000 shr 16
        return ColorComponents(red, green, blue)
    }