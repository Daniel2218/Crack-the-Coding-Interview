package interview;

import java.util.ArrayDeque;
import java.util.Deque;

class ArrayAndStrings {
    public static void main(String[] args) {
        int a = (int) 'a';
        int testArr[][] = new int[3][3];
        testArr = setTestArr(testArr);

        // System.out.println(isUnique("daniela"));
        // System.out.println(reverse("daniel"));
        // System.out.println(isAnagram("debit cArd1","bad credit1"));
        // printArr(rotatedArr(testArr));
        System.out.println(removeDups("ldaaaannniela"));

    }

    // 1.1
    public static Boolean isUnique(String str) {
      Boolean tracker[] = new Boolean[256];
      int length = str.length();
      int ascVal;

      for (int i = 0; i < length; i++) {
        ascVal = (int) str.charAt(i);

        if(tracker[ascVal] == null) {
          tracker[ascVal] = false;
        } else if(tracker[ascVal] == true) {
          return false;
        }

        tracker[ascVal] = true;
      }

      return true;
    }

    // 1.2
    public static String reverse(String str) {
       Deque<Character> stack = new ArrayDeque<Character>();
       int length = str.length();
       String newStr = "";

       for(int i = 0; i < length; i++) {
         stack.push(str.charAt(i));
       }

       for(int i = 0; i < length; i++) {
         newStr += stack.pop();
       }

       return newStr;
    }

    // 1.3
    public static String removeDups(String s) {
      StringBuffer newStr = new StringBuffer();
      int length = s.length();

      for (int i = 0; i < length; i++) {
        String c = s.charAt(i) + "";
        if(newStr.indexOf(c) == -1) {
          newStr.append(c);
        }
      }

      return newStr.toString();
    }

    // 1.4
    public static Boolean isAnagram(String w1, String w2) {
      int w1Len = w1.length();
      int w2Len = w2.length();
      int ascValW1, ascValW2;
      Boolean tracker1[] = new Boolean[256];
      Boolean tracker2[] = new Boolean[256];

      if (w1Len != w2Len) {
        return false;
      }

      for(int i = 0; i < w1Len; i++) {
        ascValW1 = (int) w1.toLowerCase().charAt(i);
        ascValW2 = (int) w2.toLowerCase().charAt(i);

        if(!isLetter(ascValW1) || !isLetter(ascValW2)) {
          return false;
        }

        tracker1[ascValW1] = true;
        tracker2[ascValW2] = true;
      }

      for(int i = 0; i < w1Len; i++) {
        ascValW1 = (int) w1.toLowerCase().charAt(i);

        if(tracker2[ascValW1] == null) {
          return false;
        }
      }

      return true;
    }

    private static Boolean isLetter(int ascVal) {
      return (ascVal >= 65 && ascVal <= 90) || (ascVal >= 97 && ascVal <= 122);
    }

    // 1.6
    public static int[][] rotatedArr(int arr[][]) {
      int length = arr.length - 1;
      int newArr[][] = new int[length + 1][length + 1];
      int newX, newY;

      for(int y = 0; y <= length; y++) {
        for(int x = 0; x <= length; x++) {
          newX = length - y;
          newY = x;

          newArr[newY][newX] = arr[y][x];
        }
      }

      return newArr;
    }

    public static int[][] rotatedArr2(int arr[][]) {
      int length = arr.length;

      for(int layer = 0; layer <= length / 2; layer++) {
        int first = layer;
        int last  = length - 1 - layer;

        for(int i = first; i < last; i++) {
          int topLeft  = arr[layer][i];
          int topRight = arr[layer][last];
          int botLeft  = arr[last][layer];
          int botRight = arr[layer][last];

        }
      }

      return arr;
    }

    private static void printArr(int[][] arr) {
      int length = arr.length - 1;

      for(int y = 0; y <= length; y++) {
        for(int x = 0; x <= length; x++) {
          System.out.print(arr[y][x] + "  ");
        }
        System.out.println("");
      }
    }

    private static int[][] setTestArr(int testArr[][]) {
      int length = testArr.length - 1;
      int counter = 0;

      for(int y = 0; y <= length; y++) {
        for(int x = 0; x <= length; x++) {
          testArr[y][x] = ++counter;
        }
      }

      return testArr;
    }
}
