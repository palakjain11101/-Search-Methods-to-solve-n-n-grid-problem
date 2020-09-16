import java.util.*;

public class Node {

    List<Node> children = new ArrayList<>();
    public Node parent;
    //position of the agent
    public int x = 4;
    //the value of n in n*n matrix can be set here
    public int col = 4;
    //size of new array is dependant on column/row size
    int size = col*col;
    public int[] puzzle = new int[size];
    private int totalCost;
    private int depth;
    int[] goalPuzzle =
            {
                    0, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 2, 0, 0,
                    0, 3, 0, 4
            };

    int[] list =
            {1,2,3};


    public Node(int[] p){
        setPuzzle(p);
    }

    public int getDepth() {
        return depth;
    }

    public Node getParent() {
        return parent;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }


    public int getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }



    //Copy the initial (original) puzzle to this copy
    public void setPuzzle(int[] p){
        for(int i = 0; i < puzzle.length; i++)
            this.puzzle[i] = p[i];
    }


    public void expandMove(){
        for(int i = 0; i < puzzle.length; i++){
            //Apply the legal action with 0
            if(puzzle[i] == 4) {
                x = i;
            }
        }
        moveRight(puzzle, x);
        moveLeft(puzzle, x);
        moveUp(puzzle, x);
        moveDown(puzzle, x);
    }


    public int heuristicOne(){
        int difference = 0;
        for(int i = 1; i < goalPuzzle.length; i++){
            if(puzzle[i] != goalPuzzle[i]){
                difference += 1;
            }
        }
        return difference;
    }

    public int heuristicTwo() {
        int difference = 0;
        for(int i = 0; i < list.length; i++){
            for (int j = 0; j < puzzle.length; j += 1){
                if(puzzle[j] == list[i]){
                    int k = findIndex(puzzle[j]);
                    difference = difference + ((Math.abs(j % 4 - k % 4)) + Math.abs(j / 4 - k / 4));
                }
            }


        }
        return difference;
    }


    public int findIndex(int t)
    {
        int len = this.goalPuzzle.length;
        int i = 0;

        // traverse in the array
        while (i < len) {
            if (goalPuzzle[i] == t) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

    public boolean GoalTest(){
        boolean isGoal = true;
        for(int i = 0; i < list.length; i++) {
            for (int j = 0; j < puzzle.length; j += 1) {
                if (puzzle[j] == list[i]) {
                    int k = findIndex(puzzle[j]);
                    if (k != j) {
                        isGoal = false;
                    }
                }
            }
        }
        return isGoal;
    }

    //You can expand a node by moving right, left, down or up
    public void moveRight(int[] p, int i){

        //this constraint makes sure the following operation is not applied to the last(third) column
        if(i % col < col - 1){
            //the if constraint commented out below takes into account an obstacle numbered 8
            //if(p[i+1] != 8){
                int[] pc = new int[size];
                //a new copy of the puzzle is created every time a move method is called
                copyPuzzle(pc, p);
                int temp = pc[i+1];
                pc[i+1] = pc[i];
                pc[i] = temp;

                Node child = new Node(pc);
                children.add(child);
                child.parent = this;

        }
    }

    public void moveLeft(int[] p, int i){
        if(i % col > 0) {
            //if(p[i-1] != 8) {
                int[] pc = new int[size];
                copyPuzzle(pc, p);
                int temp = pc[i - 1];
                pc[i - 1] = pc[i];
                pc[i] = temp;

                Node child = new Node(pc);
                children.add(child);
                child.parent = this;
            //}

        }
    }

    public void moveUp(int[] p, int i){
        if(i - col > 0){
            //if(p[i-col] != 8) {
                int[] pc = new int[size];
                copyPuzzle(pc, p);
                int temp = pc[i - col];
                pc[i - col] = pc[i];
                pc[i] = temp;

                Node child = new Node(pc);
                children.add(child);
                child.parent = this;
            //}
        }

    }

    public void moveDown(int[] p, int i){
        if(i + col < puzzle.length){
            //if(p[i+col] != 8) {
                int[] pc = new int[size];
                copyPuzzle(pc, p);
                int temp = pc[i + col];
                pc[i + col] = pc[i];
                pc[i] = temp;

                Node child = new Node(pc);
                children.add(child);
                child.parent = this;
            //}

        }
    }

    public void printPuzzle(){
        //System.out.println(puzzle[0]);
        int m = 0;
        for(int i = 0; i < col; i++){
            for(int j = 0; j < col; j++){
                System.out.print(puzzle[m] + "*");
                m++;
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    //This method is used when things are moved
    public void copyPuzzle(int[] a, int[] b){
        for(int i = 0; i < b.length; i++){
            a[i] = b[i];
        }
    }

    public boolean isSamePuzzle(int[] p) {
        boolean samePuzzle = true;
        for (int i = 0; i < p.length; i++) {
            if (this.puzzle[i] != p[i]) {
                samePuzzle = false;
            }
        }

        return samePuzzle;

    }

}

