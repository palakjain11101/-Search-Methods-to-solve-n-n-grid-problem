
public class Program {

    //Methods in Search.java care called through this class.
    public static void main(String[] args){
        int[] puzzle =
                {
                        0, 0, 0, 0,
                        0, 0, 0, 0,
                        0, 0, 0, 0,
                        1, 2, 3, 4
                };



        Node root = new Node(puzzle);
        Search search = new Search();
        search.BreadthFirstSearch(root);
        //search.DepthFirstSearch(root);
        //search.RandomDepthFirstSearch(root);
        //search.iterativeDeepening(root);
        //search.aStar(root);
        //search.GraphBreadthFirstSearch(root);
        //search.GraphDepthFirstSearch(root);




    }
}