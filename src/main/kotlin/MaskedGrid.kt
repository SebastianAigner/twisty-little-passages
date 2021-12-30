class MaskedGrid(val mask: Mask) : Grid(mask.rows, mask.columns) {
    init {
        grid = prepGrid()
        configureCells()
    }

    fun prepGrid(): MutableList<MutableList<Cell?>> {
        return MutableList(rows) { row ->
            (0 until columns).map { column -> if (mask[row, column]) Cell(row, column) else null }.toMutableList()
        }
    }

    override fun randomCell(): Cell {
        val (row, col) = mask.randomLocation()
        println("$row $col")
        return this[row, col]!!
    }

    override val size: Int
        get() = mask.count()
}