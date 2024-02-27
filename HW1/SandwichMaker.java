import java.util.*;

public class SandwichMaker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println(longestSubstring(input));
    }

    public static int longestSubstring(String s) {
        int maxLength = 0;
        String curMaxSub = "";
        String prevMaxSub;
        ArrayList<Integer> maxes = new ArrayList<Integer>();
        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                int bCount = 1;
                int mCount = 0;
                int j = i + 1;
                while (j < s.length() && bCount != mCount) {
                    if (s.charAt(j) == 'b') {
                        bCount++;
                    } else if (s.charAt(j) == 'm') {
                        mCount++;
                    }
                    j++;
                }
                if (bCount == mCount){
                    prevMaxSub = curMaxSub;
                    if (flag + 1 == i){
                        curMaxSub = s.substring(i, j);
                        curMaxSub = prevMaxSub + curMaxSub;
                        System.out.println("i " + i);
                        System.out.println("j " + (j-1));
                        System.out.println("cur: " + curMaxSub);
                        System.out.println("prev: " + prevMaxSub);
                        System.out.println("flag: " + flag);
                        System.out.println("----");
                        flag = j - 1;
                    }else {
                        flag = j - 1;
                        curMaxSub = s.substring(i, j);
                        System.out.println("i " + i);
                        System.out.println("j " + (j-1));
                        System.out.println("cur: " + curMaxSub);
                        System.out.println("prev: " + prevMaxSub);
                        System.out.println("flag: " + flag);
                        System.out.println("----");
                    }
                    maxLength = curMaxSub.length();
                    System.out.println("max len: " + maxLength);
                    maxes.add(maxLength);
                }
            }
        }
        if (!maxes.isEmpty()) {
            maxLength = Collections.max(maxes);
        }
        return maxLength;
    }
}
