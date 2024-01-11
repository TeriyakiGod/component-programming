package ko;
    public class Math {
    public static final double PI = 3.14159265359;
    public static final double e = 2.71828;
    private static boolean divisionByZeroWarning = false;
    private static boolean warnIfNumberBiggerThan = false;
    private static boolean warnIfNumberSmallerThan = false;
    private static double warningNumberBig;
    private static double warningNumberSmall;
    private static boolean isVerbose;
    private static int verboseColor = 0;

    public static double add(double a, double b) {
        double result = a + b;
        checkWarnings(result);
        verbose(a, b, '+', result);
        return result;
    }

    public static double sub(double a, double b) {
        double result = a - b;
        checkWarnings(result);
        verbose(a, b, '-', result);
        return result;
    }

    public static double div(double a, double b) {
        if (isZero(b)) return 0;
        double result = a / b;
        checkWarnings(result);
        verbose(a, b, '/', result);
        return result;
    }

    public static double mul(double a, double b) {
        double result = a * b;
        checkWarnings(result);
        verbose(a, b, '*', result);
        return result;
    }

    public static void setDivisionByZeroWarning(boolean bool) {
        divisionByZeroWarning = bool;
    }

    public static void setWarnIfNumberBiggerThan(boolean bool, double number) {
        warnIfNumberBiggerThan = bool;
        warningNumberBig = number;
    }

    public static void setWarnIfNumberSmallerThan(boolean bool, double number) {
        warnIfNumberSmallerThan = bool;
        warningNumberSmall = number;
    }

    public static void setVerbose(boolean bool){
        isVerbose = bool;
    }

    public static void verbose(double a, double b, char operator, double result){
        if (isVerbose){
            String color = "\u001B[" + verboseColor;
            System.out.println(color + "m" + a + " " + operator + " " + b + " = " + result + "\u001B[0m");
        }
    }

    public static void setVerboseColor(int color){
        verboseColor = color;
    }

    private static void checkWarnings(double number){
        if (warnIfNumberBiggerThan && warningNumberBig < number){
            System.out.println("Warning result is bigger than: " + warningNumberBig);
        }
        if (warnIfNumberSmallerThan && warningNumberSmall > number){
            System.out.println("Warning result is smaller than: " + warningNumberSmall);
        }
    }

    private static boolean isZero(double number){
        if (number == 0) {
            if (divisionByZeroWarning) System.out.println("Division by 0!");
            return true;
        }
        return false;
    }
}