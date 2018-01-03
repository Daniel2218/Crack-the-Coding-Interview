import java.util.*;

public class Recursion {

  public static void main(String args[]) {
    // ArrayList<ArrayList<Integer>> subSets = new ArrayList<ArrayList<Integer>>();
    // ArrayList<Integer> list = new ArrayList<Integer>();
    // list.add(1);
    // list.add(2);
    // // list.add(3);
    // // subSets.add(list);
    //
    // // ArrayList<Integer> list1 = new ArrayList<Integer>();
    // // list1.add(1);
    // // list1.add(2);
    // // list1.add(3);
    // //
    // //
    // // subSets.add(list1);
    // System.out.println(getSubSets(list));

    // ArrayList<String> strings = permute("aba");
    // subStrings("daata");
    // System.out.println(isPalindrome(strings));

    String trips [] = new String[3];
    trips[0] = "10 2 5";
    trips[1] = "12 4 4";
    trips[2] = "6 2 2";
    maximumCupcakes(trips);
  }

  private static int subStrings(String str) {
    String sub;
    int length = str.length();
    int numOfPalins = 0;

    for(int c = 0 ; c < length ; c++ ) {
        for(int i = 1; i <= length - c; i++) {
           sub = str.substring(c, c+i);
           if(isPalindrome(sub)) {
             numOfPalins++;
           }
        }
     }

    return numOfPalins;
  }

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

  private static int isPalindrome(ArrayList<String> str) {
    int length = str.size();
    int numOfPalins = 0;

    for(int i = 0; i < length; i++) {
      if(isPalindrome(str.get(i))) {
        numOfPalins++;
      }
    }

    return numOfPalins;
  }

  private static Boolean isPalindrome(String str) {
    String reverse = new StringBuffer(str).reverse().toString();

    if (str.equals(reverse)) {
      return true;
    }
    return false;
  }

  static int countPalindromes(String s) {
        String sub;
        int length = s.length();
        int numOfPalins = 0;
        ArrayList<String> strings = new ArrayList<String>();

        for(int c = 0 ; c < length ; c++ ) {
            for(int i = 1; i <= length - c; i++) {
                sub = s.substring(c, c+i);

                if(isPalindrome(sub)) {
                    numOfPalins++;
                }
            }
        }

        return numOfPalins;
    }

    static void maximumCupcakes(String[] trips) {
      int length = trips.length;
      int cost, wrappersRatio, cakes, money, numOfWraps;

       for(int i = 0; i < length; i++) {
         String[] splited = trips[i].split("\\s+");
         cakes = 0;
         numOfWraps = 0;

         money         = Integer.parseInt(splited[0]);
         cost          = Integer.parseInt(splited[1]);
         wrappersRatio = Integer.parseInt(splited[2]);

         cakes = money / cost;
         money = money - (cost * cakes);
         numOfWraps = cakes;

         while (numOfWraps >= wrappersRatio) {
           numOfWraps = numOfWraps - wrappersRatio;
           cakes++;
           numOfWraps++;
         }
         System.out.println(cakes);
    }
  }
}
