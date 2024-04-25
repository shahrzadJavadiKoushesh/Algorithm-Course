import java.util.*;

public class StoD {
    static int findLIS(Vector<Integer> nums) {
        int n = nums.size();
        List<Integer> ans = new ArrayList<>();

        ans.add(nums.get(0));

        for (int i = 1; i < n; i++) {
            if (nums.get(i) > ans.get(ans.size() - 1)) {
                ans.add(nums.get(i));
            } else {

                int low = Collections.binarySearch(ans, nums.get(i));

                if (low < 0) {
                    low = -(low + 1);
                }
                ans.set(low, nums.get(i));
            }
        }

        return ans.size() + 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int booster;
        Vector<Integer> maps = new Vector<>();
        for (int i = 0; i < n; i++) {
            booster = scanner.nextInt();
            if (i != 0 && booster != 1){
                maps.add(booster);
            }
        }

        System.out.println(2 * n - findLIS(maps));
    }
}