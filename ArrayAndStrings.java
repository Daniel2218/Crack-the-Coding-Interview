import java.util.ArrayDeque;
import java.util.Deque;

class ArrayAndStrings {
    public static void main(String[] args) {
        int a = (int) 'a';
        int testArr[][] = new int[3][3];
        testArr = setTestArr(testArr);

        System.out.println(isUnique("daniela"));
        System.out.println(reverse("daniel"));
        System.out.println(isAnagram("debit cArd1","bad credit1"));
        // printArr(rotatedArr(testArr));
        // System.out.println(removeDups("ldaaaannniela"));

    }

    /**
      * 1.1
      * Implement an algorithm to determine if a string has all unique characters. What
      * if you cannot use additional data structures? (Assume string is ASCII based.)
    */
    public static Boolean isUnique(String str) {
      if (str == null || str.isEmpty()) return false;

      int ascVal;
      int length = str.length();
      Boolean tracker[] = new Boolean[256];

      for (int i = 0; i < length; ++i) {
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

    // using a bitVector
    public static Boolean isUnique2(String str) {
      if (str == null || str.isEmpty()) return false;
      if (str.length() > 256) return false;

      int bitVector = 0;
      for (int i = 0; i < str.length(); ++i) {
          char ch = str.charAt(i);
          if ((bitVector & (1 << ch)) > 0) return false;
          else bitVector |= (1 << ch);
      }

      return true;
    }

    // 1.2
    // reverse a string
    public static String reverse(String str) {
       Deque<Character> stack = new ArrayDeque<Character>();
       int length = str.length();
       StringBuilder newStr = new StringBuilder();

       for(int i = 0; i < length; ++i) {
         stack.push(str.charAt(i));
       }

       for(int i = 0; i < length; ++i) {
         newStr.append(stack.pop());
       }

       return newStr.toString();
    }

    // 1.3
    // an algorithm and write code to remove the duplicate characters in a string
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
      Boolean tracker[] = new Boolean[256];

      if (w1Len != w2Len) return false;

      for(int i = 0; i < w1Len; ++i) {
        ascValW1 = (int) w1.toLowerCase().charAt(i);
        ascValW2 = (int) w2.toLowerCase().charAt(i);

        tracker[ascValW2] = true;
      }

      for(int i = 0; i < w1Len; i++) {
        ascValW1 = (int) w1.toLowerCase().charAt(i);

        if(tracker[ascValW1] == null) {
          return false;
        }
      }
      return true;
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
