import java.util.*;

public class SandwichMaker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println(longestSubstring(input));
    }

    public static int longestSubstring(String s) {
        int bCount = 0, mCount = 0, maxLength = 0;
        ArrayList<Integer> maxes = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b'){
                bCount = 0;
                mCount = 0;
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) == 'b'){
                        bCount++;
//                        System.out.println("b: " + bCount);
                    }
                    else if (s.charAt(j) == 'm'){
                        mCount++;
//                        System.out.println("m: " + mCount);

                    }
                    if (mCount > bCount){
                        break;
                    }
                    if (mCount == bCount && s.charAt(j) == 'm'){
//                        System.out.println(s.substring(i, j+1));
                        maxLength = s.substring(i, j+1).length();
                        maxes.add(maxLength);
                    }
                }
            }
        }
        if (!maxes.isEmpty()){
            maxLength = Collections.max(maxes);
        }
        return maxLength;
    }
}
