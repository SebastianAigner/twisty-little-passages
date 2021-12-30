class RecursiveBacktracker : Algorithm {
    override fun on(grid: Grid) {
        on(grid, startAt = grid.randomCell())
    }

    fun on(grid: Grid, startAt: Cell) {
        val stack = ArrayDeque<Cell>(1)
        stack.addLast(startAt)
        while (stack.isNotEmpty()) {
            val current = stack.last()
            val neighbors = current.neighbors().filter { it.links().isEmpty() }
            if (neighbors.isEmpty()) {
                stack.removeLast()
            } else {
                val neighbor = neighbors.random()
                current.link(neighbor)
                stack.addLast(neighbor)
            }
        }
    }
}

fun main() {
    val grid = ColoredGrid(20, 20)
    BinaryTree().on(grid)
    val distances = grid[grid.rows / 2, grid.columns / 2]!!.distances()
    grid.distances = distances
    grid.toImage().savePng("RecursiveBacktracker.png")
}