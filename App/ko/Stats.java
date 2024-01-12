package ko;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class Stats {
    private static double largestNumber = 0;
    private static double smallestNumber = 0;
    private static double divisionsByZero = 0;
    private static double additions = 0;
    private static double subtractions = 0;
    private static double multiplications = 0;
    private static double divisions = 0;
    private static double pi = 0;
    private static double e = 0;
    private static double startTime;
    private static JDialog stats = new JDialog();

    private static JLabel largestNumberLabel = new JLabel();
    private static JLabel smallestNumberLabel = new JLabel();
    private static JLabel divisionsByZeroLabel = new JLabel();
    private static JLabel additionsLabel = new JLabel();
    private static JLabel subtractionsLabel = new JLabel();
    private static JLabel multiplicationsLabel = new JLabel();
    private static JLabel divisionsLabel = new JLabel();
    private static JLabel piLabel = new JLabel();
    private static JLabel eLabel = new JLabel();
    private static JLabel timeLabel = new JLabel();

    public static double getLargestNumber() {
        return largestNumber;
    }

    public static void checkLargestNumber(double number) {
        if (number > largestNumber) {
            largestNumber = number;
            largestNumberLabel.setText(String.valueOf(largestNumber));
            timeLabel.setText(String.valueOf(getTime()));
            stats.repaint();
        }
    }

    public static double getSmallestNumber() {
        return smallestNumber;
    }

    public static void checkSmallestNumber(double number) {
        if (number < smallestNumber) {
            smallestNumber = number;
            smallestNumberLabel.setText(String.valueOf(smallestNumber));
            timeLabel.setText(String.valueOf(getTime()));
            stats.repaint();
        }
    }

    public static double getDivisionsByZero() {
        return divisionsByZero;
    }

    public static void bumpDivisionsByZero() {
        divisionsByZero++;
        divisionsByZeroLabel.setText(String.valueOf(divisionsByZero));
        timeLabel.setText(String.valueOf(getTime()));
        stats.repaint();
    }

    public static double getAdditions() {
        return additions;
    }

    public static void bumpAdditions() {
        additions++;
        additionsLabel.setText(String.valueOf(additions));
        timeLabel.setText(String.valueOf(getTime()));
        stats.repaint();
    }

    public static double getSubtractions() {
        return subtractions;
    }

    public static void bumpSubtractions() {
        subtractions++;
        subtractionsLabel.setText(String.valueOf(subtractions));
        timeLabel.setText(String.valueOf(getTime()));
        stats.repaint();
    }

    public static double getMultiplications() {
        return multiplications;
    }

    public static void bumpMultiplications() {
        multiplications++;
        multiplicationsLabel.setText(String.valueOf(multiplications));
        timeLabel.setText(String.valueOf(getTime()));
        stats.repaint();
    }

    public static double getDivisions() {
        return divisions;
    }

    public static void bumpDivisions() {
        divisions++;
        divisionsLabel.setText(String.valueOf(divisions));
        timeLabel.setText(String.valueOf(getTime()));
        stats.repaint();
    }

    public static double getPi() {
        return pi;
    }

    public static void bumpPi() {
        pi++;
        piLabel.setText(String.valueOf(pi));
        timeLabel.setText(String.valueOf(getTime()));
        stats.repaint();
    }

    public static double getE() {
        return e;
    }

    public static void bumpE() {
        e++;
        eLabel.setText(String.valueOf(e));
        timeLabel.setText(String.valueOf(getTime()));
        stats.repaint();
    }

    public static double getTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public static void setStartTime() {
        startTime = System.currentTimeMillis();
    }

    public static void initStats() {
        stats.setTitle("Stats");
        stats.setSize(400, 300);
        stats.setLocationRelativeTo(null);
        stats.setVisible(false);
        stats.setLayout(new GridLayout(11, 2));
        stats.add(new javax.swing.JLabel("Largest number:"));
        stats.add(largestNumberLabel);
        stats.add(new javax.swing.JLabel("Smallest number:"));
        stats.add(smallestNumberLabel);
        stats.add(new javax.swing.JLabel("Divisions by zero:"));
        stats.add(divisionsByZeroLabel);
        stats.add(new javax.swing.JLabel("Additions:"));
        stats.add(additionsLabel);
        stats.add(new javax.swing.JLabel("Subtractions:"));
        stats.add(subtractionsLabel);
        stats.add(new javax.swing.JLabel("Multiplications:"));
        stats.add(multiplicationsLabel);
        stats.add(new javax.swing.JLabel("Divisions:"));
        stats.add(divisionsLabel);
        stats.add(new javax.swing.JLabel("Pi:"));
        stats.add(piLabel);
        stats.add(new javax.swing.JLabel("E:"));
        stats.add(eLabel);
        stats.add(new javax.swing.JLabel("Time in app:"));
        stats.add(timeLabel);

        largestNumberLabel.setText(String.valueOf(largestNumber));
        smallestNumberLabel.setText(String.valueOf(smallestNumber));
        divisionsByZeroLabel.setText(String.valueOf(divisionsByZero));
        additionsLabel.setText(String.valueOf(additions));
        subtractionsLabel.setText(String.valueOf(subtractions));
        multiplicationsLabel.setText(String.valueOf(multiplications));
        divisionsLabel.setText(String.valueOf(divisions));
        piLabel.setText(String.valueOf(pi));
        eLabel.setText(String.valueOf(e));
        timeLabel.setText(String.valueOf(getTime()));

        stats.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }

    public static void showStats() {
        stats.setVisible(true);
        timeLabel.setText(String.valueOf(getTime()));
        stats.repaint();
    }

    public static void hideStats() {
        stats.setVisible(false);
    }

    public static void repaintStats() {
        timeLabel.setText(String.valueOf(getTime()));
        stats.repaint();
    }
}
