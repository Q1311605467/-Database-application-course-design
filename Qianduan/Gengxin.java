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
		frame.setTitle("\u4F1A\u5458\u6CE8\u518C");//表名：会员注册
		frame.setBounds(100, 100, 706, 509);//创建会员窗口
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u4F1A\u5458\u6CE8\u518C");//注册标题
		label.setFont(new Font("宋体", Font.PLAIN, 32));//标题字体大小
		label.setHorizontalAlignment(SwingConstants.CENTER);//居中
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);//垂直对齐
		label.setBounds(50, 13, 573, 56);//标题再窗口中的位置
		frame.getContentPane().add(label);//添加进窗口
		
		textField = new JTextField();//顾客号输入文本框
		textField.setFont(new Font("宋体", Font.PLAIN, 22));
		textField.setBackground(Color.WHITE);//文本框背景颜色
		textField.setBounds(163, 100, 237, 38);//文本框在窗口中的位置
		frame.getContentPane().add(textField);//文本框添加进窗口
		textField.setColumns(10);//文本框列宽
		
		JLabel label_1 = new JLabel("\u987E\u5BA2\u53F7\uFF1A");//顾客号标题
		label_1.setBackground(Color.WHITE);//顾客号背景颜色
		label_1.setFont(new Font("宋体", Font.PLAIN, 22));//字体大小
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(64, 100, 98, 38);
		frame.getContentPane().add(label_1);//添加顾客号标题进窗口
		
		JButton btnNewButton = new JButton("\u5347\u7EA7");//升级键按钮
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";// 声明数据库DRMSDB的URL
				/// 上面两句驱动语句，在
				try {
					Class.forName(jdbcDriver);// 加载数据库驱动
					String user = "sa";// 数据库用户名
					String password = "123456";// 数据库密码
					Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象

					Statement statement = connection.createStatement();// 创建一个Statement对象

					ResultSet rs = statement
							.executeQuery("SELECT * FROM Orderform WHERE Customer_id = '" + textField.getText() + "'");// 查询订单记录语句
					int count = 0;
					while (rs.next()) {
						count++;//记录该顾客的订单总次数
					}
					if(count>=5)//如果该顾客的订单总次数大于5则可以升级成会员
					{
						statement.executeUpdate("update Customer set Customer_vip= 1 where Customer_id = '" + textField.getText() + "'");// 执行升级语句
						
						JOptionPane.showMessageDialog(null,"升级成功");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "升级失败");
					}
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "程序错误");
				}
			}
		});
		//btnNewButton.setBackground(Color.BLUE);
		//btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 27));
		btnNewButton.setBounds(414, 100, 98, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");//返回按钮
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Front();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 29));
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