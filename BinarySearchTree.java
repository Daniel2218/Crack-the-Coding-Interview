package interview;

import interview.TreeNode;
import interview.BTreePrinter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

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
      if(tree.data <= data) {
        tree.right = add(tree.right, data);
      } else {
        tree.left = add(tree.left, data);
      }
    }

    return tree;
  }
}
