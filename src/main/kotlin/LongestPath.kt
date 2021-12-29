fun main() {
    val grid = DistanceGrid(5, 5)
    BinaryTree().on(grid)
    val start = grid[0, 0]!!
    val distances = start.distances()
    val (newStart, _) = distances.max()

    val newDistances = newStart.distances()
    val (goal, _) = newDistances.max()

    grid.distances = newDistances.pathTo(goal)
    println(grid)
}