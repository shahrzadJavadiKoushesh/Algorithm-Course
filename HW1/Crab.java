import java.util.Scanner;

import java.util.Stack;


public class Crab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println(findMaxLen(input));
    }

    public static int findMaxLen(String str) {

        int n = str.length();
        Stack<Integer> stk = new Stack<>();
        int firstBdetected = str.indexOf('b');
        stk.push(-1);

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == 'b')
                stk.push(i);

            else if (str.charAt(i) == 'm'){
                if (!stk.empty())

                    stk.pop();

                if (!stk.empty())

                    result = Math.max(result, i - stk.peek() - firstBdetected);

                else

                    stk.push(i);
            }

        }
        return result;

    }
}
