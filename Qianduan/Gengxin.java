package Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.CardLayout;
import javax.swing.BoxLayout;

import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
public class Gengxin {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Gengxin() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u4F1A\u5458\u6CE8\u518C");//��������Աע��
		frame.setBounds(100, 100, 706, 509);//������Ա����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u4F1A\u5458\u6CE8\u518C");//ע�����
		label.setFont(new Font("����", Font.PLAIN, 32));//���������С
		label.setHorizontalAlignment(SwingConstants.CENTER);//����
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);//��ֱ����
		label.setBounds(50, 13, 573, 56);//�����ٴ����е�λ��
		frame.getContentPane().add(label);//��ӽ�����
		
		textField = new JTextField();//�˿ͺ������ı���
		textField.setFont(new Font("����", Font.PLAIN, 22));
		textField.setBackground(Color.WHITE);//�ı��򱳾���ɫ
		textField.setBounds(163, 100, 237, 38);//�ı����ڴ����е�λ��
		frame.getContentPane().add(textField);//�ı�����ӽ�����
		textField.setColumns(10);//�ı����п�
		
		JLabel label_1 = new JLabel("\u987E\u5BA2\u53F7\uFF1A");//�˿ͺű���
		label_1.setBackground(Color.WHITE);//�˿ͺű�����ɫ
		label_1.setFont(new Font("����", Font.PLAIN, 22));//�����С
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(64, 100, 98, 38);
		frame.getContentPane().add(label_1);//��ӹ˿ͺű��������
		
		JButton btnNewButton = new JButton("\u5347\u7EA7");//��������ť
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
				try {
					Class.forName(jdbcDriver);// �������ݿ�����
					String user = "sa";// ���ݿ��û���
					String password = "123456";// ���ݿ�����
					Connection connection = DriverManager.getConnection(connectDB, user, password);// �������ݿ����ӣ�������Ӷ���

					Statement statement = connection.createStatement();// ����һ��Statement����

					ResultSet rs = statement
							.executeQuery("SELECT * FROM Orderform WHERE Customer_id = '" + textField.getText() + "'");// ��ѯ������¼���
					int count = 0;
					while (rs.next()) {
						count++;//��¼�ù˿͵Ķ����ܴ���
					}
					if(count>=5)//����ù˿͵Ķ����ܴ�������5����������ɻ�Ա
					{
						statement.executeUpdate("update Customer set Customer_vip= 1 where Customer_id = '" + textField.getText() + "'");// ִ���������
						
						JOptionPane.showMessageDialog(null,"�����ɹ�");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "����ʧ��");
					}
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "�������");
				}
			}
		});
		//btnNewButton.setBackground(Color.BLUE);
		//btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("����", Font.PLAIN, 27));
		btnNewButton.setBounds(414, 100, 98, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");//���ذ�ť
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Front();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 29));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(255, 358, 146, 50);
		frame.getContentPane().add(btnNewButton_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.control);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 25));
		textArea.setEditable(false);
		textArea.setText("  \u987E\u5BA2\u5347\u7EA7\u6210\u4F1A\u5458\u7684\u6761\u4EF6\uFF1A\r\n\u5728\u672C\u5E97\u8D2D\u4E70\u4E66\u7C4D\u6B21\u6570\u8FBE\u52305\u6B21");
		textArea.setBounds(149, 215, 344, 91);
		frame.getContentPane().add(textArea);
	}
}