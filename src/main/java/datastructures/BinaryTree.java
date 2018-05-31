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
      System.out.println(node.data + " = Visted");

      if(node.left != null) q.add(node.left);
      if(node.right != null) q.add(node.right);
    }
  }

  // Recursive Depth First Searh implementation
  public void DFS() {
    DFS(this.root);
  }

  public void DFS(TreeNode node) {
    if(node != null) {
      System.out.println(node.data + " = Visted");

      if(node.left != null) {
        DFS(node.left);
      }

      if(node.right != null) {
        DFS(node.right);
      }
    }
  }

  // In-Order Transversal implementation
  public void inOrder() {
    inOrder(this.root);
    System.out.println("null");
  }

  public void inOrder(TreeNode tree) {
    if(tree != null) {
      inOrder(tree.left);
      System.out.print(tree.data + " => ");
      inOrder(tree.right);
    }
  }

  // PreOrder Transversal implementation
  public void preOrder() {
      preOrder(this.root);
      System.out.println("null");
  }

  public void preOrder(TreeNode tree) {
    if(tree != null) {
      System.out.print(tree.data + " => ");
      preOrder(tree.left);
      preOrder(tree.right);
    }
  }

  // PostOrder Transversal implementation
  public void postOrder() {
      preOrder(this.root);
      System.out.println("null");
  }

  public void postOrder(TreeNode tree) {
    if(tree != null) {
      preOrder(tree.left);
      preOrder(tree.right);
      System.out.print(tree.data + " => ");
    }
  }

  public int getSize() {
    return size;
  }
}
