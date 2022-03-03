package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Doctor;
import Model.Patient;
import Model.ChiefPhysician;

public class LoginGUI extends JFrame {

	private JPanel wrapper_pane;
	private JTextField field_patientTC;
	private JTextField field_doctorTC;
	private JPasswordField field_doctorPassword;
	private JPasswordField field_patientPassword;
	private databaseConnection connect = new databaseConnection();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		wrapper_pane = new JPanel();
		wrapper_pane.setBackground(Color.WHITE);
		wrapper_pane.setForeground(Color.WHITE);
		wrapper_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(wrapper_pane);
		wrapper_pane.setLayout(null);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(30, 225, 475, 210);
		wrapper_pane.add(w_tabpane);

		JPanel w_patientLogin = new JPanel();
		w_patientLogin.setBorder(null);
		w_patientLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_patientLogin, null);
		w_patientLogin.setLayout(null);

		JLabel label_tcNo1 = new JLabel("T.C. Kimlik No: ");
		label_tcNo1.setFont(new Font("Roboto", Font.PLAIN, 18));
		label_tcNo1.setBounds(42, 25, 155, 24);
		w_patientLogin.add(label_tcNo1);

		JLabel label_password1 = new JLabel("Şifre: ");
		label_password1.setFont(new Font("Roboto", Font.PLAIN, 18));
		label_password1.setBounds(42, 60, 155, 24);
		w_patientLogin.add(label_password1);

		field_patientTC = new JTextField();
		field_patientTC.setColumns(10);
		field_patientTC.setBounds(238, 27, 185, 24);
		w_patientLogin.add(field_patientTC);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI register = new RegisterGUI();
				register.setVisible(true);
				dispose();
			}
		});
		btn_register.setBounds(42, 116, 155, 39);
		w_patientLogin.add(btn_register);

		JButton btn_login2 = new JButton("Giriş Yap");
		btn_login2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field_patientTC.getText().length() == 0 || field_patientPassword.getText().length() == 0) {
					Helper.showMessage("fill_up");
				} else {
					boolean key = true;
					try {
						Connection connection = connect.connectDatabase();
						java.sql.Statement st = connection.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (field_patientTC.getText().equals(rs.getString("tcno"))
									&& field_patientPassword.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("patient")) {
									Patient patient = new Patient();
									patient.setId(rs.getInt("id"));
									patient.setPassword("password");
									patient.setName(rs.getString("name"));
									patient.setTcNo(rs.getString("tcno"));
									patient.setType(rs.getString("type"));
									PatientGUI patientGUI = new PatientGUI(patient);
									patientGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (key) {
						Helper.showMessage("Böyle bir hasta bulunamadı. Lütfen kayıt olunuz!");
					}

				}
			}
		});
		btn_login2.setBounds(268, 116, 155, 39);
		w_patientLogin.add(btn_login2);

		field_patientPassword = new JPasswordField();
		field_patientPassword.setBounds(238, 64, 185, 24);
		w_patientLogin.add(field_patientPassword);

		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setLayout(null);
		w_doctorLogin.setBorder(null);
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);

		JLabel label_tcNo2 = new JLabel("T.C. Kimlik No: ");
		label_tcNo2.setFont(new Font("Roboto", Font.PLAIN, 18));
		label_tcNo2.setBounds(42, 26, 155, 24);
		w_doctorLogin.add(label_tcNo2);

		JLabel label_password2 = new JLabel("Şifre: ");
		label_password2.setFont(new Font("Roboto", Font.PLAIN, 18));
		label_password2.setBounds(42, 61, 155, 24);
		w_doctorLogin.add(label_password2);

		field_doctorTC = new JTextField();
		field_doctorTC.setColumns(10);
		field_doctorTC.setBounds(238, 28, 185, 24);
		w_doctorLogin.add(field_doctorTC);

		JButton btn_login1 = new JButton("Giriş Yap");
		btn_login1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field_doctorTC.getText().length() == 0 || field_doctorPassword.getText().length() == 0) {
					Helper.showMessage("fill_up");
				} else {
					try {
						Connection connection = connect.connectDatabase();
						java.sql.Statement st = connection.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (field_doctorTC.getText().equals(rs.getString("tcno"))
									&& field_doctorPassword.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("chiefphysician")) {
									ChiefPhysician ChiefPhysician = new ChiefPhysician();
									ChiefPhysician.setId(rs.getInt("id"));
									ChiefPhysician.setPassword("password");
									ChiefPhysician.setName(rs.getString("name"));
									ChiefPhysician.setTcNo(rs.getString("tcno"));
									ChiefPhysician.setType(rs.getString("type"));
									ChiefPhysicianGUI ChiefPhysicianGUI = new ChiefPhysicianGUI(ChiefPhysician);
									ChiefPhysicianGUI.setVisible(true);
									dispose();
								} else if (rs.getString("type").equals("doctor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setName(rs.getString("name"));
									doctor.setTcNo(rs.getString("tcno"));
									doctor.setType(rs.getString("type"));
									DoctorGUI doctorGUI = new DoctorGUI(doctor);
									doctorGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_login1.setBounds(42, 116, 380, 40);
		w_doctorLogin.add(btn_login1);

		field_doctorPassword = new JPasswordField();
		field_doctorPassword.setBounds(238, 63, 185, 24);
		w_doctorLogin.add(field_doctorPassword);

		JLabel label_logo = new JLabel(new ImageIcon(getClass().getResource("Logo.png")));
		label_logo.setVerticalAlignment(SwingConstants.BOTTOM);
		label_logo.setBounds(150, -10, 250, 250);
		wrapper_pane.add(label_logo);
	}
}
