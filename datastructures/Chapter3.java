package datastructures;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayDeque;
import java.util.Deque;
import interview.BinaryTree;
import interview.TreeNode;
import interview.BTreePrinter;

public class Chapter3 {

  // 4.1
  public static Boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }

    int leftMax  = maxPath(root.left);
    int rightMax = maxPath(root.right);

    if(Math.abs(leftMax - rightMax) > 1) {
      return false;
    }

    return true;
  }

  private static int maxPath(TreeNode tree){
    if(tree == null) {
      return 0;
    } else if (tree.left == null && tree.right == null){
      return 1;
    }

    int leftSum  = 1 + maxPath(tree.left);
    int rightSum = 1 + maxPath(tree.right);
    int max      = Math.max(leftSum, rightSum);

    return max;
  }

  // 4.2
  // suto code
  // public Boolean route(Graph g, Node start, Node end) {
  //   LinkedList<Node> list = new LinkedList<Node>();
  //
  //   // mark everything as not visited
  //   for(Node n : g.getNodes()) {
  //     n.visted = State.UNVISTED;
  //   }
  //
  //   // add start to the list
  //   start.state = State.VISITING;
  //   list.add(start);
  //
  //   Node current;
  //   while(!list.isEmpty()) {
  //     // get the first node (on first iteration it will be start)
  //     current = list.removeFirst();
  //
  //     // go through all of its neightbours
  //     // mark as visiting
  //     for (Node v : current.getNeighbours()) {
  //       // if we reached the end node then were done
  //       if(v === end) {
  //         return true;
  //       } else { // mark node so we will vist it on future iterations
  //         v.state = State.VISITING;
  //         list.add(v);
  //       }
  //     }
  //
  //     // we have visted this node so we are dont looking at it
  //     current.state = State.VISITED;
  //   }
  //   return false;
  // }
  //
  // private enum State {
  //   VISTED, UNVISTED, VISITING
  // }

  // 4.3
  public static TreeNode createTree(int sortedArr[]) {
    int length = sortedArr.length - 1;
    return createTree(sortedArr, 0, length);
  }

  private static TreeNode createTree(int sortedArr[], int start, int end) {
    if(end < start){
      return null;
    }

    int midPoint = (start + end) / 2;
    TreeNode tree = new TreeNode(sortedArr[midPoint]);

    tree.left  = createTree(sortedArr, start, midPoint - 1);
    tree.right = createTree(sortedArr, midPoint + 1, end);

    return tree;
  }

  // 4.4
  public ArrayList<LinkedList<TreeNode>> createLists() {
    ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
    LinkedList<TreeNode> list = new LinkedList<TreeNode>();
    int level = 0;
    TreeNode current = this.root;

    list.add(current);
    result.add(level, list);

    while(true) {
      for(int i = 0; i < result.get(level).size(); i++) {
        current =  result.get(level).get(i);

        if(current != null) {
          if(current.left !=  null) {
            list.add(current.left);
          }
          if(current.right != null) {
            list.add(current.right);
          }
        }
      }

      if(list.size() > 0) {
        result.add(level + 1, list);
      } else {
        break;
      }
      level++;
    }

    return result;
  }

  public static void main(String args[]) {
    // BinaryTree tree = new BinaryTree();
    // tree.add(5);
    // tree.add(3);
    // tree.add(6);
    // tree.add(7);
    // tree.add(8);
    //
    // BTreePrinter.printNode(tree.root);
    // System.out.println(tree.maxPath(tree.root));
    // System.out.println(tree.isBalanced());
    // System.out.println("BFS Search");
    // tree.BFS();
    // System.out.println("DFS Search");
    // tree.DFS();
    // System.out.println("Pre Order Transversal");
    // tree.preOrder();

    // System.out.println("RUNNING");
    // BinarySearchTree bst = new BinarySearchTree();
    // bst.add(1);
    // bst.add(5);
    // bst.add(4);
    // bst.add(-1);
    // bst.add(6);
    // bst.add(6);
    // bst.add(2);
    // bst.add(-3);
    // bst.add(0);

    // TreeNode bst;
    // int arr[] = new int[6];
    // arr[0] = 1;
    // arr[1] = 2;
    // arr[2] = 4;
    // arr[3] = 5;
    // arr[4] = 7;
    // arr[5] = 9;
    //
    // bst = createTree(arr);
    // BTreePrinter.printNode(bst);
  }
}
