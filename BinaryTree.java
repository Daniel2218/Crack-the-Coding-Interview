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
}
