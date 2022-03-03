package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.databaseConnection;

public class Clinic {
	private int id;
	private String name;

	private databaseConnection connect = new databaseConnection();
	PreparedStatement preparedStatement = null;

	public Clinic() {
	}

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ArrayList<Clinic> getClinicList() throws SQLException {
		ArrayList<Clinic> clinicList = new ArrayList<>();
		Clinic object;
		try {
			Connection connection = connect.connectDatabase();
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clinic");
			while (rs.next()) {
				object = new Clinic();
				object.setId(rs.getInt("id"));
				object.setName(rs.getString("name"));
				clinicList.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clinicList;
	}

	public boolean addClinic(String name) throws SQLException {
		boolean key = false;
		String query = "INSERT INTO clinic" + "(name) VALUES" + "(?)";

		try {
			Connection connection = connect.connectDatabase();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
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

	public boolean deleteClinic(int id) throws SQLException {
		boolean key = false;
		String query = "DELETE FROM clinic WHERE id = ?";
		try {
			Connection connection = connect.connectDatabase();
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

	public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {
		ArrayList<User> doctorList = new ArrayList<>();
		User object;
		try {
			Connection connection = connect.connectDatabase();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
