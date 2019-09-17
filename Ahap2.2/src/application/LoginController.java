package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController {

	@FXML private Label lblStatus;
	@FXML private TextField txtUsername, txtPassword;
	@FXML private TextField txtFirstname, txtLastname, txtNewPassword, txtNewUsername, txtType;


	public void Login(ActionEvent event) throws Exception {


		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String user = "";
		String pass = "";
		String type = "";
		Employees employee = null;

		try {
		

		employee = Platform.getEmployee(username);
		
		user = employee.getUsername();
		pass = employee.getPassword();
		type = employee.getEmployeeType();
		
		} catch (NullPointerException e) {
			lblStatus.setText("Login Failed");
			System.out.println("Login Failed");
		}
		
		if (username.equals(user)) {
			if (password.equals(pass) && (type.equals("Manager") || type.equals("Staff"))) {

				lblStatus.setText("Login Success");
				Platform.setLoggedIn(employee);
				Platform.getScene().home();

			} else {
				lblStatus.setText("Login Failed");
				lblStatus.setVisible(true);
			}
		} else {
			lblStatus.setText("Login Failed");
			lblStatus.setVisible(true);
		}
	}
}
