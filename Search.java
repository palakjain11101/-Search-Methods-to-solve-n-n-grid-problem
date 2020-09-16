import java.util.List;
import java.util.*;
import java.util.Stack;
import java.util.PriorityQueue;

public class Search {

    public Search() {

    }

    public void BreadthFirstSearch(Node root) {
        System.out.println("Breadth First Search");
        List<Node> states = new ArrayList<>();
        int time = 0;
        Boolean goalFound = false;
        Queue<Node> queue = new LinkedList<>();
        root.setDepth(0);
        Node currentNode = root;
        queue.add(currentNode);
        while ((goalFound==false) && !queue.isEmpty()) {
            currentNode = queue.poll();
            System.out.println("Current node depth: "+ currentNode.getDepth());
            currentNode.printPuzzle();

            if (currentNode.GoalTest()) {
                System.out.println("Goal found");
                goalFound = true;
                printSolution(currentNode);
            }
            else{
                currentNode.expandMove();
                states.add(currentNode);
                time = currentNode.children.size() + time;
                for (Node currentChild : currentNode.children) {
                    currentChild.setDepth(currentNode.getDepth()+1);
                    ((LinkedList<Node>) queue).add(currentChild);
                }
            }

        }

        System.out.println("Number of nodes generated: " + time);

        /*Number of generated nodes can be confirmed by counting
        the number of elements remaining in queue and in ArrayList states */
        int count = 0;
        while(!queue.isEmpty()){
            queue.poll();
            count = count + 1;
        }
        int size = states.size()+count;
        System.out.println("Check: " + size);

    }

    public void DepthFirstSearch(Node root) {
        System.out.println("Depth First Search");
        List<Node> states = new ArrayList<>();
        int time = 0;
        Boolean goalFound = false;
        Stack<Node> stack = new Stack<>();
        root.setDepth(0);
        Node currentNode = root;
        stack.push(currentNode);

        while (!goalFound && !stack.isEmpty()) {
            currentNode = stack.pop();
            currentNode.printPuzzle();
            System.out.println("Current node depth: "+ currentNode.getDepth());
            if (currentNode.GoalTest()) {
                System.out.println("Goal found");
                goalFound = true;
                printSolution(currentNode);
            }
            else{
                currentNode.expandMove();
                states.add(currentNode);
                time = currentNode.children.size() + time;
                for (Node currentChild : currentNode.children) {
                    currentChild.setDepth(currentNode.getDepth()+1);
                    stack.push(currentChild);
                }
            }

        }
        System.out.println("Number of nodes generated: " + time);

        /*Number of generated nodes can be confirmed by counting
        the number of elements remaining in queue and in ArrayList states */
        int count = 0;
        while(!stack.isEmpty()){
            stack.pop();
            count = count + 1;
        }
        int size = states.size()+count;
        System.out.println("Check: " + size);
    }


    public int RandomDepthFirstSearch(Node root) {
        System.out.println("Random Depth First Search");
        int time = 0;
        Boolean goalFound = false;
        Stack<Node> stack = new Stack<>();
        root.setDepth(0);
        Node currentNode = root;
        stack.push(currentNode);

        while (!goalFound && !stack.isEmpty()) {
            currentNode = stack.pop();
            System.out.println("Current node depth:" + currentNode.getDepth());
            currentNode.printPuzzle();

            if (currentNode.GoalTest()) {
                System.out.println("Goal found");
                goalFound = true;
                printSolution(currentNode);
            }
            else{
                currentNode.expandMove();
                time = currentNode.children.size() + time;
                Collections.shuffle(currentNode.children);
                for (Node currentChild : currentNode.children) {
                    currentChild.setDepth(currentNode.getDepth()+1);
                    stack.push(currentChild);
                }
            }

        }

        System.out.println("Number of nodes generated: " + time);

        return time;
    }

    /* Method to call RandomDepthFirst search 50 times
    and take the average of number of generated nodes */
    public void DepthFirstSearchNTimes(Node root){
        int i = 0;
        int totalTime = 0;
        while (i < 50){
            totalTime   = totalTime + RandomDepthFirstSearch(root);
            i++;
        }
        System.out.println("The average number of nodes generated: " + (totalTime/50));
    }


    public void iterativeDeepening(Node root) {
        System.out.println("Iterative Deepening Search");
        List<Node> states = new ArrayList<>();
        int time = 0;
        int limit = 0;
        Boolean goalFound = false;
        Stack<Node> stack = new Stack<>();
        root.setDepth(0);
        Node currentNode = root;
        stack.push(currentNode);
        int count = 0;

        while (!goalFound) {
            if (stack.empty() == false) {
                currentNode = stack.pop();
                System.out.println("Current node depth:" + currentNode.getDepth());
                currentNode.printPuzzle();
                if (currentNode.GoalTest()) {
                    System.out.println("Goal found");
                    printSolution(currentNode);
                    goalFound = true;
                } else if (currentNode.getDepth() < limit) {
                    states.add(currentNode);
                    currentNode.expandMove();
                    time = currentNode.children.size() + time;
                    for (Node currentChild : currentNode.children) {
                        currentChild.setDepth(currentNode.getDepth()+1);
                        stack.push(currentChild);
                    }
                    currentNode.children.clear();
                }
                if(currentNode != root){
                    count++;
                }
            } else  {
                limit = limit + 1;
                System.out.println("Current limit is:" + limit);
                stack.push(root);
            }
        }

        System.out.println("Number of nodes generated: " + time);
    }

