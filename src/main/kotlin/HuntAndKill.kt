class HuntAndKill : Algorithm {
    override fun on(grid: Grid) {
        var current: Cell? = grid.randomCell()
        while (current != null) {
            val unvisitedNeighbors = current.neighbors().filter { it.links().isEmpty() }
            if (unvisitedNeighbors.isNotEmpty()) {
                // perform a regular random walk
                val neighbor = unvisitedNeighbors.random()
                current.link(neighbor)
                current = neighbor
            } else {
                current = null
                // the "hunt" phase
                for (cell in grid.cells()) {
                    val visitedNeighbors = cell.neighbors().filter { it.links().isNotEmpty() }
                    if (cell.links().isEmpty() && visitedNeighbors.isNotEmpty()) {
                        current = cell
                        val neighbor = visitedNeighbors.random()
                        current.link(neighbor)
                        break
                    }
                }
            }
        }
    }
}

fun main() {
    val grid = ColoredGrid(20, 20)
    HuntAndKill().on(grid)
    val center = grid[grid.rows / 2, grid.columns / 2]!!
    val distances = center.distances()
    grid.distances = distances
    grid.toImage().savePng("HuntAndKill.png")
}