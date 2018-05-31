package datastructures;

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
    this.root = add(this.root, null, data);
  }

  private TreeNode add(TreeNode tree, TreeNode parrent, int data) {
    if(tree == null) {
      tree = new TreeNode(data, parrent);
    } else {
      if(data >= tree.data) {
        tree.right = add(tree.right, tree, data);
      } else {
        tree.left = add(tree.left, tree, data);
      }
    }

    return tree;
  }
}
