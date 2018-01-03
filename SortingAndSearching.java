package interview;

import java.util.Deque;
import java.util.ArrayDeque;

class SortingAndSearching {

  public static void main(String args[]) {
    // String arr[] = new String[13];
    // arr[0]  = "at";
    // arr[1]  = "";
    // arr[2]  = "";
    // arr[3]  = "";
    // arr[4]  = "ball";
    // arr[5]  = "";
    // arr[6]  = "";
    // arr[7]  = "car";
    // arr[8]  = "";
    // arr[9]  = "";
    // arr[10] = "dad";
    // arr[11] = "";
    // arr[12] = "";
    //
    // System.out.println(find(arr,"ballcar"));

    int arr[][] = new int[2][2];
    arr[0][0]  = 1;
    arr[0][1]  = 2;
    arr[1][0]  = 3;
    arr[1][1]  = 4;

    System.out.println(findValue(arr,1,2,2));
  }


  // 9.5
  public static int find(String arr[], String val) {
    return find(arr,val,0,arr.length);
  }

  public static int find(String arr[], String val, int start , int end) {
    int mid = start + (end - start) / 2;

    if(start > end) {
      return -1;
    } else if(arr[mid] == "") {
      // find value to the right
      int index = findRight(arr, mid + 1, end);

      // find value to the left
      if(index == -1) {
        index = findLeft(arr, mid - 1, start);
        if(index == -1) {
          return -1;
        }
      }

      mid = index;
    }

    if(arr[mid].compareTo(val) < 0) {
      return find(arr, val, mid + 1, end);
    } else if(arr[mid].compareTo(val) > 0) {
      return find(arr, val, start,  mid - 1);
    } else {
      return mid;
    }
  }

  public static int findRight(String arr[], int start, int end) {
    String nearest = "";

    // find actual value to the right
    while(start <= end) {
      nearest = arr[start];
      if(nearest != "") {
        break;
      }
      start++;
    }

    if(nearest != "") {
      return start;
    }

    return -1;
  }

  public static int findLeft(String arr[], int start, int end) {
    String nearest = "";

    // find actual value to the right
    while(start >= end) {
      nearest = arr[start];
      if(nearest != "") {
        break;
      }
      start--;
    }

    if(nearest != "") {
      return start;
    }

    return -1;
  }

  // 9.6
  public static Boolean findValue(int arr[][], int val, int m, int n) {
    int row = 0;
    int col = n - 1;

    while(col >= 0 && row < m) {
      int current = arr[row][col];

      if(current == val) {
        return true;
      } else if(current > val) {
        col--;
      } else {
        row++;
      }
    }

    return false;
  }
}
