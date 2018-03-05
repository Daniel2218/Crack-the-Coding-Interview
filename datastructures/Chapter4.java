package datastructures;

import datastructures.*;
import utils.BTreePrinter;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;

public class Chapter4 {

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

  /**
    * 4.4
    * Given a binary search tree, design an algorithm which creates a linked list of all the
    * nodes at each depth (i.e., if you have a tree with depth D, you’ll have D linked lists).
  **/
  public static ArrayList<LinkedList<TreeNode>> createLists(TreeNode root) {
    ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    LinkedList<TreeNode> list;

    q.add(root);

    while(!q.isEmpty()) {
      ArrayList<TreeNode> nodeList = removeAll(q);

      list = new LinkedList<TreeNode>();

      for(TreeNode node : nodeList) {
        list.add(node);

        if(node.left != null) q.add(node.left);
        if(node.right != null) q.add(node.right);
      }
      result.add(list);
    }

    return result;
  }

  private static ArrayList<TreeNode> removeAll(Queue<TreeNode> q) {
    ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();

    while(!q.isEmpty()) {
      nodeList.add(q.remove());
    }

    return nodeList;
  }

  private static void printListOfLinkedLists(ArrayList<LinkedList<TreeNode>> listOfNodes) {
    for(LinkedList<TreeNode> list : listOfNodes) {
      for(TreeNode node : list) {
        System.out.print(node.data + " => ");
      }
      System.out.println("null");
    }
  }

  public static void main(String args[]) {
    BinaryTree tree = new BinaryTree();
    tree.add(1);
    tree.add(2);
    tree.add(3);
    tree.add(4);
    tree.add(5);

    BinarySearchTree bst = new BinarySearchTree();
    bst.add(1);
    bst.add(5);
    bst.add(4);
    bst.add(-1);
    bst.add(6);
    bst.add(6);
    bst.add(2);
    bst.add(-3);
    bst.add(0);
    BTreePrinter.printNode(bst.root);

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

    System.out.println("Running 4.1: ");
    System.out.println(isBalanced(tree.root));

    System.out.println("Running 4.3: ");
    int[] sortedArr = new int[] { 1, 2, 3, 4, 5 };
    TreeNode node = createTree(sortedArr);
    BTreePrinter.printNode(bst.root);

    System.out.println("Running 4.4: ");
    ArrayList<LinkedList<TreeNode>> listOfNodes = createLists(bst.root);
    printListOfLinkedLists(listOfNodes);

  }
}
