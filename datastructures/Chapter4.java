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

  /**
    * 4.5
    * Write an algorithm to find the ‘next’ node (eg  , in-order successor) of a given node in
    * a binary search tree where each node has a link to its parent
  **/
  public static TreeNode findSuccessor(TreeNode node) {
    if(node != null) {
      if(node.right != null) {
        return getLeftMostChildOf(node.right);
      } else {
        while(node != null) {
          TreeNode parrent = node.parrent;

          if(parrent.left == node) {
            return parrent;
          } else {
            node = parrent;
          }
        }
      }
    }

    return null;
  }

  // helper function: Get the left most node the node passed in
  private static TreeNode getLeftMostChildOf(TreeNode node) {
    while(node.left != null) {
      node = node.left;
    }

    return node;
  }

  private static void printListOfLinkedLists(ArrayList<LinkedList<TreeNode>> listOfNodes) {
    for(LinkedList<TreeNode> list : listOfNodes) {
      for(TreeNode node : list) {
        System.out.print(node.data + " => ");
      }
      System.out.println("null");
    }
  }

  /**
    * 4.6
    * Design an algorithm and write code to find the first common ancestor of two nodes
    * in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
    * necessarily a binary search tree
  */
  static int TWO_NODES_FOUND = 2;
  static int ONE_NODE_FOUND = 1;
  static int NO_NODES_FOUND = 0;

  public static TreeNode findAncestor(TreeNode root, TreeNode p, TreeNode q) {
     // q == p: only looking for one node
     if (q == p && (root.left == q || root.right == q)) {
       return root;
     }

     int nodesFromLeft = covers(root.left, p, q); // Check left side

     if (nodesFromLeft == TWO_NODES_FOUND) {
       if(root.left == p || root.left == q) {
         return root.left;
       } else {
         // there may be a closer ancestor
         return findAncestor(root.left, p, q);
       }
     } else if (nodesFromLeft == ONE_NODE_FOUND) {
       if (root == p) {
         return p;
       } else if (root == q) {
         return q;
       }
     }

     int nodesFromRight = covers(root.right, p, q); // Check right side

     if(nodesFromRight == TWO_NODES_FOUND) {
       if(root.right == p || root.right == q) {
         return root.right;
       } else {
         return findAncestor(root.right, p, q);
       }
     } else if (nodesFromRight == ONE_NODE_FOUND) {
       if (root == p) {
         return p;
       } else if (root == q) {
         return q;
       }
     }

     if (nodesFromLeft == ONE_NODE_FOUND &&
         nodesFromRight == ONE_NODE_FOUND) {
       return root;
     } else {
       return null;
     }
   }

  // Checks how many “special” nodes are located under this root
  private static int covers(TreeNode root, TreeNode p, TreeNode q) {
    int ret = NO_NODES_FOUND;

    if (root == null) {
      return ret;
    }

    if (root == p || root == q) {
      ret += 1;
    }

    ret += covers(root.left, p, q);

    // Found p and q
    if(ret == TWO_NODES_FOUND) {
      return ret;
    }

    return ret + covers(root.right, p, q);
  }

  /**
    * 4.7
    * You  have  two  very  large  binary  trees: T1,  with  millions  of  nodes,  and T2,  with  hundreds of nodes
    * Create an algorithm to decide if T2 is a subtree of T1
    * Worst  case  runtime  is  at most O(n * m), where n and m are the sizes of trees T1 and T2, respectively
    * If k is the number of occurrences of T2’s root in T1, the worst case runtime can be characterized as O(n + k * m)
  */
  public static boolean isSubTree(TreeNode t1, TreeNode t2) {
    if(t1 == null || t2 == null) {
      return false;
    }

    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.add(t1);

    while(!q.isEmpty()) {
      TreeNode node = q.remove();

      if(node.data == t2.data) {
        if(compareTrees(node, t2)) {
          return true;
        }
      }

      if(node.left != null) {
        q.add(node.left);
      }

      if(node.right != null) {
        q.add(node.right);
      }
    }

    return false;
  }

  /**
    * helper function that will that will return true if t2 is subtree of t1 starting at t1
  */
  private static boolean compareTrees(TreeNode t1, TreeNode t2) {
    Queue<TreeNode> q1 = new LinkedList<TreeNode>();
    Queue<TreeNode> q2 = new LinkedList<TreeNode>();

    q1.add(t1);
    q2.add(t2);

    while(!(q2.isEmpty())) {
      if(q1.isEmpty()) {
        return false;
      }

      TreeNode node1 = q1.remove();
      TreeNode node2 = q2.remove();

      if(node1.data != node2.data) {
        return false;
      }

      if(node1.left != null && node2.left != null) {
        q1.add(node1.left);
        q2.add(node2.left);
      }

      if(node1.right != null && node2.right != null) {
        q1.add(node1.right);
        q2.add(node2.right);
      }
    }

    return true;
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

    System.out.println("\n STARTING QUESTIONS: ");
    BTreePrinter.printNode(tree.root);
    BTreePrinter.printNode(bst.root);

    // System.out.println("Running 4.1: ");
    // System.out.println(isBalanced(tree.root));
    //
    // System.out.println("Running 4.3: ");
    // int[] sortedArr = new int[] { 1, 2, 3, 4, 5 };
    // TreeNode node = createTree(sortedArr);
    //
    // System.out.println("Running 4.4: ");
    // ArrayList<LinkedList<TreeNode>> listOfNodes = createLists(bst.root);
    // printListOfLinkedLists(listOfNodes);
    //
    // System.out.println("Running 4.5: ");
    // TreeNode successor = findSuccessor(bst.root.left.right);
    // System.out.println("Successor: " + successor.data);

    System.out.println("Running 4.6: ");
    TreeNode ancestor = findAncestor(tree.root, tree.root.left.left, tree.root.right);
    System.out.println("Ancestor: " + ancestor.data);

    System.out.println("Running 4.7: ");
    BinaryTree tree1 = new BinaryTree();
    tree1.add(1);
    tree1.add(2);
    tree1.add(3);
    tree1.add(4);
    tree1.add(5);

    BinaryTree tree2 = new BinaryTree();
    tree2.add(3);
    tree2.add(4);
    tree2.add(5);

    BTreePrinter.printNode(tree1.root);
    BTreePrinter.printNode(tree2.root);

    System.out.println("T2 is is a sub tree: " + isSubTree(tree1.root, tree2.root));
  }
}
