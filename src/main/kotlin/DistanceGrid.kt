class DistanceGrid(rows: Int, columns: Int, var distances: Distances? = null) : Grid(rows, columns) {
    override fun contentsOf(cell: Cell): String {
        return distances?.get(cell)?.toString(36) ?: super.contentsOf(cell)
    }
}