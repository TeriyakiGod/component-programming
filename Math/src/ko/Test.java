package ko;

public class Test {
    public static void main(String[] args) {
        System.out.println("");
        Math.setVerbose(true);
        Math.setDivisionByZeroWarning(true);
        Math.setWarnIfNumberBiggerThan(true, 10);
        Math.setWarnIfNumberSmallerThan(true, 0);
        Math.setA(0);
        Math.setB(0);
        Math.add();
        Math.sub();
        Math.div();
        Math.mul();
        Math.setA(1);
        Math.setB(1);
        Math.add();
        Math.sub();
        Math.div();
        Math.mul();
        Math.setA(-2);
        Math.setB(2);
        Math.add();
        Math.sub();
        Math.div();
        Math.mul();
        Math.setA(-3);
        Math.setB(-5);
        Math.add();
        Math.sub();
        Math.div();
        Math.mul();
    }

}