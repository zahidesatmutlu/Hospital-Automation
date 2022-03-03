package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Patient;
import Model.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField field_name;
	private JTextField field_tcNo;
	private JPasswordField field_password;
	private Patient patient = new Patient();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel label1 = new JLabel("Ad Soyad");
		label1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		label1.setBounds(10, 20, 155, 24);
		w_pane.add(label1);

		field_name = new JTextField();
		field_name.setColumns(10);
		field_name.setBounds(10, 44, 262, 27);
		w_pane.add(field_name);

		field_tcNo = new JTextField();
		field_tcNo.setColumns(10);
		field_tcNo.setBounds(10, 105, 262, 27);
		w_pane.add(field_tcNo);

		JLabel lblTcNumaras = new JLabel("T.C. Numarası");
		lblTcNumaras.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblTcNumaras.setBounds(10, 81, 155, 24);
		w_pane.add(lblTcNumaras);

		JLabel lblifre = new JLabel("Şifre");
		lblifre.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblifre.setBounds(10, 142, 155, 24);
		w_pane.add(lblifre);

		field_password = new JPasswordField();
		field_password.setBounds(10, 168, 262, 27);
		w_pane.add(field_password);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field_tcNo.getText().length() == 0 || field_password.getText().length() == 0
						|| field_name.getText().length() == 0) {
					Helper.showMessage("fill_up");
				} else {
					try {
						boolean control = patient.register(field_tcNo.getText(), field_password.getText(),
								field_name.getText());
						if (control) {
							Helper.showMessage("successfully");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						} else {
							Helper.showMessage("Error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setBounds(10, 205, 262, 32);
		w_pane.add(btn_register);

		JButton btn_goback = new JButton("Geri Dön");
		btn_goback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_goback.setBounds(10, 247, 262, 32);
		w_pane.add(btn_goback);
	}
}
