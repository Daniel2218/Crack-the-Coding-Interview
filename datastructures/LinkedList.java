package datastructures;

import java.util.Random;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;


/**
  A quick implemenation of a LinkedList
**/
public class LinkedList {
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

  public Node findNode(int n) {
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

  public void print(Node node) {
    Node current = this.head;

    while(current != null) {
      System.out.println(current.data);
      current = current.next;
    }
  }
}
