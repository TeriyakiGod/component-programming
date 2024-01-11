package ko;

public class Test {
    public static void main(String[] args) {
        System.out.println("");
        Math.setVerbose(true);
        Math.setDivisionByZeroWarning(true);
        Math.setWarnIfNumberBiggerThan(true, 10);
        Math.setWarnIfNumberSmallerThan(true, 0);
        Math.add(Math.PI, 10);
        Math.sub(0, Math.e);
        Math.div(2, 0);
        Math.mul(2, 2);
        Math.div(2, 2);
    }
}
