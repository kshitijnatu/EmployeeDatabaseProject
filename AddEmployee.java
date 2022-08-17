package graphicalUserInterfacePlusDBProjects;

//import java.awt.Color;
//import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.sql.*;

//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
import javax.swing.*;

public final class AddEmployee extends LoginScreen implements ActionListener {

	private Font font = setFont("Arial", Font.BOLD, 30);
	private JFrame frame;
	private JPanel panel;
	private JLabel title, firstNameLabel, lastNameLabel, salaryLabel, emailLabel, usernameLabel, passwordLabel,
			mobileNumLabel;
	private JTextField firstName, lastName, salary, email, username, password, mobileNum;
	private JButton addButton, backButton;

	public AddEmployee() {
		title = setLabel("Add Employee", 600, 0, 300, 100, Color.BLACK, "Times New Roman", 2, 50);
		firstNameLabel = setLabel("First Name: ", 30, 100, 300, 100, Color.BLACK, "Times New Roman", 2, 50);
		lastNameLabel = setLabel("Last Name: ", 30, 200, 300, 100, Color.BLACK, "Times New Roman", 2, 50);
		salaryLabel = setLabel("Salary: ", 30, 300, 300, 100, Color.BLACK, "Times New Roman", 2, 50);
		emailLabel = setLabel("Email: ", 30, 400, 300, 100, Color.BLACK, "Times New Roman", 2, 50);
		usernameLabel = setLabel("Username: ", 30, 500, 300, 100, Color.BLACK, "Times New Roman", 2, 50);
		passwordLabel = setLabel("Password: ", 30, 600, 300, 100, Color.BLACK, "Times New Roman", 2, 50);
		mobileNumLabel = setLabel("Mobile Number: ", 30, 700, 400, 100, Color.BLACK, "Times New Roman", 2, 50);

		firstName = setTextField(firstNameLabel.getX() + 350, firstNameLabel.getY() + 25, 300, 50);
		firstName.setFont(font);

		lastName = setTextField(lastNameLabel.getX() + 350, lastNameLabel.getY() + 25, firstName.getWidth(),
				firstName.getHeight());
		lastName.setFont(font);

		salary = setTextField(salaryLabel.getX() + 350, salaryLabel.getY() + 25, firstName.getWidth(),
				firstName.getHeight());
		salary.setFont(font);

		email = setTextField(emailLabel.getX() + 350, emailLabel.getY() + 25, firstName.getWidth(),
				firstName.getHeight());
		email.setFont(font);

		username = setTextField(usernameLabel.getX() + 350, usernameLabel.getY() + 25, firstName.getWidth(),
				firstName.getHeight());
		username.setFont(font);

		password = setTextField(passwordLabel.getX() + 350, passwordLabel.getY() + 25, firstName.getWidth(),
				firstName.getHeight());
		password.setFont(font);

		mobileNum = setTextField(mobileNumLabel.getX() + 350, mobileNumLabel.getY() + 25, firstName.getWidth(),
				firstName.getHeight());
		mobileNum.setFont(font);

		addButton = setButton("Add", 1000, 400, 200, 150);
		addButton.setFont(font);

		backButton = setButton("Back", addButton.getX() + 250, addButton.getY(), addButton.getWidth(),
				addButton.getHeight());
		backButton.setFont(font);
	}

	@Override
	public void execute() {
		panel = setPanel(0, 0, 1000, 1000, new Color(249, 248, 203));
		panel.add(title);
		panel.add(firstNameLabel);
		panel.add(lastNameLabel);
		panel.add(salaryLabel);
		panel.add(emailLabel);
		panel.add(usernameLabel);
		panel.add(passwordLabel);
		panel.add(mobileNumLabel);
		panel.add(firstName);
		panel.add(lastName);
		panel.add(salary);
		panel.add(email);
		panel.add(username);
		panel.add(password);
		panel.add(mobileNum);
		panel.add(addButton);
		panel.add(backButton);

		frame = setFrame("Company Name");
		frame.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			String databaseURL = "jdbc:ucanaccess://EmployeeRecords.accdb";
			try {
				Connection conn = DriverManager.getConnection(databaseURL);
				String sql = "INSERT INTO Employees (First_Name, Last_Name, Salary, email, username, password, mobile_number) VALUES "
						+ "(?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatemet = conn.prepareStatement(sql);
				preparedStatemet.setString(1, firstName.getText());
				preparedStatemet.setString(2, lastName.getText());
				preparedStatemet.setInt(3, Integer.parseInt(salary.getText()));
				preparedStatemet.setString(4, email.getText());
				preparedStatemet.setString(5, username.getText());
				preparedStatemet.setString(6, password.getText());
				preparedStatemet.setString(7, mobileNum.getText());

				int rows = preparedStatemet.executeUpdate();
				if (rows > 0) {
					System.out.println("A new employee has been added");
					firstName.setText("");
					lastName.setText("");
					salary.setText("");
					email.setText("");
					username.setText("");
					password.setText("");
					mobileNum.setText("");
				}
				conn.close();
			} catch (SQLException e1) {
				System.out.println(e1.getCause());
			}

		} else if (e.getSource() == backButton) {
			EmployeeRecords employeeRecords = new EmployeeRecords();
			employeeRecords.execute();
			frame.dispose();
		}
	}
}
