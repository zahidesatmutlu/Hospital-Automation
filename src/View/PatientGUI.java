package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Patient;
import Model.WorkHour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class PatientGUI extends JFrame {

	private JPanel w_pane;
	private static Patient patient = new Patient();
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_workhour;
	private WorkHour workhour = new WorkHour();
	private DefaultTableModel workHourModel;
	private Object[] workHourData = null;
	private int selectedDoctorID = 0;
	private String selectedDoctorName = null;
	private JTable table_appointment;
	private DefaultTableModel appointmentModel;
	private Object[] appointmentData = null;
	private Appointment appointment = new Appointment();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI frame = new PatientGUI(patient);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PatientGUI(Patient patient) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] columnDoctor = new Object[2];
		columnDoctor[0] = "ID";
		columnDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(columnDoctor);
		doctorData = new Object[2];

		workHourModel = new DefaultTableModel();
		Object[] columnWorkHour = new Object[2];
		columnWorkHour[0] = "ID";
		columnWorkHour[1] = "Tarih";
		workHourModel.setColumnIdentifiers(columnWorkHour);
		workHourData = new Object[2];

		appointmentModel = new DefaultTableModel();
		Object[] columnAppointment = new Object[3];
		columnAppointment[0] = "ID";
		columnAppointment[1] = "Doktor Adı";
		columnAppointment[2] = "Tarih";
		appointmentModel.setColumnIdentifiers(columnAppointment);
		appointmentData = new Object[3];
		for (int i = 0; i < appointment.getPatientList(patient.getId()).size(); i++) {
			appointmentData[0] = appointment.getPatientList(patient.getId()).get(i).getId();
			appointmentData[1] = appointment.getPatientList(patient.getId()).get(i).getDoctorName();
			appointmentData[2] = appointment.getPatientList(patient.getId()).get(i).getAppointmentDate();
			appointmentModel.addRow(appointmentData);
		}

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel label_welcome = new JLabel("Hoşgeldiniz, Sayın " + patient.getName());
		label_welcome.setFont(new Font("Roboto", Font.BOLD, 18));
		label_welcome.setBounds(23, 19, 360, 22);
		w_pane.add(label_welcome);

		JButton btn_quit = new JButton("Çıkış Yap");
		btn_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_quit.setBounds(562, 19, 100, 26);
		w_pane.add(btn_quit);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 72, 664, 380);
		w_pane.add(w_tabpane);

		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tabpane.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 33, 236, 310);
		w_appointment.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		JLabel lblNewLabel = new JLabel("Doktor Listesi");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel.setBounds(85, 10, 110, 20);
		w_appointment.add(lblNewLabel);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(256, 33, 145, 24);
		select_clinic.addItem("#poliklinik seçiniz#");
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			select_clinic
					.addItem(new Item(clinic.getClinicList().get(i).getId(), clinic.getClinicList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox combobox = (JComboBox) e.getSource();
					Item item = (Item) combobox.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_appointment.add(select_clinic);

		JLabel lblPoliklinikAd_1 = new JLabel("Doktor Seç");
		lblPoliklinikAd_1.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		lblPoliklinikAd_1.setBounds(256, 78, 145, 24);
		w_appointment.add(lblPoliklinikAd_1);

		JButton btn_selectDoctor = new JButton("Seç");
		btn_selectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int workHourRow = table_doctor.getSelectedRow();
				if (workHourRow >= 0) {
					String value = table_doctor.getModel().getValueAt(workHourRow, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_workhour.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < workhour.getWorkHourList(id).size(); i++) {
							workHourData[0] = workhour.getWorkHourList(id).get(i).getId();
							workHourData[1] = workhour.getWorkHourList(id).get(i).getWorkDate();
							workHourModel.addRow(workHourData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_workhour.setModel(workHourModel);
					selectedDoctorID = id;
					selectedDoctorName = table_doctor.getModel().getValueAt(workHourRow, 1).toString();
				} else {
					Helper.showMessage("Lütfen bir doktor seçiniz.");
				}
			}
		});
		btn_selectDoctor.setBounds(256, 101, 145, 32);
		w_appointment.add(btn_selectDoctor);

		JScrollPane w_scrollWorkHour = new JScrollPane();
		w_scrollWorkHour.setBounds(413, 33, 236, 310);
		w_appointment.add(w_scrollWorkHour);

		table_workhour = new JTable(workHourModel);
		w_scrollWorkHour.setViewportView(table_workhour);
		table_workhour.getColumnModel().getColumn(0).setPreferredWidth(16);

		JLabel lblNewLabel_1 = new JLabel("Randevu Listesi");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_1.setBounds(478, 10, 110, 20);
		w_appointment.add(lblNewLabel_1);

		JLabel lblPoliklinikAd = new JLabel("Poliklinik Adı");
		lblPoliklinikAd.setBounds(256, 7, 145, 24);
		w_appointment.add(lblPoliklinikAd);
		lblPoliklinikAd.setFont(new Font("Malgun Gothic", Font.BOLD, 12));

		JLabel lblPoliklinikAd_1_1 = new JLabel("Randevu");
		lblPoliklinikAd_1_1.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		lblPoliklinikAd_1_1.setBounds(256, 173, 145, 24);
		w_appointment.add(lblPoliklinikAd_1_1);

		JButton btn_addAppointment = new JButton("Randevu Al");
		btn_addAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow = table_workhour.getSelectedRow();
				if (selectRow >= 0) {
					String appointmentDate = table_workhour.getModel().getValueAt(selectRow, 1).toString();
					try {
						boolean control = patient.addAppointment(selectedDoctorID, patient.getId(), selectedDoctorName,
								patient.getName(), appointmentDate);
						if (control) {
							Helper.showMessage("successfully");
							patient.updateWorkHourStatus(selectedDoctorID, appointmentDate);
							updateWorkHourModel(selectedDoctorID);
							updateAppointmentModel(patient.getId());
						} else {
							Helper.showMessage("Error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btn_addAppointment.setBounds(256, 196, 145, 32);
		w_appointment.add(btn_addAppointment);

		JPanel w_appointmentList = new JPanel();
		w_appointmentList.setBackground(Color.WHITE);
		w_tabpane.addTab("Randevularım", null, w_appointmentList, null);
		w_appointmentList.setLayout(null);

		JScrollPane w_scrollAppointment = new JScrollPane();
		w_scrollAppointment.setBounds(10, 10, 639, 333);
		w_appointmentList.add(w_scrollAppointment);

		table_appointment = new JTable(appointmentModel);
		w_scrollAppointment.setViewportView(table_appointment);
	}

	public void updateWorkHourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_workhour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < workhour.getWorkHourList(doctor_id).size(); i++) {
			workHourData[0] = workhour.getWorkHourList(doctor_id).get(i).getId();
			workHourData[1] = workhour.getWorkHourList(doctor_id).get(i).getWorkDate();
			workHourModel.addRow(workHourData);
		}
	}

	public void updateAppointmentModel(int patient_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appointment.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < appointment.getPatientList(patient_id).size(); i++) {
			appointmentData[0] = appointment.getPatientList(patient_id).get(i).getId();
			appointmentData[1] = appointment.getPatientList(patient_id).get(i).getDoctorName();
			appointmentData[2] = appointment.getPatientList(patient_id).get(i).getAppointmentDate();
			appointmentModel.addRow(appointmentData);
		}
	}
}
