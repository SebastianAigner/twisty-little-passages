fun main() {
    val grid = DistanceGrid(10, 10)
    BinaryTree().on(grid)
    val start = grid[0, 0]!!
    val distances = start.distances()
    grid.distances = distances
    println(grid)

    println("Path from northwest to southwest")
    grid.distances = distances.pathTo(grid[grid.rows - 1, 0]!!)
    println(grid)
}