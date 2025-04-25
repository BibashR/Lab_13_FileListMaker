import java.util.Scanner;

public class SafeInput {

    /**
     * Prompts the user for a non-empty string input.
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString;
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.isEmpty());
        return retString;
    }

    // Prompts the user for an integer input.

    public static int getInt(Scanner pipe, String prompt) {
        int value;
        while (true) {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                pipe.nextLine(); // Clear buffer
                return value;
            } else {
                System.out.println("Invalid input. Enter an integer.");
                pipe.nextLine(); // Discard invalid input
            }
        }
    }

    // Prompts the user for a double input.

    public static double getDouble(Scanner pipe, String prompt) {
        double value;
        while (true) {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                pipe.nextLine(); // Clear buffer
                return value;
            } else {
                System.out.println("Invalid input. Enter a decimal number.");
                pipe.nextLine(); // Discard invalid input
            }
        }
    }

    // Prompts the user for an integer input within a specified range.

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int value;
        do {
            value = getInt(pipe, prompt + " [" + low + "-" + high + "]");
        } while (value < low || value > high);
        return value;
    }

    // Prompts the user for a double input within a specified range.

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double value;
        do {
            value = getDouble(pipe, prompt + " [" + low + "-" + high + "]");
        } while (value < low || value > high);
        return value;
    }

    // Prompts the user for a Yes or No confirmation.

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String response;
        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine().trim().toUpperCase();
            if (!response.equals("Y") && !response.equals("N")) {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        } while (!response.equals("Y") && !response.equals("N"));
        return response.equals("Y");
    }

    // Prompts the user for a string input that matches a given regular expression pattern.

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String response;
        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (!response.matches(regEx)) {
                System.out.println("Invalid format. Try again.");
            }
        } while (!response.matches(regEx));
        return response;
    }

    // Displays a formatted header with a centered message.

    public static void prettyHeader(String msg) {
        int width = 60;
        int padding = (width - msg.length() - 6) / 2;
        System.out.println("*".repeat(width));
        System.out.println("***" + " ".repeat(padding) + msg + " ".repeat(padding) + "***");
        System.out.println("*".repeat(width));
    }
}
