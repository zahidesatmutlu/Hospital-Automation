package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.databaseConnection;

public class ChiefPhysician extends User {

	private databaseConnection connect = new databaseConnection();
	Connection connection = connect.connectDatabase();
	PreparedStatement preparedStatement = null;

	public ChiefPhysician() {
		super();
	}

	public ChiefPhysician(int id, String tcNo, String name, String password, String type) {
		super(id, tcNo, name, password, type);
	}

	public ArrayList<User> getdoctorList() throws SQLException {
		ArrayList<User> doctorList = new ArrayList<>();
		User object;
		try {
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE type = 'doctor'");

			while (rs.next()) {
				object = new User(rs.getInt("id"), rs.getString("name"), rs.getString("tcno"), rs.getString("password"),
						rs.getString("type"));
				doctorList.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorList;
	}

	public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {
		ArrayList<User> doctorList = new ArrayList<>();
		User object;
		try {
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT u.id, u.tcno, u.type, u.name, u.password FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id ="
							+ clinic_id);

			while (rs.next()) {
				object = new User(rs.getInt("u.id"), rs.getString("u.name"), rs.getString("u.tcno"),
						rs.getString("u.password"), rs.getString("u.type"));
				doctorList.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorList;
	}

	public boolean addDoctor(String tcno, String password, String name) throws SQLException {
		boolean key = false;
		String query = "INSERT INTO user" + "(tcno, password, name, type) VALUES" + "(?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doctor");
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

	public boolean deleteDoctor(int id) throws SQLException {
		boolean key = false;
		String query = "DELETE FROM user WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
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

	public boolean updateDoctor(int id, String tcno, String password, String name) throws SQLException {
		boolean key = false;
		String query = "UPDATE user SET name = ?, tcno = ?, password = ? WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);
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

	public boolean addWorker(int user_id, int clinic_id) throws SQLException {
		boolean key = false;
		String query = "INSERT INTO worker" + "(user_id, clinic_id) VALUES" + "(?, ?)";
		int count = 0;
		try {
			Connection connection = connect.connectDatabase();
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM  worker WHERE clinic_id AND user_id =" + clinic_id + user_id);
			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, clinic_id);
				preparedStatement.executeUpdate();
			}
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
