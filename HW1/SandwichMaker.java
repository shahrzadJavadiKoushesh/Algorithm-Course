public class SandwichMaker {
    public static String makeSandwich(String input) {
        StringBuilder sandwich = new StringBuilder();
        int breadCount = 0; // Counter for breads encountered
        int meatCount = 0;  // Counter for meats encountered
        boolean started = false; // Flag to track if sandwich making has started

        for (char c : input.toCharArray()) {
            if (c == 'b') {
                breadCount++;
            }




//            if (c == 'b') {
//                if (!started) {
//                    started = true;
//                    sandwich.append(c);
//                } else if (breadCount > meatCount) {
//                    sandwich.append(c);
//                }
//                breadCount++;
//            } else if (c == 'm') {
//                if (started && meatCount < breadCount) {
//                    sandwich.append(c);
//                    meatCount++;
//                }
//            } else if (c == 't' || c == 'p' || c == 's') {
//                if (started && meatCount < breadCount) {
//                    sandwich.append(c);
//                }
//            }
        }

        // Ensure the sandwich ends with meat
        if (sandwich.length() > 0 && sandwich.charAt(sandwich.length() - 1) != 'm') {
            sandwich.deleteCharAt(sandwich.length() - 1); // Remove the last character if it's not meat
        }

        return sandwich.toString();
    }

    public static void main(String[] args) {
        String input = "bbsmbms";
        String output = makeSandwich(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
    }
}
