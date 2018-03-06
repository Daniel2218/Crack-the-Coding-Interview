package datastructures;

public class TreeNode {
  public TreeNode left, right, parrent;
  public int data;

  public TreeNode(int data, TreeNode parrent) {
    this.data = data;
    this.parrent = parrent;
  }

  public TreeNode(int data) {
    this.data = data;
  }
}
