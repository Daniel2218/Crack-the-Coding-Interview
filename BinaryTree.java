package interview;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayDeque;
import java.util.Deque;
import interview.BinaryTree;
import interview.TreeNode;
import interview.BTreePrinter;

public class BinaryTree {
  public TreeNode root;
  private int size;

  public BinaryTree() { }

  public void add(int data) {
    this.root = add(this.root, data);
  }

  private TreeNode add(TreeNode tree, int data) {
    if(tree == null) {
      tree = new TreeNode(data);
    } else {
      if(tree.right == null) {
        tree.right = add(tree.right, data);
      } else {
        tree.left = add(tree.left, data);
      }
    }

    return tree;
  }

  private void BFS() {
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    TreeNode node;

    q.add(this.root);

    while(!q.isEmpty()) {
      node = q.remove();
      System.out.println(node.data + " = Visted ");

      if(node.left != null) q.add(node.left);
      if(node.right != null) q.add(node.right);
    }
  }

  private void preOrder() {
      preOrder(this.root);
  }

  private void preOrder(TreeNode tree) {
    if(tree != null) {
      System.out.println(tree.data);
      preOrder(tree.left);
      preOrder(tree.right);
    }
  }

  public int getSize() {
    return size;
  }

  // 4.1
  private Boolean isBalanced() {
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

  private int maxPath(TreeNode tree){
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

  public static void main(String args[]) {
    BinaryTree tree = new BinaryTree();
    tree.add(5);
    tree.add(3);
    tree.add(6);
    tree.add(7);
    tree.add(8);

    BTreePrinter.printNode(tree.root);
    // System.out.println(tree.maxPath(tree.root));
    // System.out.println(tree.isBalanced());
    // System.out.println("BFS Search");
    // tree.BFS();
    // System.out.println("DFS Search");
    // tree.DFS();
    // System.out.println("Pre Order Transversal");
    // tree.preOrder();
  }
}
