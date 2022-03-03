package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Helper.*;
import javax.swing.JComboBox;

public class ChiefPhysicianGUI extends JFrame {

	static ChiefPhysician ChiefPhysician = new ChiefPhysician();
	Clinic clinic = new Clinic();
	private JPanel wrapper_pane;
	private JTextField field_doctorName;
	private JTextField field_doctorTc;
	private JTextField field_doctorPassword;
	private JTextField field_doctorId;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorListData = null;
	private JTable table_clinic;
	private JTextField field_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JTextField field_deleteClinic;
	private JTable table_worker;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiefPhysicianGUI frame = new ChiefPhysicianGUI(ChiefPhysician);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChiefPhysicianGUI(ChiefPhysician ChiefPhysician) throws SQLException {

		// Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] columnDoctorName = new Object[4];
		columnDoctorName[0] = "ID";
		columnDoctorName[1] = "Ad Soyad";
		columnDoctorName[2] = "TC No";
		columnDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(columnDoctorName);
		doctorListData = new Object[4];
		for (int i = 0; i < ChiefPhysician.getdoctorList().size(); i++) {
			doctorListData[0] = ChiefPhysician.getdoctorList().get(i).getId();
			doctorListData[1] = ChiefPhysician.getdoctorList().get(i).getName();
			doctorListData[2] = ChiefPhysician.getdoctorList().get(i).getTcNo();
			doctorListData[3] = ChiefPhysician.getdoctorList().get(i).getPassword();
			doctorModel.addRow(doctorListData);
		}

		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] columnClinicName = new Object[2];
		columnClinicName[0] = "ID";
		columnClinicName[1] = "Poliklinik Adı";
		clinicModel.setColumnIdentifiers(columnClinicName);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		// Worker Model
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] columnWorker = new Object[2];
		columnWorker[0] = "ID";
		columnWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(columnWorker);
		Object[] workerData = new Object[2];

		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		wrapper_pane = new JPanel();
		wrapper_pane.setBackground(Color.WHITE);
		wrapper_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(wrapper_pane);
		wrapper_pane.setLayout(null);

		JLabel label_welcome = new JLabel("Hoşgeldiniz, Dr. " + ChiefPhysician.getName());
		label_welcome.setFont(new Font("Roboto", Font.BOLD, 18));
		label_welcome.setBounds(23, 19, 360, 22);
		wrapper_pane.add(label_welcome);

		JButton btn_quit = new JButton("Çıkış Yap");
		btn_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_quit.setBounds(562, 19, 100, 26);
		wrapper_pane.add(btn_quit);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 60, 668, 390);
		wrapper_pane.add(w_tabpane);

		JPanel w_doctorManagement = new JPanel();
		w_doctorManagement.setLayout(null);
		w_doctorManagement.setBorder(null);
		w_doctorManagement.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Yönetimi", null, w_doctorManagement, null);

		field_doctorName = new JTextField();
		field_doctorName.setColumns(10);
		field_doctorName.setBounds(498, 35, 145, 24);
		w_doctorManagement.add(field_doctorName);

		JLabel label1 = new JLabel("Ad Soyad");
		label1.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		label1.setBounds(498, 11, 155, 24);
		w_doctorManagement.add(label1);

		field_doctorTc = new JTextField();
		field_doctorTc.setColumns(10);
		field_doctorTc.setBounds(498, 94, 145, 24);
		w_doctorManagement.add(field_doctorTc);

		JLabel label2 = new JLabel("T.C. No");
		label2.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		label2.setBounds(498, 70, 155, 24);
		w_doctorManagement.add(label2);

		field_doctorPassword = new JTextField();
		field_doctorPassword.setColumns(10);
		field_doctorPassword.setBounds(498, 153, 145, 24);
		w_doctorManagement.add(field_doctorPassword);

		JLabel label3 = new JLabel("Şifre");
		label3.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		label3.setBounds(498, 129, 155, 24);
		w_doctorManagement.add(label3);

		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field_doctorName.getText().length() == 0 || field_doctorTc.getText().length() == 0
						|| field_doctorPassword.getText().length() == 0) {
					Helper.showMessage("fill_up");
				} else {
					try {
						boolean control = ChiefPhysician.addDoctor(field_doctorTc.getText(),
								field_doctorPassword.getText(), field_doctorName.getText());
						if (control) {
							Helper.showMessage("successfully");
							field_doctorName.setText(null);
							field_doctorPassword.setText(null);
							field_doctorTc.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btn_addDoctor.setBounds(498, 188, 145, 32);
		w_doctorManagement.add(btn_addDoctor);

		JLabel label4 = new JLabel("Kullanıcı ID");
		label4.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		label4.setBounds(498, 236, 155, 24);
		w_doctorManagement.add(label4);

		JButton btn_deleteDoctor = new JButton("Sil");
		btn_deleteDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field_doctorId.getText().length() == 0) {
					Helper.showMessage("Lütfen geçerli bir doktor id giriniz.");
				} else {
					if (Helper.confirm("sure")) {
						int selectedID = Integer.parseInt(field_doctorId.getText());
						try {
							boolean control = ChiefPhysician.deleteDoctor(selectedID);
							if (control) {
								Helper.showMessage("successfully");
								field_doctorId.setText(null);
								updateDoctorModel();
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_deleteDoctor.setBounds(498, 306, 145, 32);
		w_doctorManagement.add(btn_deleteDoctor);

		field_doctorId = new JTextField();
		field_doctorId.setColumns(10);
		field_doctorId.setBounds(498, 271, 145, 24);
		field_doctorId.setEditable(false);
		w_doctorManagement.add(field_doctorId);

		JScrollPane wrapper_scrollpane = new JScrollPane();
		wrapper_scrollpane.setBounds(10, 11, 463, 340);
		w_doctorManagement.add(wrapper_scrollpane);

		table_doctor = new JTable(doctorModel);
		wrapper_scrollpane.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					field_doctorId.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception e1) {

				}
			}
		});

		table_doctor.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcNo = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPassword = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {
						ChiefPhysician.updateDoctor(selectID, selectTcNo, selectPassword, selectName);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tabpane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane wrapper_scrollClinic = new JScrollPane();
		wrapper_scrollClinic.setBounds(10, 11, 239, 340);
		w_clinic.add(wrapper_scrollClinic);

		table_clinic = new JTable(clinicModel);
		wrapper_scrollClinic.setViewportView(table_clinic);

		JLabel lblPoliklinikAd = new JLabel("Poliklinik Adı");
		lblPoliklinikAd.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		lblPoliklinikAd.setBounds(259, 11, 145, 24);
		w_clinic.add(lblPoliklinikAd);

		field_clinicName = new JTextField();
		field_clinicName.setColumns(10);
		field_clinicName.setBounds(259, 35, 145, 24);
		w_clinic.add(field_clinicName);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (field_clinicName.getText().length() == 0) {
					Helper.showMessage("fill_up");
				} else {
					try {
						if (clinic.addClinic(field_clinicName.getText())) {
							Helper.showMessage("successfully");
							field_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setBounds(259, 70, 145, 32);
		w_clinic.add(btn_addClinic);

		JScrollPane wrapper_scrollWorker = new JScrollPane();
		wrapper_scrollWorker.setBounds(414, 11, 239, 340);
		w_clinic.add(wrapper_scrollWorker);

		table_worker = new JTable();
		wrapper_scrollWorker.setViewportView(table_worker);

		JLabel lblPoliklinikId = new JLabel("Poliklinik ID");
		lblPoliklinikId.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		lblPoliklinikId.setBounds(259, 112, 145, 24);

		w_clinic.add(lblPoliklinikId);

		field_deleteClinic = new JTextField();
		field_deleteClinic.setColumns(10);
		field_deleteClinic.setBounds(259, 138, 145, 24);
		field_deleteClinic.setEditable(false);
		w_clinic.add(field_deleteClinic);

		table_clinic.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					field_deleteClinic.setText(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				} catch (Exception e1) {

				}
			}
		});

		JButton btn_deleteClinic = new JButton("Sil");
		btn_deleteClinic.setBounds(259, 172, 145, 32);
		w_clinic.add(btn_deleteClinic);

		JComboBox select_doctor = new JComboBox();
		for (int i = 0; i < ChiefPhysician.getdoctorList().size(); i++) {
			select_doctor.addItem(new Item(ChiefPhysician.getdoctorList().get(i).getId(),
					ChiefPhysician.getdoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
		});
		select_doctor.setBounds(259, 279, 145, 32);
		w_clinic.add(select_doctor);

		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_clinic.getSelectedRow();
				if (selectedRow >= 0) {
					String selectedClinic = table_clinic.getModel().getValueAt(selectedRow, 0).toString();
					int selectedClinicID = Integer.parseInt(selectedClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = ChiefPhysician.addWorker(doctorItem.getKey(), selectedClinicID);
						if (control) {
							Helper.showMessage("successfully");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < ChiefPhysician.getClinicDoctorList(selectedClinicID).size(); i++) {
								workerData[0] = ChiefPhysician.getClinicDoctorList(selectedClinicID).get(i).getId();
								workerData[1] = ChiefPhysician.getClinicDoctorList(selectedClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);
						} else {
							Helper.showMessage("Error");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMessage("Lütfen bir poliklinik seçiniz.");
				}
			}
		});
		btn_addWorker.setBounds(259, 319, 145, 32);
		w_clinic.add(btn_addWorker);

		JLabel lblPoliklinikAd_1 = new JLabel("Poliklinik Adı");
		lblPoliklinikAd_1.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		lblPoliklinikAd_1.setBounds(259, 214, 145, 24);
		w_clinic.add(lblPoliklinikAd_1);

		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_clinic.getSelectedRow();
				if (selectedRow >= 0) {
					String selectedClinic = table_clinic.getModel().getValueAt(selectedRow, 0).toString();
					int selectedClinicID = Integer.parseInt(selectedClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < ChiefPhysician.getClinicDoctorList(selectedClinicID).size(); i++) {
							workerData[0] = ChiefPhysician.getClinicDoctorList(selectedClinicID).get(i).getId();
							workerData[1] = ChiefPhysician.getClinicDoctorList(selectedClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				} else {
					Helper.showMessage("Lütfen bir poliklinik seçiniz.");
				}
			}
		});
		btn_workerSelect.setBounds(259, 237, 145, 32);
		w_clinic.add(btn_workerSelect);
		btn_deleteClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field_deleteClinic.getText().length() == 0) {
					Helper.showMessage("Lütfen geçerli bir poliklinik id giriniz.");
				} else {
					if (Helper.confirm("sure")) {
						int selectedID = Integer.parseInt(field_deleteClinic.getText());
						try {
							boolean control = clinic.deleteClinic(selectedID);
							if (control) {
								Helper.showMessage("successfully");
								field_deleteClinic.setText(null);
								updateClinicModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}

	public void updateDoctorModel() throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < ChiefPhysician.getdoctorList().size(); i++) {
			doctorListData[0] = ChiefPhysician.getdoctorList().get(i).getId();
			doctorListData[1] = ChiefPhysician.getdoctorList().get(i).getName();
			doctorListData[2] = ChiefPhysician.getdoctorList().get(i).getTcNo();
			doctorListData[3] = ChiefPhysician.getdoctorList().get(i).getPassword();
			doctorModel.addRow(doctorListData);
		}
	}

	public void updateClinicModel() throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}
