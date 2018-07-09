package problems;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Chapter8 {
  /**
    * 8.1
    * Write a method to generate the nth Fibonacci number
  **/
  private static int getFibNumber(int n) {
    if(n <= 0) return -1;

    if(n == 2 || n == 1) {
      return 1;
    }

    return getFibNumber(n-1) + getFibNumber(n-2);
  }

  /**
    * 8.2
    * Imagine a robot sitting on the upper left hand corner of an NxN grid. The robot can
    * only move in two directions: right and down. How many possible paths are there for
    * the robot?
  **/
  public static int paths(int n) {
    if(n <= 0) {
      return 0;
    } else if(n == 1) {
      return 1;
    }

    return paths(0, 0, n, null);
  }

  public static int paths(int n, List<Point> offLimits) {
    if(n <= 0) {
      return 0;
    } else if(n == 1) {
      return 1;
    }

    return paths(0, 0, n, offLimits);
  }

  private static int paths(int x, int y, int n, List<Point> offLimits) {
    int paths = 0;
    int leftStatus = isPath(x + 1, y, n, offLimits);
    int rightStatus = isPath(x, y + 1, n, offLimits);

    if(leftStatus == 0) {
      paths += paths(x + 1, y, n, offLimits);
    }

    if(rightStatus == 0) {
      paths += paths(x, y + 1, n, offLimits);
    }

    if(leftStatus == 1) {
      paths++;
    }

    if(rightStatus == 1) {
      paths++;
    }

    return paths;
  }

  private static int isPath(int x, int y, int n, List<Point> offLimits) {
    if(x > n - 1 || y > n - 1) {
      return -1;
    } else if (x == n - 1 && y == n-1) {
      return 1;
    } else if (is_free(x, y, n, offLimits)) {
      return -1;
    } else {
      return 0;
    }
  }

  private static boolean is_free(int x, int y, int n, List<Point> offLimits) {
    if(offLimits == null) {
      return false;
    }

    for(int i = 0; i < offLimits.size(); i++) {
      Point p = offLimits.get(i);

      if(p.getX() == x && p.getY() == y) {
        return true;
      }
    }

    return false;
  }

  /**
    * 8.3
    * Write a method that returns all subsets of a set
  **/
  private static ArrayList<ArrayList<Integer>> getSubSets(ArrayList<Integer> set) {
    ArrayList<ArrayList<Integer>> allSubSets = new ArrayList<ArrayList<Integer>>();
    int size = allSubSets.size();

    if(set.isEmpty()) {
      ArrayList<Integer> emptySet = new ArrayList<Integer>();
      allSubSets.add(emptySet);
    } else {
      // clones array with last element removed
      ArrayList<Integer> smallerSet = (ArrayList<Integer>) set.clone();
      smallerSet.remove(size);

      ArrayList<ArrayList<Integer>> smallerSubSets = getSubSets(smallerSet);
      // System.out.println("Should be empty list of lists: " + smallerSubSets);
      // adds all subset of the smaller set to the allSubsets
      for(ArrayList<Integer> subset : smallerSubSets) {
        // System.out.println("Should be empty list: " + subset);
        allSubSets.add(subset);
        // System.out.println("Should be [[]]: " + allSubSets);
        subset.add(set.get(size));
        // System.out.println("Should be [1]: " + subset);
        allSubSets.add(subset);
        // System.out.println("Should be [[], [1]]: " + allSubSets);
      }
    }

    return allSubSets;
  }

  /**
    * 8.4
    * Write a method to compute all permutations of a string.
  **/
  private static ArrayList<String> permute(String str) {
    int length = str.length();
    ArrayList<String> allStrings = new ArrayList<String>();

    if (length == 1) {
      allStrings.add(str);
    } else if(length > 1){
      ArrayList<String> allStringsSmall = permute(str.substring(0, length - 1));
      String lastChar = str.substring(length - 1);
      String newStr = "";

      for (String s : allStringsSmall) {
        for(int i = 0; i < length; i++) {
          newStr += s.substring(0, i) + lastChar + s.substring(i);
          allStrings.add(newStr);
          newStr = "";
        }
      }
    }

    return allStrings;
  }

  /**
    * 8.5
    * Implement an algorithm to print all valid (e.g., properly opened and closed) combinations
    * of n-pairs of parentheses.

    * EXAMPLE:
    *  input: 3 (e.g., 3 pairs of parentheses)
    *  output: ()()(), ()(()), (())(), ((()))
  **/
  private static void parenth(int n) {
    parenth("",n,n);
  }

  private static void parenth(String str, int l, int r) {
    if(l == 0 && r == 0) {
      System.out.println(str);
    } else {
      if (l > 0) {
        str += "(";
        parenth(str, l - 1, r);
      }
      if(r > l) {
        str += ")";
        parenth(str,l, r - 1);
      }
    }
  }

  /**
    8.6
    Implement the “paint fill” function that one might see on many image editing programs.
    That is, given a screen (represented by a 2-dimensional array of Colors), a
    point, and a new color, fill in the surrounding area until you hit a border of that color.
  */
  enum Color {
    Black, White, Red, Yellow, Green
  }

  boolean PaintFill(Color[][] screen, int x, int y, Color ocolor, Color ncolor) {
      if(x < 0 || x >= screen[0].length ||
         y < 0 || y >= screen.length) {
           return false;
      }

      if(screen[y][x] == ocolor) {
        screen[y][x] = ncolor;
        PaintFill(screen, x + 1, y, ocolor. ncolor); // left
        PaintFill(screen, x - 1, y, ocolor. ncolor); // right
        PaintFill(screen, x, y - 1, ocolor. ncolor); // top
        PaintFill(screen, x, y + 1, ocolor. ncolor); // bottom
      }

      return true;
  }

  boolean PaintFill(Color[][] screen, int x, int y, Color ncolor){
    return PaintFill(screen, x, y, screen[y][x], ncolor);
  }

  /**
    * 8.7
    * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents) and
    * pennies (1 cent), write code to calculate the number of ways of representing n cents.
  **/
  private static int makeChange(int n, int denom) {
    int next_denom = 0;
    switch (denom) {
      case 25:
        next_denom = 10;
        break;
      case 10:
        next_denom = 5;
        break;
      case 5:
        next_denom = 1;
        break;
      case 1:
        return 1;
    }

    int ways = 0;
    for (int i = 0; i * denom <= n; i++) {
      ways += makeChange(n - i * denom, next_denom);
    }
    return ways;
  }

  /**
    8.8

    Write  an  algorithm  to  print  all  ways  of  arranging  eight  queens  on  a  chess  board  so
    that none of them share the same row, column or diagona
  */
  int columnForRow[] = new int[8];

  private boolean check(int row) {
    for(int i = 0; i < row; i++) {
      int diff = Math.abs(columnForRow[i] - columnForRow[row]);
      if(diff == 0 || diff == row - i) return false;
    }
    return true;
  }

  public void PlaceQueen(int row) {
    if(row == 8) {
      // printBoard(); unimplemented function that would print the printBoard
      return;
    }

    for(int i = 0; i < 8; i++) {
      columnForRow[row] = i;
      if(check(row)) {
        PlaceQueen(row+1);
      }
    }
  }

  public static void main(String args[]) {
    ArrayList<ArrayList<Integer>> subSets = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> list1 = new ArrayList<Integer>();
    list1.add(1);
    list1.add(2);
    list1.add(3);
    subSets.add(list1);

    ArrayList<Integer> list2 = new ArrayList<Integer>();
    list2.add(1);
    list2.add(2);
    list2.add(3);
    subSets.add(list2);

    List<Point> offLimits = new ArrayList<Point>();
    offLimits.add(new Point(1,1));

    int arr[][] = new int[3][3];

    System.out.println("Running 8.1: ");
    System.out.println(getFibNumber(5));

    System.out.println("Running 8.2: ");
    System.out.println(paths(4));

    System.out.println("Running 8.2 With offLimits: ");
    System.out.println(paths(3, offLimits));

    System.out.println("Running 8.3: ");
    // System.out.println(getSubSets(list));

    System.out.println("Running 8.4: ");
    System.out.println(permute("abcde"));

    System.out.println("Running 8.5: ");
    parenth(4);

    System.out.println("Running 8.7: ");
    System.out.println(makeChange(10, 25));
  }
}
