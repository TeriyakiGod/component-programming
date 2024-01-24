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
    private static double a;
    private static double b;
    private static double result;

    public static double getA() {
        return a;
    }

    public static void setA(double value) {
        a = value;
    }

    public static double getB() {
        return b;
    }

    public static void setB(double value) {
        b = value;
    }

    public static double getResult() {
        return result;
    }

    public static void add() {
        result = a + b;
        checkWarnings(result);
        verbose(a, b, '+', result);
    }

    public static void sub() {
        result = a - b;
        checkWarnings(result);
        verbose(a, b, '-', result);
    }

    public static void div() {
        if (isZero(b))
            return;
        result = a / b;
        checkWarnings(result);
        verbose(a, b, '/', result);
    }

    public static void mul() {
        result = a * b;
        checkWarnings(result);
        verbose(a, b, '*', result);
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

    public static void setVerbose(boolean bool) {
        isVerbose = bool;
    }

    public static void verbose(double a, double b, char operator, double result) {
        if (isVerbose) {
            System.out.println(a + " " + operator + " " + b + " = " + result);
        }
    }

    private static void checkWarnings(double number) {
        if (warnIfNumberBiggerThan && warningNumberBig < number) {
            System.out.println("Warning result is bigger than: " + warningNumberBig);
        }
        if (warnIfNumberSmallerThan && warningNumberSmall > number) {
            System.out.println("Warning result is smaller than: " + warningNumberSmall);
        }
    }

    private static boolean isZero(double number) {
        if (number == 0) {
            if (divisionByZeroWarning)
                System.out.println("Division by 0!");
            return true;
        }
        return false;
    }
}