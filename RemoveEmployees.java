package graphicalUserInterfacePlusDBProjects;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public final class RemoveEmployees extends LoginScreen implements ActionListener {

	private Font font = setFont("Arial", Font.BOLD, 30);
	private JFrame frame;
	private JPanel panel;
	private JLabel removeEmployeeLabel;
	private JButton removeButton, backButton;
	private JComboBox<String> nameBox;

	public RemoveEmployees() {
		removeEmployeeLabel = setLabel("Remove Employees", 600, 30, 450, 55, Color.BLACK, "Times New Roman", Font.BOLD,
				50);
		removeButton = setButton("Remove", 500 + 100, 500, 200, 100);
		removeButton.setFont(font);

		backButton = setButton("Back", removeButton.getX() + 250, removeButton.getY(), removeButton.getWidth(),
				removeButton.getHeight());
		backButton.setFont(font);
		String[] choices = null;
		try {
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://EmployeeRecords.accdb");
			String sql = "SELECT First_Name, Last_Name FROM Employees";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ArrayList<String> names = new ArrayList<>();
			while (rs.next()) {
				names.add(rs.getString("First_Name") + " " + rs.getString("Last_Name"));
			}
			choices = new String[names.size()];
			for (int i = 0; i < choices.length; i++) {
				choices[i] = names.get(i);
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getCause());
		}
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		nameBox = setBox(700, 200, 250, 100, choices);
		nameBox.setRenderer(dlcr);
		nameBox.setFont(font);
	}

	@Override
	public void execute() {
		panel = setPanel(new Color(60, 174, 158));
		panel.add(removeEmployeeLabel);
		panel.add(removeButton);
		panel.add(backButton);
		panel.add(nameBox);

		frame = setFrame("Company Name");
		frame.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			frame.dispose();
			EmployeeRecords employeeRecords = new EmployeeRecords();
			employeeRecords.execute();
		} else if (e.getSource() == removeButton) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:ucanaccess://EmployeeRecords.accdb");
				Statement st = conn.createStatement();
				String sql = "DELETE FROM Employees WHERE First_Name||' '||Last_Name='" + nameBox.getSelectedItem()
						+ "'";
				int updated = st.executeUpdate(sql);
				if (updated > 0) {
					System.out.println("An employee has been deleted");
				}
				conn.close();
			} catch (SQLException e1) {
				System.out.println(e1.getCause());
			}
		}
	}

}
