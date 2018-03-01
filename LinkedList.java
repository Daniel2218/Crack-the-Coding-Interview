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
    if(node == null || node.next == null) {
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

  /**
    * 2.4:
    * You have two numbers represented by a linked list, where each node contains a single digit
    * The digits are stored in reverse order, such that the 1’s digit is at the head of the list
    * Write a function that adds the two numbers and returns the sum as a linked list.
  **/

  // interative approach
  public Node addLists(Node node1, Node node2) {
     Node newNode = null;
     boolean ifCarry = false;

     while(node1 != null && node2 != null) {
        int total = node1.data + node2.data;

        total += ifCarry ? 1 : 0;

        ifCarry = false; // reset the carry after use

        int rounded = total % 10;

        Node temp = new Node(rounded);
        temp.next = newNode;
        newNode = temp;

        if(total >= 10) {
          ifCarry = true;
        }

        node1 = node1.next;
        node2 = node2.next;
     }

     // node1 and node2 are not of the same length
     if((node1 == null && node2 != null) ||
        (node2 == null && node1 != null)) {
       return null;
     }

     return newNode;
  }

  // recursive approach
  public Node addLists2(Node node1, Node node2, int carry) {
    if(node1 == null && node2 ==  null) {
      return null;
    }

    Node newNode = new Node(carry);
    int total = carry;

    if(node1 != null) {
      total += node1.data;
    }

    if(node2 != null) {
      total += node2.data;
    }

    newNode.data = total % 10;
    Node more = addLists2(node1 == null ? null : node1.next,
                          node2 == null ? null : node2.next,
                          total > 10 ? 1 : 1);
    newNode.next = more;
    return newNode;
  }

  public static Node hasCycle(Node head) {
    Node slowRunner = head;
    Node fastRunner = head;

    // find meeting point if there is any
    while(fastRunner.next != null) {
      slowRunner = slowRunner.next;
      fastRunner = fastRunner.next.next;
      if(slowRunner == fastRunner) {
        break;
      }
    }

    // if there is no loop return null
    if(fastRunner.next == null) {
      return null;
    }

    slowRunner = head;
    while(slowRunner != fastRunner) {
      slowRunner = slowRunner.next;
      fastRunner = fastRunner.next;
    }

    // does not matter which is returned
    return fastRunner;
  }

  private void addRandomNodes(int amount) {
    Random r = new Random();
    int low = 1;
    int high = 100;
    int result;

    for (int i = 0; i < amount; i++) {
      result = r.nextInt(high - low) + low;
      this.add(result);
    }
  }

  private void print() {
    Node current = this.head;

    while(current != null) {
      System.out.println(current.data);
      current = current.next;
    }
  }

  static class Node {
    public Node next;
    public int data;

    public Node() { }

    public Node(int data) {
      this.data = data;
    }
  }

  public static void main(String args[]) {
    LinkedList linkedList1 = new LinkedList();
    linkedList1.add(3);
    linkedList1.add(1);
    linkedList1.add(5);

    LinkedList linkedList2 = new LinkedList();
    linkedList2.add(5);
    linkedList2.add(9);
    linkedList2.add(2);

    Node newNode = linkedList1.addLists(linkedList1.head, linkedList2.head);

    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    // n5.next = n3;

    Node startOfLoop = hasCycle(n1);
    if(startOfLoop != null) {
      System.out.println(startOfLoop.data);
    }

    // while(newNode != null) {
    //   System.out.println(newNode.data);
    //   newNode = newNode.next;
    // }

    // LinkedList newList = LinkedList.addLists(linkedList1, linkedList2);

    // linkedList2.deleteDups2(linkedList2.head);
    // linkedList.nthToLast(4);
    // System.out.println(linkedList.fndNode(5).data);
    // linkedList.deleteInMiddle(linkedList.findNode(5));

    // linkedList2.print();
  }
}
