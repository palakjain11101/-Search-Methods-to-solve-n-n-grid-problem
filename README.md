# Search-Methods-to-solve-n-n-grid-problem
An ‘agent’ moves in a simulated NxN grid world with the goal of building towers of blocks. Each grid space
contains either a ‘tile’ or the agent. Some tiles have letters on them – these are the ‘blocks’. All the other tiles
are white. The agent moves up/down/left/right (except where borders prevent it). As the agent moves, the tile
that they move onto slides under them into the position that they just came from.
The goal is to build a tower, with specific starting and goal states. The position of the
agent at the end doesn’t matter.

- The course-work implements the following types of search to solve this problem: depth first, breadth first, iterative
deepening, A* heuristic search. 
- Randomising the ordering of node expansion in depth first is considered.
- Evidence of these methods running/the solutions (action sequences) provided in the report
- Exploration: How the computational time (number of nodes expanded) to reach a solution increases/scales up with problem size/difficulty 
- Problem difficulty is controlled by fixing the depth of the solution(i.e. how far the start state is from the goal state)
- Exploration: Figures with problem difficulty on the x-axis and number of nodes expanded on y-axis compared for all search methods
- Extensions:
  (1) Implementing Graph Search solutions for algorithms to get better time complexity (Initial implementation: Tree Search)
  (2) Solving the problem if the world has obstacles (i.e., tiles that cant be moved)


