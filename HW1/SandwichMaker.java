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
        boolean flagForDetectingOthers = true;
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
                    if (i == 0){
                        curMaxSub = s.substring(i, j);
                        System.out.println("FUUUCk");
                        System.out.println("i " + i);
                        System.out.println("j " + (j-1));
                        System.out.println("cur: " + curMaxSub);
                        System.out.println("prev: " + prevMaxSub);
                        flag = j - 1;
                        System.out.println("flag: " + flag);
                        System.out.println("----");
                        maxLength = curMaxSub.length();
                    }
                    else if (flag + 1 == i){
//                      two valid substrings next to each other
                        System.out.println("entered else if");
                        curMaxSub = s.substring(i, j);
                        curMaxSub = prevMaxSub + curMaxSub;
                        System.out.println("i " + i);
                        flag = j - 1;
                        System.out.println("j " + (j-1));
                        System.out.println("cur: " + curMaxSub);
                        System.out.println("prev: " + prevMaxSub);
                        System.out.println("flag: " + flag);
                        System.out.println("----");
                        maxLength = curMaxSub.length();
                    }else {
                        System.out.println("entered else");
                        System.out.println("i " + i);
                        System.out.println(s.substring(0, i));
                        if (s.substring(0, i).contains("b")){
                            System.out.println(s.substring(0, i));
                            System.out.println("Ssssssss");
                            for (int k = flag + 1; k < i; k++) {
                                System.out.println("k " + k);
                                if (s.charAt(k) == 'm' || s.charAt(k) == 'b'){
                                    flagForDetectingOthers = false;
                                    System.out.println("break");
                                    break;
                                }
                            }
                        }

//                      bsmppbssm -> to detect the pp
                        if (s.substring(0, i).contains("b")){
                            if (flagForDetectingOthers){
                                curMaxSub = curMaxSub + s.substring(flag + 1, i);
                                System.out.println("used flagForDetectingOthers");
                                System.out.println(curMaxSub);
                            }
                        }

                        flag = j - 1;
                        prevMaxSub = curMaxSub;
                        curMaxSub = prevMaxSub + s.substring(i, j);
                        System.out.println("i " + i);
                        System.out.println("j " + (j-1));
                        System.out.println("cur: " + curMaxSub);
                        System.out.println("prev: " + prevMaxSub);
                        System.out.println("flag: " + flag);
                        System.out.println("----");
                        maxLength = curMaxSub.length();
                    }
                    System.out.println("max len: " + maxLength);
                    maxes.add(maxLength);
                }
            }
        }
        System.out.println(maxes);
        if (!maxes.isEmpty()) {
            maxLength = Collections.max(maxes);
        }
        return maxLength;
    }
}
