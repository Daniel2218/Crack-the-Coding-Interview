package datastructures;

import java.util.ArrayDeque;
import java.util.Deque;

class Chapter1 {
    public static void main(String[] args) {
        int a = (int) 'a';
        int testArr[][] = new int[4][4];
        testArr = setTestArr(testArr);

        System.out.println("Running 1.1: ");
        System.out.println(isUnique("daniel"));
        System.out.println(isUnique2("daniela"));

        System.out.println("Running 1.2: ");
        System.out.println(reverse("daniel"));

        System.out.println("Running 1.3: ");
        char[] charArr = "daniela".toCharArray();
        removeDups(charArr);
        System.out.println(charArr);

        System.out.println("Running 1.4: ");
        System.out.println(isAnagram("credit card","car ctidder"));

        System.out.println("Running 1.5: ");
        char[] arr = new char[9];
        arr[0] = 'h';
        arr[1] = 'e';
        arr[2] = 'l';
        arr[3] = 'l';
        arr[4] = 'o';
        arr[5] = ' ';
        replaceSpaces(arr, 6);
        System.out.println(arr);

        System.out.println("Running 1.6: ");
        printArr(rotatedArr(testArr));
        System.out.println("Running 1.6  More Efficient: ");
        printArr(rotatedArr2(testArr));

        testArr[0][0] = 0;
        testArr[2][2] = 0;

        System.out.println("Running 1.7: ");
        setZeroes(testArr);
        printArr(testArr);

        System.out.println("Running 1.8: ");
        System.out.println(isRotation("waterbottle","erbottlewat"));
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
      // java will initialized tracker with false for each of its entries
      boolean tracker[] = new boolean[256];

      for (int i = 0; i < length; i++) {
        ascVal = str.charAt(i);
        if(tracker[ascVal]) return false;
        tracker[ascVal] = true;
      }
      return true;
    }

    /** using a bitVector
      * we can save some space if we use a bitVector instead
      * assume that only lowercase values are used
      * How does this work?
      * 1 << ascVal:
      * The integer bitVector can be written in binary.
      * Each entry in the binary number represents a letter. Therefore 1011 or 11 would indicate letter a,b and d.
      * bitVector & (1 << ascVal):
      * The result of this will be whatever bits in both vectors are turned on 1001 & 1100 = 1000.
      * If we get a value greater then 0, then would mean that we have come across this letter in the past.
    **/
    public static Boolean isUnique2(String str) {
      if (str == null || str.isEmpty()) return false;

      int bitVector = 0;
      for (int i = 0; i < str.length(); i++) {
          int ascVal = str.charAt(i) - 'a';
          if ((bitVector & (1 << ascVal)) > 0) return false;
          else bitVector |= (1 << ascVal);
      }
      return true;
    }

    // 1.2
    // reverse a string
    public static String reverse(String str) {
       Deque<Character> stack = new ArrayDeque<Character>();
       int length = str.length();
       StringBuilder newStr = new StringBuilder();

       for(int i = 0; i < length; i++) {
         stack.push(str.charAt(i));
       }

       for(int i = 0; i < length; i++) {
         newStr.append(stack.pop());
       }

       return newStr.toString();
    }

    // 1.3
    // an algorithm and write code to remove the duplicate characters in a string
    public static void removeDups(char[] s) {
      if(s == null) return;

      int length = s.length;

      if (length < 2) return;
      boolean tracker[] = new boolean[256];

      tracker[s[0]] = true;
      int tail = 1;

      for(int i = 1; i < length; ++i) {
        if(!tracker[s[i]]) {
          s[tail] = s[i];
          ++tail;
          tracker[s[i]] = true;
        }
      }
      s[tail] = 0;
    }

    // 1.4
    public static Boolean isAnagram(String w1, String w2) {
      int w1Len = w1.length();
      int w2Len = w2.length();
      int ascValW1, ascValW2;
      Boolean tracker[] = new Boolean[256];

      if (w1Len != w2Len) return false;

      for(int i = 0; i < w1Len; i++) {
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

    /** 1.5
      * Write a method to replace all spaces in a string with'%20'. You may assume that
      * the string has sufficient space at the end of the string to hold the additional
      * characters, and that you are given the "true" length of the string. (Note: if
      * implementing in Java, please use a character array so that you can perform this
      * operation in place.)
    **/
    public static void replaceSpaces(char[] str, int length) {
      int spacesCount = 0;
      int newLength = 0;
      // scan for number of spaces
      for (int i = 0; i < length; i++) {
        if(str[i] == ' ') {
          spacesCount++;
        }
      }

      /** %20 takes up 3 bytes, therefore after removing
        * the space from the string we need two extra bytes for the rest.
      **/
      newLength = length + spacesCount * 2;
      str[newLength] = '\0';
      --newLength;

      for (int i = length - 1; i >= 0; --i) {
         if (str[i] == ' ') {
             str[newLength--] = '0';
             str[newLength--] = '2';
             str[newLength--] = '%';
         } else {
             str[newLength--] = str[i];
         }
       }
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

    // more efficient array swap
    public static int[][] rotatedArr2(int arr[][]) {
      int length = arr.length;

      for(int layer = 0; layer < length / 2; layer++) {
        int first = layer;
        int last  = length - 1 - layer;

        for(int i = first; i < last; i++) {
          int offset   = i - first;
          int topLeft  = arr[first][i]; // save topLeft

          arr[first][i] = arr[last - offset][first]; // topLeft = bottomRight
          arr[last-offset][first] = arr[last][last - offset]; // bottomRight = bottomLeft
          arr[last][last - offset] = arr[i][last]; // bottomLeft = topRight
          arr[i][last] = topLeft; // topRight = topLeft
        }
      }

      return arr;
    }

    /**
      * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
      * column is set to 0
    **/
    public static void setZeroes(int[][] matrix) {
      int[] rows = new int[matrix.length];
      int[] cols = new int[matrix[0].length];

      // first pass to find all the zeros
      for(int col = 0; col < matrix[0].length; col++) {
        for(int row = 0; row < matrix.length; row++) {
          if(matrix[col][row] == 0) {
            rows[row] = 1;
            cols[col] = 1;
          }
        }
      }

      for(int col = 0; col < matrix[0].length; col++) {
        for(int row = 0; row < matrix.length; row++) {
          if(rows[row] == 1 || cols[col] == 1) {
            matrix[col][row] = 0;
          }
        }
      }
    }


    /**
      * 1.8
      * Assume  you  have  a  method  isSubstring  which  checks  if  one  word  is  a  substring  of
      * another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using
      * only one call to isSubstring (i e
      *,“waterbottle” is a rotation of “erbottlewat”)
    **/
    public static boolean isRotation(String s1, String s2) {
      if(s1.isEmpty() || s2.isEmpty()) return false;

      if(s1.length() != s2.length()) return false;

      StringBuilder newStr = new StringBuilder();

      newStr.append(s1);
      newStr.append(s1);

      return isSubstring(newStr.toString(), s2);
    }

    // helper function to get isRotation to compile
    private static boolean isSubstring(String s1, String s2) {
      return s1.contains(s2);
    }

    // helper functions
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
