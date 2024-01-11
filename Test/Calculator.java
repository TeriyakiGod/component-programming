import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import ko.Math;

/**
 * The Calculator class represents a calculator window.
 * It extends the JFrame class and provides a graphical user interface for performing mathematical calculations.
 */
public class Calculator extends JFrame {
	//TODO: Report
	//TODO: Wykorzystaj kalkulator w innej aplikacji / Zaproponuj uzycie kalkulatora w innej aplikacji
	//TODO: 10 functions + 10 non-functions
	//TODO: testing 2 reczne 1 automatyczny
	private JTextField num1Field, num2Field;
	private JButton plusButton, minusButton, mulButton, divButton, piButton, eButton, clearButton, colorButton;
	private JTextField lastSelectedField = null;

	private JList<String> componentList;
    private DefaultListModel<String> listModel;
    private JComponent selectedComponent = null;

	/**
	 * Represents a window for a calculator application.
	 * The window contains various components such as text fields, buttons, and a list.
	 * Users can perform mathematical operations and change the background color of selected components.
	 */
	public Calculator() {
		super("Calculator");
		setSize(600, 400);
		// set location to center
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		num1Field = new JTextField(22);
		num2Field = new JTextField(22);
		plusButton = new JButton("+");
		minusButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		piButton = new JButton("pi");
		eButton = new JButton("e");
		clearButton = new JButton("clear");
		colorButton = new JButton("color");

		listModel = new DefaultListModel<>();
        componentList = new JList<>(listModel);

        listModel.addElement("num1Field");
        listModel.addElement("num2Field");
        listModel.addElement("plusButton");
        listModel.addElement("minusButton");
        listModel.addElement("mulButton");
        listModel.addElement("divButton");
        listModel.addElement("piButton");
        listModel.addElement("eButton");
        listModel.addElement("clearButton");
        listModel.addElement("colorButton");
		listModel.addElement("componentList");


		plusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1Text = num1Field.getText();
				String num2Text = num2Field.getText();
				double num1 = num1Text.isEmpty() ? 0 : Double.parseDouble(num1Text);
				double num2 = num2Text.isEmpty() ? 0 : Double.parseDouble(num2Text);
				double result = Math.add(num1, num2);
				JOptionPane.showMessageDialog(null, "Result: " + result);
			}
		});

		minusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1Text = num1Field.getText();
				String num2Text = num2Field.getText();
				double num1 = num1Text.isEmpty() ? 0 : Double.parseDouble(num1Text);
				double num2 = num2Text.isEmpty() ? 0 : Double.parseDouble(num2Text);
				double result = Math.sub(num1, num2);
				JOptionPane.showMessageDialog(null, "Result: " + result);
			}
		});

		mulButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1Text = num1Field.getText();
				String num2Text = num2Field.getText();
				double num1 = num1Text.isEmpty() ? 0 : Double.parseDouble(num1Text);
				double num2 = num2Text.isEmpty() ? 0 : Double.parseDouble(num2Text);
				double result = Math.mul(num1, num2);
				JOptionPane.showMessageDialog(null, "Result: " + result);
			}
		});

		divButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1Text = num1Field.getText();
				String num2Text = num2Field.getText();
				double num1 = num1Text.isEmpty() ? 0 : Double.parseDouble(num1Text);
				double num2 = num2Text.isEmpty() ? 0 : Double.parseDouble(num2Text);
				double result = Math.div(num1, num2);
				JOptionPane.showMessageDialog(null, "Result: " + result);
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
				}
			}
		});

		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lastSelectedField != null) {
					lastSelectedField.setText(String.valueOf(Math.e));
				}
			}
		});

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num1Field.setText("");
				num2Field.setText("");
			}
		});

		componentList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				switch (componentList.getSelectedValue()) {
					case "num1Field":
						selectedComponent = num1Field;
						break;
					case "num2Field":
						selectedComponent = num2Field;
						break;
					case "plusButton":
						selectedComponent = plusButton;
						break;
					case "minusButton":
						selectedComponent = minusButton;
						break;
					case "mulButton":
						selectedComponent = mulButton;
						break;
					case "divButton":
						selectedComponent = divButton;
						break;
					case "piButton":
						selectedComponent = piButton;
						break;
					case "eButton":
						selectedComponent = eButton;
						break;
					case "clearButton":
						selectedComponent = clearButton;
						break;
					case "colorButton":
						selectedComponent = colorButton;
						break;
					case "componentList":
						selectedComponent = componentList;
						break;
				}
			}
		});

        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedComponent != null) {
                    Color initialColor = Color.RED; // Initial color, change to what you want
                    Color chosenColor = JColorChooser.showDialog(null, "Choose a color", initialColor);
                    if (chosenColor != null) {
                        selectedComponent.setBackground(chosenColor);
                    }
                }
            }
        });

		add(num1Field);
		add(num2Field);
		add(plusButton);
		add(minusButton);
		add(mulButton);
		add(divButton);
		add(piButton);
		add(eButton);
		add(clearButton);
		add(colorButton);
		add(new JScrollPane(componentList));

		setVisible(true);
	}

	public static void main(String[] args) {
		new Calculator();
	}
}