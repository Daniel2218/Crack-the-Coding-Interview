import java.util.Random;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

class LinkedList {
  public Node head;
  public int length;

  public LinkedList() { }

  public void add(int data) {
    Node newNode = new Node(data);
    Node current = this.head;

    if(current == null) {
      this.head = newNode;
    } else {
      while(current.next != null) {
        current = current.next;
      }

      current.next = newNode;
      ++this.length;
    }
  }

  /**
    2.1: Write code to remove duplicates from an unsorted linked list
  **/
  public void deleteDups(Node current) {
    HashSet set = new HashSet<Integer>();
    Node previous = null;

    while(current != null) {
      if(!set.add(current.data)) {
        previous.next = current.next;
      } else {
        previous = current;
      }

      current = current.next;
    }
  }

  // without additional buffer
  public void deleteDups2(Node current) {
    Node prev = null;
    Node head = current;
    int n = 0;

    while(current != null) {
      boolean found = runner(head, current.data, n);

      if(found) {
        prev.next = current.next;
      } else {
        prev = current;
      }

      current = current.next;
      n++;
    }
  }

  private boolean runner(Node head, int data, int n) {
    while(head != null && n > 0) {
      if(head.data == data) {
        return true;
      }

      head = head.next;
      n--;
    }
    return false;
  }

  // 2.2: Implement an algorithm to find the nth to last element of a singly linked list
  public Node nthToLast(Node head, int n) {
      if(head == null || n < 1) {
        return null;
      }

      Node node1 = head;
      Node node2 = head;

      for(int i = 0; i < n - 1; i++) {
        if(node2 == null) {
          return null;
        }
        node2 = node2.next;
      }

      while(node2.next != null) {
        node1 = node1.next;
        node2 = node2.next;
      }
      return node1;
  }

  // 2.3
  // Note we assume that the node given will not be the tail or head of the LinkedList
  public boolean deleteInMiddle(Node node) {
    if(node == null || node.next = null) {
      return false;
    }

    node.data = node.next.data;
    node.next = node.next.next;
    return true;
  }

  private Node findNode(int n) {
    Node current = this.head;
    int i = 0;

    while(++i < n) {
      if(current == null) {
        return null;
      }

      current = current.next;
    }

    return current;
  }

  // 2.4
  // assume that list1 and list2 have the same number for elemenets
  public static LinkedList addLists(LinkedList list1, LinkedList list2) {
    LinkedList newList = new LinkedList();
    Node current1 = list1.head;
    Node current2 = list2.head;

    while(current1 != null) {
      newList.add(current1.data + current2.data);
      current1 = current1.next;
      current2 = current2.next;
    }

    return newList;
  }

  public void addRandomNodes(int amount) {
    Random r = new Random();
    int low = 1;
    int high = 100;
    int result;

    for (int i = 0; i < amount; i++) {
      result = r.nextInt(high - low) + low;
      this.add(result);
    }
  }

  public void print() {
    Node current = this.head;

    while(current != null) {
      System.out.println(current.data);
      current = current.next;
    }
  }

  class Node {
    public Node next;
    public int data;

    public Node() { }

    public Node(int data) {
      this.data = data;
    }
  }

  public static void main(String args[]) {
    LinkedList linkedList1 = new LinkedList();
    linkedList1.add(1);
    linkedList1.add(1);
    linkedList1.add(1);
    linkedList1.add(1);
    linkedList1.add(1);

    LinkedList linkedList2 = new LinkedList();
    linkedList2.add(1);
    linkedList2.add(2);
    linkedList2.add(3);
    linkedList2.add(1);
    linkedList2.add(8);
    linkedList2.add(2);


    // LinkedList newList = LinkedList.addLists(linkedList1, linkedList2);

    linkedList2.deleteDups2(linkedList2.head);
    // linkedList.nthToLast(4);
    // System.out.println(linkedList.fndNode(5).data);
    // linkedList.deleteInMiddle(linkedList.findNode(5));

    linkedList2.print();
  }
}
