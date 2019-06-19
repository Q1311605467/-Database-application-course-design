package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;
public class Gukezhuce {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Gukezhuce() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u987E\u5BA2\u6CE8\u518C");
		frame.setBounds(200, 300, 458, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u987E\u5BA2\u53F7\uFF1A");//�˿ͺű���
		label.setFont(new Font("����", Font.PLAIN, 20));
		label.setBounds(43, 39, 94, 50);
		frame.getContentPane().add(label);
		
		textField = new JTextField();//�˿ͺ��ı���
		textField.setFont(new Font("����", Font.PLAIN, 20));
		textField.setBounds(170, 45, 173, 38);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");//�˿���ϵ�绰����
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setBounds(43, 118, 113, 46);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();//�˿���ϵ�绰�ı���
		textField_1.setFont(new Font("����", Font.PLAIN, 20));
		textField_1.setBounds(170, 122, 173, 38);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");//ע�����ť
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";// �������ݿ�DRMSDB��URL
				/// ��������������䣬��
				if(textField.getText().equals(""))
				{JOptionPane.showMessageDialog(null, "�˿ͺŲ���Ϊ��");}
				else {
					try {
					Class.forName(jdbcDriver);// �������ݿ�����
					String user = "sa";// ���ݿ��û���
					String password = "123456";// ���ݿ�����
					Connection connection = DriverManager.getConnection(connectDB, user, password);// �������ݿ����ӣ�������Ӷ���

					Statement statement = connection.createStatement();// ����һ��Statement����

					String name1 = textField.getText();
					String lianxi = textField_1.getText();
					statement.executeUpdate("insert into Customer values('"+name1+"', 0, 0, '"+lianxi+"')");// ִ��ע�����
					JOptionPane.showMessageDialog(null,"ע��ɹ�");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ע��ʧ��");
				}
				}
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 24));
		btnNewButton.setBounds(60, 198, 173, 50);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Front();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 24));
		btnNewButton_1.setBounds(272, 198, 123, 50);
		frame.getContentPane().add(btnNewButton_1);
	}

}
