import java.util.Scanner;
import java.util.Stack;

public class Crab
{
    static int findMaxLen(String str) {

        Stack<Integer> stk = new Stack<>();

        int result = 0;
        int flag = 0;
        int x = 0;
        int lasti = 0;
        for (int i = 0; i < str.length(); i++) {
            System.out.println("i: " + i);
            if (str.charAt(i) == 'b') {
                if (stk.empty()) {
                    stk.push(i - 1);
                }
                stk.push(i);
                System.out.println("detected b");
                System.out.println(stk);
            }

            else if (str.charAt(i) == 'm') {
                System.out.println("detected m");
                if(!stk.empty()) {
                    flag = stk.peek();
                    stk.pop();
                    System.out.println("stack after popping: "  +stk);
                }
                if (!stk.empty()) {
                    System.out.println("flag: " + flag);
                    if (lasti + 1 == flag){
                        System.out.println("chasbide");
                        x = i - flag + 1;
                        result = result + x;
                        System.out.println(result);
                        System.out.println("goftam ke chasbide");
                    }
                    x = i - flag + 1;
                    System.out.println("x: " + x);
                    if (x >= result){
                        lasti = i;
                        result = x;
                        System.out.println("last i changed to: " + lasti);
                    }

                    System.out.println("result: " + result);
                    System.out.println("x: " + x);
                }

            }
        }

        return result;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println(findMaxLen(input));
    }
}
