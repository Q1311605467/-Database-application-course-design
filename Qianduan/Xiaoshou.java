package Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Xiaoshou {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Xiaoshou() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u9500\u552E\u754C\u9762");
		frame.setBounds(100, 100, 793, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BA2\u5355\u53F7\uFF1A");//订单号标签
		label.setBounds(14, 31, 72, 18);
		frame.getContentPane().add(label);
		
		textField = new JTextField();//订单号文本框
		textField.setBounds(76, 28, 101, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u53F7\uFF1A");//图书号标签
		label_1.setBounds(14, 81, 72, 18);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();//图书号文本框
		textField_1.setBounds(76, 78, 101, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("\u987E\u5BA2\u53F7\uFF1A");//顾客号标签
		label_3.setBounds(14, 175, 72, 18);
		frame.getContentPane().add(label_3);
		
		textField_3 = new JTextField();//顾客号文本框
		textField_3.setBounds(76, 172, 101, 24);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_4 = new JLabel("\u6570\u91CF\uFF1A");//数量标签
		label_4.setBounds(14, 127, 72, 18);
		frame.getContentPane().add(label_4);
		
		textField_4 = new JTextField();//数量文本框
		textField_4.setBounds(76, 124, 101, 24);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setText("\u7279\u4EF7\u4E66\u6298\u6263\u4E3A\uFF1A\u4E03\u6298\r\n\u7279\u4EF7\u4E66\u5224\u522B\u6761\u4EF6\uFF1A\u5F53\u8BE5\u4E66\u5165\u5E93\u65F6\u95F4\u8D85\u8FC730\u5929\u65F6\uFF0C\u5224\u5B9A\u8BE5\r\n\u4E66\u4E3A\u7279\u4EF7\u4E66\r\n\r\n\u4F1A\u5458\u6298\u6263\u4E3A\uFF1A\u516B\u6298\r\n\u4F1A\u5458\u6298\u6263\u4F7F\u7528\u6761\u4EF6\uFF1A\u5F53\u4F1A\u5458\u6D88\u8D39\u91D1\u989D\u8FBE\u523050\u5143\u65F6\uFF0C\u53EF\u4EE5\r\n\u4F7F\u7528\u8BE5\u6298\u6263");
		textArea.setBounds(312, 13, 435, 199);
		frame.getContentPane().add(textArea);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 264, 733, 168);
		frame.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("\u8D2D\u4E70\u6DFB\u52A0");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";

				try {
					Class.forName(jdbcDriver);// 加载数据库驱动
					String user = "sa";// 数据库用户名
					String password = "123456";// 数据库密码
					Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
					PreparedStatement pstmt = connection.prepareStatement("{call dbo.sell_book(?,?,?,?)}");
					
					pstmt.setString(1, textField.getText());
					pstmt.setString(2, textField_1.getText());
					pstmt.setString(3, textField_3.getText());
					pstmt.setInt(4, Integer.parseInt(textField_4.getText()));
					pstmt.execute();
					
					int count = 0;
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery("SELECT * FROM Order_detail  WHERE Orderform_id =  '" + textField.getText() + "'");
					
					while (rs.next()) {
						count++;
					}

					Object[][] infomation = new Object[count][4];
					count = 0;
					rs = statement.executeQuery("SELECT * FROM Order_detail  WHERE Orderform_id =  '" + textField.getText() + "'");
					while (rs.next()) {
						
						infomation[count][0] = String.valueOf(rs.getString("Orderform_id"));
						infomation[count][1] = String.valueOf(rs.getString("Book_num"));
						infomation[count][2] = String.valueOf(rs.getString("Book_id"));
						infomation[count][3] = String.valueOf(rs.getString("Pay_total"));
						count++;

					}
					String[] title = { "订单号", "数量", "图书号","小计" };
					table = new JTable(infomation, title);
					table.setBounds(41, 122, 843, 372);
					scrollPane.setViewportView(table);/// 加表格
					
					rs.close();
					pstmt.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "程序错误");
				}
				
			}
		});
		btnNewButton.setBounds(31, 215, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Front();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(637, 225, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton button = new JButton("\u652F\u4ED8");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textField.getText().equals("")) {
					String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";
					try {
						Class.forName(jdbcDriver);// 加载数据库驱动
						String user = "sa";// 数据库用户名
						String password = "123456";// 数据库密码
						Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
						Statement statement = connection.createStatement();// 创建一个Statement对象

						ResultSet rs = statement
								.executeQuery("select SUM(Pay_total) money from Order_detail where Orderform_id = '"
										+ textField.getText() + "'");// 执行查询语句
						String money = null;
						while (rs.next()) {
							money = rs.getString("money");
							// System.out.println(rs);
						}

						// System.out.println(money);

						ZhiFu a = new ZhiFu();
						a.label_1.setText("订单号：" + textField.getText());
						a.label_2.setText("支付总额：" + money + "元");
						a.set(textField.getText(), money);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "支付失败！");
					}
				} 
				else {
					JOptionPane.showMessageDialog(null, "订单号不能为空！");
				}
			}
		});
		button.setBounds(180, 215, 113, 27);
		frame.getContentPane().add(button);
		
	}
}
