package datastructures;

import datastructures.*;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayDeque;
import java.util.Deque;

/**
  Basic implementation of a BinaryTree
**/
public class BinaryTree {
  public TreeNode root;
  private int size;

  public BinaryTree() { }

  public void add(int data) {
    this.root = add(this.root, data);
  }

  // BinaryTree insertion implementation
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

  // Breadth First Search implementation
  public void BFS() {
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

  // In-Order Transversal implementation
  public void inOrder() {
    inOrder(this.root);
  }

  public void inOrder(TreeNode tree) {
    if(tree != null) {
      inOrder(tree.left);
      System.out.println(tree.data);
      inOrder(tree.right);
    }
  }

  // PreOrder Transversal implementation
  public void preOrder() {
      preOrder(this.root);
  }

  public void preOrder(TreeNode tree) {
    if(tree != null) {
      System.out.println(tree.data);
      preOrder(tree.left);
      preOrder(tree.right);
    }
  }

  public int getSize() {
    return size;
  }
}
