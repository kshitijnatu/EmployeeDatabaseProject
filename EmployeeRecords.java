package graphicalUserInterfacePlusDBProjects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JPanel;
import javax.swing.*;

public final class EmployeeRecords extends LoginScreen implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JLabel title;
	private JMenuBar menuBar;
	private JMenu addEmployee, viewEmployee, removeEmployee;
	private JMenuItem openAddEmployee, openViewEmployee, openRemoveEmployee;

	public EmployeeRecords() {
		title = setLabel("Home Page", 600, 0, 500, 100, Color.WHITE, "Times New Roman", 2, 50);

		menuBar = new JMenuBar();

		addEmployee = new JMenu("Add Employee");
		viewEmployee = new JMenu("View Employees");
		removeEmployee = new JMenu("Remove Employee");

		openAddEmployee = new JMenuItem("Open");
		openAddEmployee.addActionListener(this);

		openViewEmployee = new JMenuItem("Open");
		openViewEmployee.addActionListener(this);

		openRemoveEmployee = new JMenuItem("Open");
		openRemoveEmployee.addActionListener(this);

		addEmployee.add(openAddEmployee);
		viewEmployee.add(openViewEmployee);
		removeEmployee.add(openRemoveEmployee);

		menuBar.add(addEmployee);
		menuBar.add(viewEmployee);
		menuBar.add(removeEmployee);

	}

	@Override
	public void execute() {
		panel = setPanel(Color.BLACK);
		panel.add(title);

		frame = setFrame("Company Name");
		frame.setJMenuBar(menuBar);
		frame.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openAddEmployee) {
			AddEmployee addEmployee = new AddEmployee();
			addEmployee.execute();
			frame.dispose();
		} else if (e.getSource() == openViewEmployee) {
			ViewEmployees viewEmployees = new ViewEmployees();
			viewEmployees.execute();
			frame.dispose();
		} else if (e.getSource() == openRemoveEmployee) {
			RemoveEmployees removeEmployees = new RemoveEmployees();
			removeEmployees.execute();
			frame.dispose();
		}
	}
}
