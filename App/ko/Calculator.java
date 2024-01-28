package ko;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * The Calculator class represents a calculator window.
 * It extends the JFrame class and provides a graphical user interface for
 * performing mathematical calculations.
 */
public class Calculator extends JFrame {
	private JTextField num1Field, num2Field, warningNumberBigField, warningNumberSmallField;
	private JButton plusButton, minusButton, mulButton, divButton, piButton, eButton, clearButton, colorButton,
			statsButton, saveHistoryButton;
	private JTextField lastSelectedField = null;
	private JCheckBox divisionByWarningCheckbox, warnIfNumberBiggerThanCheckbox,
			warnIfNumberSmallerThanCheckbox;

	private JList<String> componentList;
	private DefaultListModel<String> listModel;
	private JComponent selectedComponent = null;

	private JTextArea outputField;

	private String statePath = "App/ko/calculator_state.ser";

	/**
	 * Represents a window for a calculator application.
	 * The window contains various components such as text fields, buttons, and a
	 * list.
	 * Users can perform mathematical operations and change the background color of
	 * selected components.
	 */
	public Calculator() {
		super();
		Stats.setStartTime();
		Math.setVerbose(true);
		Map<String, String> strings = Xml.readStringsFromXml("App/res/strings.xml");
		Stats.initStats();
		setTitle(strings.get("title"));
		setSize(700, 550);
		// set location to center
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		num1Field = new JTextField(10);
		num2Field = new JTextField(10);
		plusButton = new JButton(strings.get("plus"));
		minusButton = new JButton(strings.get("minus"));
		mulButton = new JButton(strings.get("multiply"));
		divButton = new JButton(strings.get("divide"));
		piButton = new JButton(strings.get("pi"));
		eButton = new JButton(strings.get("e"));
		clearButton = new JButton(strings.get("clear"));
		colorButton = new JButton(strings.get("color"));
		statsButton = new JButton(strings.get("stats"));
		outputField = new JTextArea("History:\n", 10, 50);
		outputField.setEditable(false);
		saveHistoryButton = new JButton(strings.get("saveHistory"));
		divisionByWarningCheckbox = new JCheckBox(strings.get("divisionByWarning"));
		warnIfNumberBiggerThanCheckbox = new JCheckBox(strings.get("warnIfNumberBiggerThan"));
		warnIfNumberSmallerThanCheckbox = new JCheckBox(strings.get("warnIfNumberSmallerThan"));
		warningNumberBigField = new JTextField(10);
		warningNumberSmallField = new JTextField(10);

		PrintStream printStream = new PrintStream(new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				String currentText = outputField.getText();
				String newText = currentText + String.valueOf((char) b);
				outputField.setText(newText);
			}
		});

		System.setOut(printStream);

		saveHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = outputField.getText();
				String fileName = JOptionPane.showInputDialog(null, "Enter the filename:");
				if (fileName != null && !fileName.isEmpty()) {
					try {
						FileWriter fileWriter = new FileWriter(fileName);
						fileWriter.write(output);
						fileWriter.close();
						JOptionPane.showMessageDialog(null, "Output saved to " + fileName);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		divisionByWarningCheckbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Math.setDivisionByZeroWarning(divisionByWarningCheckbox.isSelected());
			}
		});

		warnIfNumberBiggerThanCheckbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = warningNumberBigField.getText();
				double value = (text == null || text.isEmpty()) ? 0 : Double.parseDouble(text);
				Math.setWarnIfNumberBiggerThan(warnIfNumberBiggerThanCheckbox.isSelected(), value);
			}
		});

		warnIfNumberSmallerThanCheckbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = warningNumberSmallField.getText();
				double value = (text == null || text.isEmpty()) ? 0 : Double.parseDouble(text);
				Math.setWarnIfNumberSmallerThan(warnIfNumberSmallerThanCheckbox.isSelected(), value);
			}
		});

		warningNumberBigField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String text = warningNumberBigField.getText();
				double value = (text == null || text.isEmpty()) ? 0 : Double.parseDouble(text);
				Math.setWarnIfNumberBiggerThan(warnIfNumberBiggerThanCheckbox.isSelected(), value);
			}
		});

		warningNumberSmallField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String text = warningNumberSmallField.getText();
				double value = (text == null || text.isEmpty()) ? 0 : Double.parseDouble(text);
				Math.setWarnIfNumberSmallerThan(warnIfNumberSmallerThanCheckbox.isSelected(), value);
			}
		});

		plusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1Text = num1Field.getText();
				String num2Text = num2Field.getText();
				double num1 = num1Text.isEmpty() ? 0 : Double.parseDouble(num1Text);
				double num2 = num2Text.isEmpty() ? 0 : Double.parseDouble(num2Text);
				Math.setA(num2);
				Math.setB(num1);
				Math.add();
				double result = Math.getResult();
				JOptionPane.showMessageDialog(null, strings.get("resultLabel") + result);
				Stats.checkLargestNumber(result);
				Stats.checkSmallestNumber(result);
				Stats.bumpAdditions();
			}
		});

		minusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1Text = num1Field.getText();
				String num2Text = num2Field.getText();
				double num1 = num1Text.isEmpty() ? 0 : Double.parseDouble(num1Text);
				double num2 = num2Text.isEmpty() ? 0 : Double.parseDouble(num2Text);
				Math.setA(num2);
				Math.setB(num1);
				Math.sub();
				double result = Math.getResult();
				JOptionPane.showMessageDialog(null, strings.get("resultLabel") + result);
				Stats.checkLargestNumber(result);
				Stats.checkSmallestNumber(result);
				Stats.bumpSubtractions();
			}
		});

		mulButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1Text = num1Field.getText();
				String num2Text = num2Field.getText();
				double num1 = num1Text.isEmpty() ? 0 : Double.parseDouble(num1Text);
				double num2 = num2Text.isEmpty() ? 0 : Double.parseDouble(num2Text);
				Math.setA(num2);
				Math.setB(num1);
				Math.mul();
				double result = Math.getResult();
				JOptionPane.showMessageDialog(null, strings.get("resultLabel") + result);
				Stats.checkLargestNumber(result);
				Stats.checkSmallestNumber(result);
				Stats.bumpMultiplications();
			}
		});

		divButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1Text = num1Field.getText();
				String num2Text = num2Field.getText();
				double num1 = num1Text.isEmpty() ? 0 : Double.parseDouble(num1Text);
				double num2 = num2Text.isEmpty() ? 0 : Double.parseDouble(num2Text);
				if (num2 == 0 && divisionByWarningCheckbox.isSelected()) {
					JOptionPane.showMessageDialog(null, strings.get("divisionByWarning"));
					Stats.bumpDivisionsByZero();
					Stats.bumpDivisions();
					return;
				}
				Math.setA(num2);
				Math.setB(num1);
				Math.div();
				double result = Math.getResult();
				JOptionPane.showMessageDialog(null, strings.get("resultLabel") + result);
				Stats.checkLargestNumber(result);
				Stats.checkSmallestNumber(result);
				Stats.bumpDivisions();
			}
		});

		num1Field.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				lastSelectedField = num1Field;
			}
		});

		num2Field.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				lastSelectedField = num2Field;
			}
		});

		piButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lastSelectedField != null) {
					lastSelectedField.setText(String.valueOf(Math.PI));
					Stats.bumpPi();
				}
			}
		});

		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lastSelectedField != null) {
					lastSelectedField.setText(String.valueOf(Math.e));
					Stats.bumpE();
				}
			}
		});

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num1Field.setText("");
				num2Field.setText("");
			}
		});

		statsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stats.showStats();
			}
		});

		listModel = new DefaultListModel<>();
		componentList = new JList<>(listModel);
		Map<String, JComponent> componentMap = new HashMap<>();

		String[] componentNames = {
				"num1Field", "num2Field", "plusButton", "minusButton", "mulButton", "divButton",
				"piButton", "eButton", "clearButton", "colorButton", "componentList", "outputField",
				"divisionByWarningCheckbox", "warnIfNumberBiggerThanCheckbox",
				"warnIfNumberSmallerThanCheckbox", "warningNumberBigField", "warningNumberSmallField"
		};

		try {
			for (String componentName : componentNames) {
				listModel.addElement(componentName);
				componentMap.put(componentName, (JComponent) getClass().getDeclaredField(componentName).get(this));
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			// Handle the exceptions here
			e.printStackTrace();
		}

		componentList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectedComponent = componentMap.get(componentList.getSelectedValue());
			}
		});

		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedComponent != null) {
					Color initialColor = Color.RED; // Initial color, change to what you want
					Color chosenColor = JColorChooser.showDialog(null, strings.get("chooseColor"), initialColor);
					if (chosenColor != null) {
						selectedComponent.setBackground(chosenColor);
					}
				}
			}
		});
		setLayout(new GridLayout(0, 2));

		add(num1Field);
		add(num2Field);
		add(plusButton);
		add(minusButton);
		add(mulButton);
		add(divButton);
		add(piButton);
		add(eButton);
		add(new JScrollPane(componentList));
		add(colorButton);
		add(statsButton);
		add(clearButton);
		add(saveHistoryButton);
		add(new JScrollPane(outputField));
		add(warnIfNumberBiggerThanCheckbox);
		add(warningNumberBigField);
		add(warnIfNumberSmallerThanCheckbox);
		add(warningNumberSmallField);

		add(divisionByWarningCheckbox);

		JComponent author = new JLabel("Kacper Ochnik 2024");
		((JLabel) author).setHorizontalAlignment(SwingConstants.CENTER);

		add(author);

		setResizable(false);

		setVisible(true);
	}
}