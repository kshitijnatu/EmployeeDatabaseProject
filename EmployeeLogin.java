package graphicalUserInterfacePlusDBProjects;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EmployeeLogin {

	public static void main(String[] args) {
		LoginScreen loginScreen = new LoginScreen();
		loginScreen.execute();
	}
}

class LoginScreen implements ActionListener {

	private JFrame frame;
	private JPanel outerPanel, innerPanel;
	private JLabel loginTitle, userNameLabel, passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton login, clear;

	public LoginScreen() {
		loginTitle = setLabel("Company Name", 140, 0, 240, 50, Color.BLACK, "Times New Roman", 1, 30);
		userNameLabel = setLabel("User Name", 20, 50, 100, 50, Color.WHITE, "Times New Roman", 1, 20);
		passwordLabel = setLabel("Password", 20, 100, 100, 50, Color.WHITE, "Times New Roman", 1, 20);

		usernameField = setTextField(250, 60, 190, 30);

		passwordField = setPasswordField(250, 110, 190, 30);

		login = setButton("Login", 250, 120 + 60, 100, 40);
		clear = setButton("Clear", 360, 180, 100, 40);
	}

	public void execute() {
		outerPanel = setPanel(0, 50, 500, 500, Color.gray);
		outerPanel.add(userNameLabel);
		outerPanel.add(passwordLabel);
		outerPanel.add(usernameField);
		outerPanel.add(passwordField);
		outerPanel.add(login);
		outerPanel.add(clear);

		innerPanel = setPanel(10, 20, 75, 75, Color.cyan);
		innerPanel.add(loginTitle);

		frame = setFrame("Company Name", 500, 500);
		frame.setLocation(500, 200);
		frame.add(outerPanel);
		frame.add(innerPanel);
	}

	public JFrame setFrame(String screenTitle, int width, int height) {
		JFrame frame = new JFrame(screenTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setResizable(false);
		return frame;
	}

	public JFrame setFrame(String titleScreen) {
		JFrame frame = new JFrame(titleScreen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		return frame;
	}

	public JPanel setPanel(int x, int y, int width, int height, Color color) {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		return panel;
	}

	public JPanel setPanel(Color color) {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 1000);
		return panel;
	}

	public JLabel setLabel(String title, int x, int y, int width, int height, Color color, String fontName, int style,
			int size) {
		JLabel label = new JLabel(title);
		Font font = setFont(fontName, style, size);
		label.setBounds(x, y, width, height);
		label.setForeground(color);
		label.setFont(font);
		return label;
	}

	public JTextField setTextField(int x, int y, int width, int height) {
		JTextField tf = new JTextField();
		tf.setBounds(x, y, width, height);
		tf.setForeground(Color.BLACK);
		return tf;
	}

	public JPasswordField setPasswordField(int x, int y, int width, int height) {
		JPasswordField pf = new JPasswordField();
		pf.setBounds(x, y, width, height);
		pf.setBackground(Color.WHITE);
		pf.setForeground(Color.BLACK);
		return pf;
	}

	public JButton setButton(String title, int x, int y, int width, int height) {
		JButton button = new JButton(title);
		button.setBounds(x, y, width, height);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.addActionListener(this);
		return button;
	}

	public JTextArea setTextArea(int x, int y, int width, int height) {
		JTextArea textArea = new JTextArea();
		textArea.setBounds(x, y, width, height);
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		return textArea;
	}

	public JComboBox<String> setBox(int x, int y, int len, int width, String[] choices) {
		JComboBox<String> myBox = new JComboBox<String>(choices);
		myBox.setBounds(x, y, len, width);
		myBox.addActionListener(this);
		return myBox;
	}

	public Font setFont(String fontName, int style, int size) {
		Font font = new Font(fontName, style, size);
		return font;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			char[] adminPassword = { '1', '2', '3', '4' };
			char[] passwordEnterd = passwordField.getPassword();

			if (!usernameField.getText().equals("Admin")) {
				System.out.println("Username is wrong. Please enter it again");
			} else {
				if (adminPassword.length != passwordEnterd.length) {
					System.out.println("Password is wrong. Please enter it again");
				} else {
					for (int i = 0; i < adminPassword.length; i++) {
						if (adminPassword[i] != passwordEnterd[i]) {
							System.out.println("Password is wrong. Please enter it again");
							break;
						}
					}
					System.out.println("Login successful");
					EmployeeRecords employeeRecords = new EmployeeRecords();
					employeeRecords.execute();
					frame.dispose();
				}
			}

		} else if (e.getSource() == clear) {
			usernameField.setText(null);
			passwordField.setText(null);
		}
	}
}
