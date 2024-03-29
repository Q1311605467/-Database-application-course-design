package Frame;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.FlowLayout;
import java.awt.CardLayout;
//import java.lang.Float;
public class JinHuo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTable table_1;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JinHuo() {
		setResizable(false);
		setTitle("\u4E66\u5E97\u9500\u552E\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u8FDB\u8D27");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(346, 13, 93, 34);
		contentPane.add(label);

		JPanel panel = new JPanel();
		panel.setBounds(187, 39, 454, 34);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton button_1 = new JButton("\u8FDB\u8D27\u5DF2\u6709\u56FE\u4E66");
		panel.add(button_1);

		JButton button_2 = new JButton("\u8FDB\u8D27\u65B0\u7684\u56FE\u4E66");
		panel.add(button_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 81, 790, 472);
		contentPane.add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, "name_594357503791412");
		panel_2.setLayout(null);

		JLabel label_4 = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(24, 31, 72, 18);
		panel_2.add(label_4);

		textField_3 = new JTextField();					//订单号
		textField_3.setBounds(110, 28, 86, 24);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		JLabel label_5 = new JLabel("\u56FE\u4E66\u53F7\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(228, 31, 72, 18);
		panel_2.add(label_5);

		textField_4 = new JTextField();					//图书号
		textField_4.setBounds(295, 28, 86, 24);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		JLabel label_6 = new JLabel("\u4E66\u540D\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(397, 31, 72, 18);
		panel_2.add(label_6);

		textField_5 = new JTextField();					///书名
		textField_5.setBounds(465, 28, 86, 24);
		panel_2.add(textField_5);
		textField_5.setColumns(10);

		JLabel label_7 = new JLabel("\u8FDB\u4EF7\uFF1A");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(561, 31, 51, 18);
		panel_2.add(label_7);

		textField_7 = new JTextField();					//进价
		textField_7.setBounds(603, 28, 39, 24);
		panel_2.add(textField_7);
		textField_7.setColumns(10);

		JLabel label_9 = new JLabel("\u51FA\u7248\u5546\uFF1A");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(24, 62, 72, 18);
		panel_2.add(label_9);

		textField_8 = new JTextField();					//出版商
		textField_8.setBounds(110, 59, 86, 24);
		panel_2.add(textField_8);
		textField_8.setColumns(10);

		JLabel label_10 = new JLabel("\u4F9B\u5E94\u5546\u53F7\uFF1A");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(210, 62, 86, 18);
		panel_2.add(label_10);

		textField_9 = new JTextField();					//出版商号
		textField_9.setBounds(295, 59, 86, 24);
		panel_2.add(textField_9);
		textField_9.setColumns(10);

		JLabel label_11 = new JLabel("\u552E\u4EF7\uFF1A");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(647, 31, 56, 18);
		panel_2.add(label_11);

		textField_10 = new JTextField();                  ///售价
		textField_10.setBounds(704, 28, 39, 24);
		panel_2.add(textField_10);
		textField_10.setColumns(10);

		JLabel label_12 = new JLabel("\u4F9B\u5E94\u91CF\uFF1A");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(397, 62, 72, 18);
		panel_2.add(label_12);

		textField_11 = new JTextField();///供应量
		textField_11.setBounds(465, 59, 39, 24);
		panel_2.add(textField_11);
		textField_11.setColumns(10);

//		@Orderform_id varchar(20),--订单号
//		@Book_id varchar(20),--图书号
//		@Book_name varchar(20),--书名
//		@Book_in_price float,--进价
//		@Book_out_price float,--售价
//		@Supplier_name varchar(20),--出版商
//		@Supplier_id varchar(20),--供应商号
//		--Book_storage_time datetime,--图书入库时间
//		@Book_stock int--库存/进货数量
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 130, 762, 290);
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton button_4 = new JButton("\u6DFB\u52A0\u8BA2\u5355");///订单
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent f) {
				
				
				String Orderform_id = textField_3.getText();	 
				 String Book_id = textField_4.getText();
				 String Book_name = textField_5.getText();
				 Float Book_in_price = Float.parseFloat(textField_7.getText());
				 Float Book_out_price = Float.parseFloat(textField_10.getText());
				 String Supplier_name = textField_8.getText();
				 String Supplier_id = textField_9.getText();
				 int Book_stock = Integer.parseInt(textField_11.getText());
				 
				 String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";

					try {
						Class.forName(jdbcDriver);// 加载数据库驱动
						String user = "sa";// 数据库用户名
						String password = "123456";// 数据库密码
						Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
						
						int count1 = 0;///判断是否有这个供应商
						int count2 = 0;
						Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(
								"SELECT * FROM Book WHERE Book_id = '" + textField_4.getText() + "'");
						while (rs.next()) {
							count2++;
						}
						 rs = statement.executeQuery(
								"SELECT * FROM Supplier WHERE Supplier_id = '" + textField_9.getText() + "'");
						 while (rs.next()) {
								count1++;
							}
						if(count1 == 0)
						{
							JOptionPane.showMessageDialog(null, "没有该供应商");
							throw new Exception();
						
						}
						if(count2 != 0) {
							JOptionPane.showMessageDialog(null, "已经有这本图书");
							throw new Exception();
						}
						if(count1 != 0 && count2 ==0) {
						PreparedStatement pstmt = connection.prepareStatement("{call dbo.Supply_NonExistentBook(?,?,?,?,?,?,?,?,?)}");
						pstmt.setString(1, Orderform_id);
						pstmt.setString(2, Book_id);
						pstmt.setString(3, Book_name);
						pstmt.setFloat(4, Book_in_price);
						pstmt.setFloat(5, Book_out_price);
						pstmt.setString(6,Supplier_name);
						pstmt.setString(7, Supplier_id);
						pstmt.setInt(8, Book_stock);
						pstmt.setString(9, textField_6.getText());
						
						pstmt.execute();
						
						int count = 0;
					
						 rs = statement.executeQuery("SELECT * FROM Order_detail  WHERE Orderform_id =  '" + textField_3.getText() + "'");
						while (rs.next()) {
							count++;
						}
						Object[][] infomation = new Object[count][4];
						count = 0;
						rs = statement.executeQuery("SELECT * FROM Order_detail  WHERE Orderform_id =  '" + textField_3.getText() + "'");
						while (rs.next()) {
							
							infomation[count][0] = String.valueOf(rs.getString("Orderform_id"));
							infomation[count][1] = String.valueOf(rs.getString("Book_num"));
							infomation[count][2] = String.valueOf(rs.getString("Book_id"));
							infomation[count][3] = String.valueOf(rs.getString("Pay_total"));
							count++;

						}
					
						String[] title = { "订单号", "数量", "图书号","小计" };
						table_1 = new JTable(infomation, title);
						table_1.setBounds(41, 122, 843, 372);
						scrollPane_1.setViewportView(table_1);/// 加表格
						System.out.println(count+2);
						rs.close();
						pstmt.close();
					}
					}
						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "程序错误");
						}

				
				}
		});

		button_4.setBounds(627, 90, 113, 27);
		panel_2.add(button_4);

		

		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, "name_594726193135799");
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
		label_1.setBounds(140, 27, 72, 18);
		panel_1.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);

		textField = new JTextField();
		textField.setBounds(243, 24, 282, 24);
		panel_1.add(textField);
		textField.setColumns(10);

		JLabel label_2 = new JLabel("\u56FE\u4E66\u53F7\uFF1A");
		label_2.setBounds(140, 59, 72, 25);
		panel_1.add(label_2);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label_3 = new JLabel("\u6570\u91CF\uFF1A");
		label_3.setBounds(140, 97, 72, 18);
		panel_1.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);

		textField_1 = new JTextField();
		textField_1.setBounds(243, 61, 282, 24);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(243, 94, 86, 24);
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		JButton button_3 = new JButton("\u6DFB\u52A0\u8BA2\u5355");
		button_3.setBounds(412, 93, 113, 27);
		panel_1.add(button_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 130, 762, 290);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		panel_2.add(scrollPane_1);
		
		JButton button = new JButton("\u8FD4\u56DE");//返回按钮
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Front();
				setVisible(false);
			}
		});
		button.setBounds(14, 13, 113, 27);
		contentPane.add(button);


		panel_1.setVisible(true);
		panel_2.setVisible(false);

		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.setVisible(true);
				panel_2.setVisible(false);
			}
		});

		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
			}
		});

		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";

				try {
					Class.forName(jdbcDriver);// 加载数据库驱动
					String user = "sa";// 数据库用户名
					String password = "123456";// 数据库密码
					Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
					int count1 = 0;///判断是否有这本图书
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(
							"SELECT * FROM Book WHERE Book_id = '" + textField_1.getText() + "'");
					while (rs.next()) {
						count1++;
					}
					if(count1 == 0)
					{
						JOptionPane.showMessageDialog(null, "没有该图书");
						throw new Exception();
						
					}
					
					if(count1!=0) {
						PreparedStatement pstmt = connection.prepareStatement("{call dbo.Supply_ExistentBook(?,?,?)}");
				
					pstmt.setString(1, textField.getText());
					pstmt.setString(3, textField_1.getText());
					pstmt.setInt(2, Integer.parseInt(textField_2.getText()));
			
					pstmt.execute();
					
					int count = 0;
					 rs = statement.executeQuery("SELECT * FROM Order_detail  WHERE Orderform_id =  '" + textField.getText() + "'");
					
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
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "程序错误");
				}
			}
		});
		
		JButton button_6 = new JButton("\u652F\u4ED8");
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textField_3.getText().equals("")) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";
				try {
					Class.forName(jdbcDriver);// 加载数据库驱动
					String user = "sa";// 数据库用户名
					String password = "123456";// 数据库密码
					Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
					Statement statement = connection.createStatement();// 创建一个Statement对象

					ResultSet rs = statement
							.executeQuery("select SUM(Pay_total) money from Order_detail where Orderform_id = '"+ textField_3.getText() + "'");// 执行查询语句
					
					String money = null;
					while (rs.next()) {
						money = rs.getString("money");
						// System.out.println(rs);
					}

					// System.out.println(money);

					ZhiFu a = new ZhiFu();
					a.label_1.setText("订单号：" + textField_3.getText());
					a.label_2.setText("支付总额：" + money + "元");
					// String s = "insert into Pay_message values ('" +
					// textField.getText() + "',"+money+",'" +
					// a.comboBox.getSelectedItem() + "')";
					a.set(textField_3.getText(), money);

					// rs = statement.executeQuery(s);
					//
					// while(rs.next()) {
					// System.out.println(rs);
					// }

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "支付失败！");
				}
				} else {
					JOptionPane.showMessageDialog(null, "订单号不能为空！");
				}

			}
		});
		button_6.setBounds(663, 432, 113, 27);
		panel_2.add(button_6);
		
		JLabel lblNewLabel = new JLabel("\u7C7B\u522B\uFF1A");
		lblNewLabel.setBounds(540, 62, 72, 18);
		panel_2.add(lblNewLabel);
		
		textField_6 = new JTextField();
		textField_6.setBounds(592, 59, 100, 24);
		panel_2.add(textField_6);
		textField_6.setColumns(10);
		JButton button_5 = new JButton("\u652F\u4ED8");
		button_5.addMouseListener(new MouseAdapter() {
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
						// String s = "insert into Pay_message values ('" +
						// textField.getText() + "',"+money+",'" +
						// a.comboBox.getSelectedItem() + "')";
						a.set(textField.getText(), money);

						// rs = statement.executeQuery(s);
						//
						// while(rs.next()) {
						// System.out.println(rs);
						// }

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "支付失败！");
					}
				} else {
					JOptionPane.showMessageDialog(null, "订单号不能为空！");
				}
			}
		});

		button_5.setBounds(663, 432, 113, 27);
		panel_1.add(button_5);

	}
}