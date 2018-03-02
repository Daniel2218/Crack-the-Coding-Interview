package datastructures;

import datastructures.*;
import utils.BTreePrinter;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;

public class Chapter3 {

  /**
    * 4.1
    * Implement a function to check if a tree is balanced. For the purposes of this question,
    * a balanced tree is defined to be a tree such that no two leaf nodes differ in distance
    * from the root by more than one.
  **/
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

  /**
    * 4.3
    * Given a sorted (increasing order) array, write an algorithm to create a binary tree with
    * minimal height.
  **/
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
  public ArrayList<LinkedList<TreeNode>> createLists(TreeNode root) {
    ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
    LinkedList<TreeNode> list = new LinkedList<TreeNode>();
    int level = 0;
    TreeNode current = root;

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
    BinaryTree tree = new BinaryTree();
    tree.add(1);
    tree.add(2);
    tree.add(3);
    tree.add(4);
    tree.add(5);

    System.out.println("\n Pre Order Transversal");
    tree.preOrder();
    System.out.println("");

    System.out.println("In-Order Transversal");
    tree.inOrder();
    System.out.println("");

    System.out.println("Post-Order Transversal");
    tree.postOrder();
    System.out.println("");

    System.out.println("\n BFS Search: ");
    tree.BFS();

    System.out.println("\n DFS Search: ");
    tree.DFS();

    BTreePrinter.printNode(tree.root);
    System.out.println("Running 1.1: ");
    System.out.println(isBalanced(tree.root));

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
