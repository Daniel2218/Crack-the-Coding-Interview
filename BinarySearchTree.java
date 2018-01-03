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

  // 4.3
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

  // 4.4
  public ArrayList<LinkedList<TreeNode>> createLists() {
    ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
    LinkedList<TreeNode> list = new LinkedList<TreeNode>();
    int level = 0;
    TreeNode current = this.root;

    list.add(current);
    result.add(level, list);

    while(true) {
      for(int i = 0; i < result.get(level).size(); i++) {
        current =  result.get(level).get(i);

        if(current != null) {
          if(current.left !=  null) {
            list.add(current.left);
          }
          if(current.right != null) {
            list.add(current.right);
          }
        }
      }

      if(list.size() > 0) {
        result.add(level + 1, list);
      } else {
        break;
      }
      level++;
    }

    return result;
  }

  public static void main(String args[]) {
    System.out.println("RUNNING");
    // BinarySearchTree bst = new BinarySearchTree();
    // bst.add(1);
    // bst.add(5);
    // bst.add(4);
    // bst.add(-1);
    // bst.add(6);
    // bst.add(6);
    // bst.add(2);
    // bst.add(-3);
    // bst.add(0);

    TreeNode bst;
    int arr[] = new int[6];
    arr[0] = 1;
    arr[1] = 2;
    arr[2] = 4;
    arr[3] = 5;
    arr[4] = 7;
    arr[5] = 9;

    bst = createTree(arr);
    BTreePrinter.printNode(bst);
  }
}
