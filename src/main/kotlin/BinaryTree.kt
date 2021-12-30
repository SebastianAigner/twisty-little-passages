class BinaryTree : Algorithm {
    override fun on(grid: Grid) {
        for (cell in grid.cells()) {
            val neighbours = listOfNotNull(cell.north, cell.east)
            if (neighbours.isNotEmpty()) {
                cell.link(neighbours.random())
            }
        }
    }
}

fun main() {
    val grid = Grid(10, 10)
    BinaryTree().on(grid)
    println(grid)
}