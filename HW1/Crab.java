import java.util.Scanner;
import java.util.Stack;

public class Crab {
    static int findMaxLen(String str) {

        int mCount = 0, bCount = 0, othersCount = 0, maxLen = 0, temp;
        for (int i = str.indexOf('b'); i < str.lastIndexOf('m'); i++) {
            if (str.charAt(i) == 'b'){
                bCount++;
            }
            else if (str.charAt(i) == 'm'){
                mCount++;
            }
            else if ((mCount > 0 || bCount > 0) && (str.charAt(i) == 's' || str.charAt(i) == 'p' || str.charAt(i) == 't')) {
                othersCount++;
            }
            if (bCount == mCount && str.charAt(i) == 'm'){
                temp = mCount + bCount + othersCount;
                if (temp > maxLen){
                    maxLen = temp;
                }
            }
            if (mCount > bCount){
                mCount = 0;
                bCount = 0;
                othersCount = 0;
            }
        }
        bCount = 0;
        mCount = 0;
        othersCount = 0;

        for (int i = str.lastIndexOf('m'); i >= str.indexOf('b') ; i--) {
            if (str.charAt(i) == 'm'){
                mCount++;
            }
            else if(str.charAt(i) == 'b'){
                bCount++;
            }
            else if ((mCount > 0 || bCount > 0) && (str.charAt(i) == 's' || str.charAt(i) == 'p' || str.charAt(i) == 't')){
                othersCount++;
            }
            if (mCount == bCount && str.charAt(i) == 'b'){
                temp = mCount + bCount + othersCount;
                if (temp > maxLen){
                    maxLen = temp;
                }
            }
            if (bCount > mCount){
                mCount = 0;
                bCount = 0;
                othersCount = 0;
            }
        }
        return maxLen;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println(findMaxLen(input));
    }
}

