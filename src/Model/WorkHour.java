package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.databaseConnection;

public class WorkHour {
	private int id, doctor_id;
	private String doctor_name, workDate, status;

	private databaseConnection connect = new databaseConnection();
	PreparedStatement preparedStatement = null;

	public WorkHour(int id, int doctor_id, String doctor_name, String workDate, String status) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.workDate = workDate;
		this.status = status;
	}

	public ArrayList<WorkHour> getWorkHourList(int doctor_id) throws SQLException {
		ArrayList<WorkHour> list = new ArrayList<>();
		WorkHour object;
		try {
			Connection connection = connect.connectDatabase();
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

	public WorkHour() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
