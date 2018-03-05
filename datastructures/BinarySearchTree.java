package datastructures;

import datastructures.*;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
  Basic implementation of a BinarySearchTree
**/
public class BinarySearchTree {
  public TreeNode root;

  public BinarySearchTree() { }

  public void add(int data) {
    this.root = add(this.root, data);
  }

  private TreeNode add(TreeNode tree, int data) {
    if(tree == null) {
      tree = new TreeNode(data);
    } else {
      if(data >= tree.data) {
        tree.right = add(tree.right, data);
      } else {
        tree.left = add(tree.left, data);
      }
    }

    return tree;
  }
}
