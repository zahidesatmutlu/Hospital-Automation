package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Helper.Helper;
import Helper.databaseConnection;

public class Patient extends User {

	private databaseConnection connect = new databaseConnection();
	Connection connection = connect.connectDatabase();
	PreparedStatement preparedStatement = null;

	public Patient() {
	}

	public Patient(int id, String name, String tcNo, String password, String type) {
		super(id, name, tcNo, password, type);
	}

	public boolean register(String tcno, String password, String name) throws SQLException {
		boolean key = false;
		boolean duplicate = false;
		String query = "INSERT INTO user" + "(tcno, password, name, type) VALUES" + "(?, ?, ?, ?)";

		try {
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");
			while (rs.next()) {
				duplicate = true;
				Helper.showMessage("Bu T.C. kimlik numarasına ait bir kayıt bulunmaktadır.");
				break;
			}
			if (!duplicate) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "patient");
				preparedStatement.executeUpdate();
				key = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean addAppointment(int doctor_id, int patient_id, String doctor_name, String patient_name,
			String appointment_date) throws SQLException {
		boolean key = false;
		String query = "INSERT INTO appointment"
				+ "(doctor_id, doctor_name, patient_id, patient_name, appointment_date) VALUES" + "(?, ?, ?, ?, ?)";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setInt(3, patient_id);
			preparedStatement.setString(4, patient_name);
			preparedStatement.setString(5, appointment_date);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean updateWorkHourStatus(int doctor_id, String workdate) throws SQLException {
		boolean key = false;
		String query = "UPDATE workhour SET status = ? WHERE doctor_id = ? AND workdate = ?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "P");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, workdate);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

}