    public void aStar(Node root) {
        System.out.println("A* Search");
        boolean goalFound = false;
        List<Node> states = new ArrayList<>();
        int time = 0;
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue(10, nodePriorityComparator);
        root.setDepth(0);
        root.parent = null;
        root.setTotalCost(root.heuristicOne());
        Node currentNode = root;
        nodePriorityQueue.add(currentNode);

        while (!goalFound && !nodePriorityQueue.isEmpty()) {
            currentNode = nodePriorityQueue.poll();
            System.out.println("Node picked:" );
            System.out.println("Current node depth:" + currentNode.getDepth());
            System.out.println("Cost of Node:" + currentNode.getTotalCost() );
            currentNode.printPuzzle();
            if(currentNode.GoalTest()){
                System.out.println("Goal found");
                printSolution(currentNode);
                goalFound = true;
            }
            else{
                currentNode.expandMove();
                states.add(currentNode);
                time = currentNode.children.size() + time;
                for(Node currentChild : currentNode.children){
                    currentChild.setDepth(currentNode.getDepth()+1);
                    //

                    int h = currentChild.heuristicOne();
                    currentChild.setTotalCost(currentChild.getDepth() + h);
                    System.out.println("-----");
                    System.out.println("Current child:" );
                    currentChild.printPuzzle();
                    System.out.println("Cost of current child:" + currentChild.getTotalCost());
                    System.out.println("-----");
                    nodePriorityQueue.add(currentChild);
                }
            }

        }
        System.out.println("Number of nodes generated: " + time);
    }

    //method to print path to solution
    public static void printSolution(Node goalNode) {
        Stack<Node> solutionStack = new Stack<>();

        solutionStack.push(goalNode);
        Node current = goalNode;
        while (current.parent != null) {
            solutionStack.push(current.getParent());
            current = current.getParent();
        }
        System.out.println("Path to solution : ");
        solutionStack.push(current);
        int i = 0;
        while (solutionStack.isEmpty()==false){
            Node thisNode = solutionStack.pop();
            System.out.println("Depth of this node:" + thisNode.getDepth());
            thisNode.printPuzzle();
            i++;

        }

        System.out.println("Number of steps to solution: " + (i-2));

    }

    //Extensions
    public void GraphBreadthFirstSearch(Node root) {
        //List of nodes that need to be expanded
        List<Node> nodesToExpandList = new ArrayList<>();
        int time = 0;
        //List of nodes that have already been expanded
        List<Node> expandedNodesList = new ArrayList<>();
        root.setDepth(0);
        nodesToExpandList.add(root);
        boolean goalFound = false;

        while (nodesToExpandList.size() > 0 && !goalFound) {
            Node currentNode = nodesToExpandList.get(0);
            currentNode.printPuzzle();
            expandedNodesList.add(currentNode);
            nodesToExpandList.remove(0);
            if (currentNode.GoalTest()) {
                System.out.println("Goal found");
                goalFound = true;
                printSolution(currentNode);
            }
            else{
                currentNode.expandMove();
                //currentNode.printPuzzle();
                for (int i = 0; i < currentNode.children.size(); i++) {
                    Node currentChild = currentNode.children.get(i);
                    currentChild.setDepth(currentNode.getDepth()+1);
                    //if current child configuration is not in either list, add child to the nodesToExpandList
                    //this ensures that a node configuration that has been visited is not visited again
                    if (!Contains(nodesToExpandList, currentChild) && !Contains(expandedNodesList, currentChild)) {
                        time = time + 1;
                        nodesToExpandList.add(currentChild);
                    }
                }
            }
        }
        System.out.println("Number of nodes generated:" + time);
    }

    public void GraphDepthFirstSearch(Node root) {
        List<Node> nodesToExpandList = new ArrayList<>();
        int time = 0;
        //Additional list to keep track of all the children nodes
        List<Node> nodesToExpandList1 = new ArrayList<>();
        List<Node> expandedNodesList = new ArrayList<>();
        root.setDepth(0);
        nodesToExpandList.add(root);
        boolean goalFound = false;

        while (nodesToExpandList.size() > 0 && !goalFound) {
            Node currentNode = nodesToExpandList.get(0);
            currentNode.printPuzzle();
            expandedNodesList.add(currentNode);
            nodesToExpandList.remove(0);
            currentNode.expandMove();
            if (currentNode.GoalTest()) {
                System.out.println("Goal found");
                goalFound = true;
                printSolution(currentNode);
            }
            else{
                currentNode.expandMove();
                for (int i = 0; i < currentNode.children.size(); i++) {
                    Node currentChild = currentNode.children.get(i);
                    currentChild.setDepth(currentNode.getDepth()+1);
                    if (!Contains(nodesToExpandList, currentChild) && !Contains(expandedNodesList, currentChild)) {
                        time = time + 1;
                        nodesToExpandList1.add(currentChild);
                    }
                }
                nodesToExpandList.addAll(0,nodesToExpandList1);
                nodesToExpandList1.clear();

            }



        }

        System.out.println("Number of nodes generated:" + time);

    }

    /* method to check whether the current child configuration
    has been visited already in Graph search */
    public static boolean Contains(List<Node> list, Node c){
        boolean contains = false;
        for(int i = 0; i < list.size();i++){
            if(list.get(i).isSamePuzzle(c.puzzle))
                contains = true;
        }
        return contains;
    }



}