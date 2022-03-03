package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.databaseConnection;

public class Appointment {
	private int id, doctorID, patientID;
	private String doctorName, patientName, appointmentDate;

	private databaseConnection connect = new databaseConnection();
	PreparedStatement preparedStatement = null;

	public Appointment(int id, int doctorID, int patientID, String doctorName, String patientName,
			String appointmentDate) {
		super();
		this.id = id;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.appointmentDate = appointmentDate;
	}

	public ArrayList<Appointment> getPatientList(int patient_id) throws SQLException {
		ArrayList<Appointment> appointmentList = new ArrayList<>();
		Appointment object;
		Connection connection = connect.connectDatabase();
		try {
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM appointment WHERE patient_id = " + patient_id);
			while (rs.next()) {
				object = new Appointment();
				object.setId(rs.getInt("id"));
				object.setDoctorID(rs.getInt("doctor_id"));
				object.setDoctorName(rs.getString("doctor_name"));
				object.setPatientID(rs.getInt("patient_id"));
				object.setPatientName(rs.getString("patient_name"));
				object.setAppointmentDate(rs.getString("appointment_date"));
				appointmentList.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appointmentList;
	}

	public ArrayList<Appointment> getDoctorList(int doctor_id) throws SQLException {
		ArrayList<Appointment> doctorList = new ArrayList<>();
		Appointment object;
		Connection connection = connect.connectDatabase();
		try {
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM appointment WHERE doctor_id = " + doctor_id);
			while (rs.next()) {
				object = new Appointment();
				object.setId(rs.getInt("id"));
				object.setDoctorID(rs.getInt("doctor_id"));
				object.setDoctorName(rs.getString("doctor_name"));
				object.setPatientID(rs.getInt("patient_id"));
				object.setPatientName(rs.getString("patient_name"));
				object.setAppointmentDate(rs.getString("appointment_date"));
				doctorList.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorList;
	}

	public Appointment() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
}
