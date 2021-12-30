fun main() {
    val grid = Grid(5, 5)
    grid[0, 0]?.east?.west = null
    grid[0, 0]?.south?.north = null
    grid[4, 4]?.west?.east = null
    grid[4, 4]?.north?.south = null
    RecursiveBacktracker().on(grid, startAt = grid[1, 1]!!)
    grid.toImage().savePng("killed.png")
}