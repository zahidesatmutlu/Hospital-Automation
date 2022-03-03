package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private JPanel w_pane;
	private static Doctor doctor = new Doctor();
	private JTable table_workhour;
	private DefaultTableModel workHourModel;
	private Object[] workHourData = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoctorGUI(Doctor doctor) throws SQLException {

		workHourModel = new DefaultTableModel();
		Object[] columnWorkHour = new Object[2];
		columnWorkHour[0] = "ID";
		columnWorkHour[1] = "Tarih";
		workHourModel.setColumnIdentifiers(columnWorkHour);
		workHourData = new Object[2];
		for (int i = 0; i < doctor.getWorkHourList(doctor.getId()).size(); i++) {
			workHourData[0] = doctor.getWorkHourList(doctor.getId()).get(i).getId();
			workHourData[1] = doctor.getWorkHourList(doctor.getId()).get(i).getWorkDate();
			workHourModel.addRow(workHourData);
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

		JLabel label_welcome = new JLabel("Hoşgeldiniz, Dr. " + doctor.getName());
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
		w_tabpane.setBounds(10, 60, 668, 390);
		w_pane.add(w_tabpane);

		JPanel w_workhour = new JPanel();
		w_workhour.setBackground(Color.WHITE);
		w_tabpane.addTab("Çalışma Saatleri", null, w_workhour, null);
		w_workhour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 10, 130, 20);
		w_workhour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(
				new String[] { "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
						"13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00" }));
		select_time.setBounds(150, 10, 60, 20);
		w_workhour.add(select_time);

		JButton btn_addWorkHour = new JButton("Ekle");
		btn_addWorkHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				String date = "";
				try {
					date = sdf.format(select_date.getDate());
				} catch (Exception e2) {

				}
				if (date.length() == 0) {
					Helper.showMessage("Lütfen geçerli bir tarih giriniz.");
				} else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {
						boolean control = doctor.addWorkHour(doctor.getId(), doctor.getName(), selectDate);
						if (control) {
							Helper.showMessage("successfully");
							updateWorkHourModel(doctor);
						} else {
							Helper.showMessage("Error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addWorkHour.setBounds(220, 10, 65, 20);
		w_workhour.add(btn_addWorkHour);

		JScrollPane w_scrollWorkHour = new JScrollPane();
		w_scrollWorkHour.setBounds(10, 40, 643, 313);
		w_workhour.add(w_scrollWorkHour);

		table_workhour = new JTable(workHourModel);
		w_scrollWorkHour.setViewportView(table_workhour);

		JButton btn_deleteWorkHour = new JButton("Sil");
		btn_deleteWorkHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_workhour.getSelectedRow();
				if (selectedRow >= 0) {
					String selectRow = table_workhour.getModel().getValueAt(selectedRow, 0).toString();
					int selectedID = Integer.parseInt(selectRow);
					boolean control;
					try {
						control = doctor.deleteWorkHour(selectedID);
						if (control) {
							Helper.showMessage("successfully");
							updateWorkHourModel(doctor);
						} else {
							Helper.showMessage("Error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_deleteWorkHour.setBounds(588, 10, 65, 20);
		w_workhour.add(btn_deleteWorkHour);
	}

	public void updateWorkHourModel(Doctor doctor) throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_workhour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < doctor.getWorkHourList(doctor.getId()).size(); i++) {
			workHourData[0] = doctor.getWorkHourList(doctor.getId()).get(i).getId();
			workHourData[1] = doctor.getWorkHourList(doctor.getId()).get(i).getWorkDate();
			workHourModel.addRow(workHourData);
		}
	}
}
