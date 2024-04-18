import java.util.*;

public class Z {
    private static final int MOD = 1000000007;
    public static long numDecodings(String s) {

        int len = s.length();
        if (len == 0 || s.charAt(0) == '0') {
            return 0;
        }
        char preChar = s.charAt(0);
        long pre = 1;

        long cur;
        if (preChar == '*') {
            cur = 9;
        } else {
            cur = 1;
        }

        for (int i = 1; i < len; i++) {
            char curChar = s.charAt(i);
            if (preChar == '0' && curChar == '0') {
                return 0;
            }
            long ways = 0;
            if (curChar != '0') {
                if (curChar == '*') {
                    ways += cur * 9;
                } else {
                    ways += cur;
                }
            }

            if (preChar == '*') {
                if (curChar == '*') {
                    ways += 15 * pre;
                } else if (curChar <= '6') {
                    ways += 2 * pre;
                } else {
                    ways += pre;
                }
            } else if (preChar == '2') {
                if (curChar == '*') {
                    ways += 6 * pre;
                } else if (curChar <= '6') {
                    ways += pre;
                }
            } else if (preChar == '1') {
                if (curChar == '*') {
                    ways += pre * 9;
                } else {
                    ways += pre;
                }
            }

            pre = cur;
            cur = ways % MOD;
            preChar = curChar;
        }
        return cur;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long ans = 1;
        boolean[] data = new boolean[n];
        for (int i = 0; i < n; i++) {
            String str = scanner.next();
//            System.out.println(numDecodings(str));
            long decodings = numDecodings(str);
            if (decodings != 0){
                data[i] = true;
                ans = (ans * decodings) % MOD;
            } else {
                data[i] = false;
            }
        }

        boolean wayFound = false;
        for (boolean item : data) {
            if (item){
                System.out.print(ans % MOD);
                wayFound = true;
                break;
            }
        }
        if (!wayFound){
            System.out.print('1');
        }
    }
}