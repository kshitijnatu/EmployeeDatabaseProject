package graphicalUserInterfacePlusDBProjects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public final class ViewEmployees extends LoginScreen implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JLabel title, salaryLabel, emailLabel, usernameLabel, passwordLabel, mobileNumLabel;
	private JTextField salary, email, username, password, mobileNum;
	private JButton backButton;
	private JComboBox<String> nameBox;
	private Font font = setFont("Arial", Font.BOLD, 20);

	public ViewEmployees() {
		title = setLabel("View Employees", 600, 0, 350, 100, Color.BLACK, "Times New Roman", Font.ITALIC, 50);
		salaryLabel = setLabel("Salary: ", 450, 200, title.getWidth(), title.getHeight(), Color.BLACK,
				"Times New Roman", Font.ITALIC, 50);
		emailLabel = setLabel("Email: ", salaryLabel.getX(), salaryLabel.getY() + 100, salaryLabel.getWidth(),
				salaryLabel.getHeight(), Color.BLACK, "Times New Roman", Font.ITALIC, 50);
		usernameLabel = setLabel("Username: ", salaryLabel.getX(), salaryLabel.getY() + 200, title.getWidth(),
				title.getHeight(), Color.BLACK, "Times New Roman", Font.ITALIC, 50);
		passwordLabel = setLabel("Password: ", salaryLabel.getX(), salaryLabel.getY() + 300, salaryLabel.getWidth(),
				salaryLabel.getHeight(), Color.BLACK, "Times New Roman", Font.ITALIC, 50);
		mobileNumLabel = setLabel("Mobile Number: ", salaryLabel.getX(), salaryLabel.getY() + 400,
				salaryLabel.getWidth(), salaryLabel.getHeight(), Color.BLACK, "Times New Roman", Font.ITALIC, 50);

		salary = setTextField(salaryLabel.getX() + 400, salaryLabel.getY() + 30, 300, 50);
		salary.setEditable(false);
		salary.setFont(font);
		salary.setHorizontalAlignment(JTextField.CENTER);

		email = setTextField(emailLabel.getX() + 400, emailLabel.getY() + 30, salary.getWidth(), salary.getHeight());
		email.setEditable(false);
		email.setFont(font);
		email.setHorizontalAlignment(JTextField.CENTER);

		username = setTextField(usernameLabel.getX() + 400, usernameLabel.getY() + 30, salary.getWidth(),
				salary.getHeight());
		username.setEditable(false);
		username.setFont(font);
		username.setHorizontalAlignment(JTextField.CENTER);

		password = setTextField(passwordLabel.getX() + 400, passwordLabel.getY() + 30, salary.getWidth(),
				salary.getHeight());
		password.setEditable(false);
		password.setFont(font);
		password.setHorizontalAlignment(JTextField.CENTER);

		mobileNum = setTextField(mobileNumLabel.getX() + 400, mobileNumLabel.getY() + 30, salary.getWidth(),
				salary.getHeight());
		mobileNum.setEditable(false);
		mobileNum.setFont(font);
		mobileNum.setHorizontalAlignment(JTextField.CENTER);

		ArrayList<String> names = new ArrayList<>();
		String[] choices = null;
		String databaseURL = "jdbc:ucanaccess://EmployeeRecords.accdb";
		try {
			Connection conn = DriverManager.getConnection(databaseURL);
			String sql = "SELECT * FROM Employees";

			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				names.add(rs.getString("First_Name") + " " + rs.getString("Last_Name"));
			}
			choices = new String[names.size()];

			for (int i = 0; i < names.size(); i++) {
				choices[i] = names.get(i);
			}
			//names.clear();
			names = null;
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getCause());
		}
		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		nameBox = setBox(600, 125, 300, 50, choices);
		nameBox.insertItemAt("Please choose a name", 0);
		nameBox.setSelectedIndex(0);
		nameBox.setRenderer(listRenderer);
		nameBox.setFont(new Font("Times New Roman", Font.BOLD, 20));

		backButton = setButton("Back", nameBox.getX(), nameBox.getY() + 575, 300, 100);
		backButton.setFont(new Font("Arial", Font.BOLD, 30));
	}

	@Override
	public void execute() {
		panel = setPanel(new Color(252, 178, 158));
		panel.add(title);
		panel.add(salaryLabel);
		panel.add(emailLabel);
		panel.add(usernameLabel);
		panel.add(passwordLabel);
		panel.add(mobileNumLabel);
		panel.add(salary);
		panel.add(email);
		panel.add(username);
		panel.add(password);
		panel.add(mobileNum);
		panel.add(nameBox);
		panel.add(backButton);

		frame = setFrame("Company Name");
		frame.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://EmployeeRecords.accdb");
			String sql = "SELECT Salary, email, username, password, mobile_number FROM Employees";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (e.getSource() == backButton) {
				EmployeeRecords employeeRecords = new EmployeeRecords();
				employeeRecords.execute();
				frame.dispose();
			} else {
				if (nameBox.getSelectedIndex() == 0) {
					salary.setText("");
					email.setText("");
					username.setText("");
					password.setText("");
					mobileNum.setText("");
				} else {
					while (rs.next()) {
						if (nameBox.getSelectedIndex() == rs.getRow()) {
							salary.setText(rs.getString("Salary"));
							email.setText(rs.getString("email"));
							username.setText(rs.getString("username"));
							password.setText(rs.getString("password"));
							mobileNum.setText(rs.getString("mobile_number"));
							break;
						}
					}
				}

			}
			conn.close();
		} catch (SQLException e1) {
			System.out.println(e1.getCause());
		}
	}
}
