package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.databaseConnection;

public class Doctor extends User {
	private databaseConnection connect = new databaseConnection();
	Connection connection = connect.connectDatabase();
	PreparedStatement preparedStatement = null;

	public Doctor() {
		super();
	}

	public Doctor(int id, String name, String tcNo, String password, String type, databaseConnection connect) {
		super(id, name, tcNo, password, type);
	}

	public boolean addWorkHour(int doctor_id, String doctor_name, String workdate) throws SQLException {
		boolean key = false;
		int count = 0;
		String query = "INSERT INTO workhour" + "(doctor_id, doctor_name, workdate) VALUES" + "(?, ?, ?)";

		try {
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM workhour WHERE status = 'A' AND doctor_id = " + doctor_id
					+ " AND workdate = '" + workdate + "'");
			while (rs.next()) {
				count++;
				break;
			}
			if (count == 0) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, doctor_id);
				preparedStatement.setString(2, doctor_name);
				preparedStatement.setString(3, workdate);
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

	public ArrayList<WorkHour> getWorkHourList(int doctor_id) throws SQLException {
		ArrayList<WorkHour> list = new ArrayList<>();
		WorkHour object;
		try {
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM workhour WHERE status = 'A' AND doctor_id = " + doctor_id);
			while (rs.next()) {
				object = new WorkHour();
				object.setId(rs.getInt("id"));
				object.setDoctor_id(rs.getInt("doctor_id"));
				object.setDoctor_name(rs.getString("doctor_name"));
				object.setStatus(rs.getString("status"));
				object.setWorkDate(rs.getString("workdate"));
				list.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteWorkHour(int id) throws SQLException {
		boolean key = false;
		String query = "DELETE FROM workhour WHERE id = ?";
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

}
