package Frame;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TongJi extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */

	public TongJi() {
		setVisible(true);
		setResizable(false);
		setTitle("\u4E66\u5E97\u9500\u552E\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 150, 766, 224);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel label = new JLabel("\u7EDF\u8BA1");
		label.setFont(new Font("宋体", Font.BOLD, 30));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(300, 20, 200, 50);
		contentPane.add(label);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setFont(new Font("宋体", Font.PLAIN, 32));
		panel.setBounds(0, 80, 800, 40);
		contentPane.add(panel);

		JButton btntop_1 = new JButton("\u4E66\u7C4D\u7C7B\u578B\u9500\u552ETop5");
		btntop_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";// 声明数据库DRMSDB的URL

				try {
					Class.forName(jdbcDriver);// 加载数据库驱动
					String user = "sa";// 数据库用户名
					String password = "123456";// 数据库密码
					Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
					int count = 0;
					Statement statement = connection.createStatement();// 创建一个Statement对象
					ResultSet rs;
					rs = statement.executeQuery(
							"select ROW_NUMBER() OVER (ORDER BY SUM(Orderform.Supplier_total) DESC)'rowno',Book.Book_kind,SUM(Orderform.Supplier_total)'number' from Book,Orderform,Order_detail,Customer where Book.Book_id = Order_detail.Book_id and Order_detail.Orderform_id = Orderform.Orderform_id and Customer.Customer_id = Orderform.Customer_id group by Book.Book_kind");// 执行查询语句
					while (rs.next()) {
						count++;
					}
					rs = statement.executeQuery(
							"select ROW_NUMBER() OVER (ORDER BY SUM(Orderform.Supplier_total) DESC)'rowno',Book.Book_kind,SUM(Orderform.Supplier_total)'number' from Book,Orderform,Order_detail,Customer where Book.Book_id = Order_detail.Book_id and Order_detail.Orderform_id = Orderform.Orderform_id and Customer.Customer_id = Orderform.Customer_id group by Book.Book_kind");// 执行查询语句

					Object[][] infomation = new Object[count][3];
					count = 0;
					while (rs.next()) {
						infomation[count][0] = String.valueOf(rs.getString("rowno"));
						infomation[count][1] = String.valueOf(rs.getString("Book_kind"));
						infomation[count][2] = String.valueOf(rs.getString("number"));

						count++;
						if (count >= 5)
							break;
					}
					String[] title = { "Top", "书本类型", "售出量" };
					table = new JTable(infomation, title);
					table.setBounds(41, 122, 843, 372);
					scrollPane.setViewportView(table);/// 加表格
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btntop_1.setBounds(400, -2, 200, 40);
		panel.add(btntop_1);

		JButton btntop_2 = new JButton("\u987E\u5BA2\u6D88\u8D39Top10");
		btntop_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";// 声明数据库DRMSDB的URL

				try {
					Class.forName(jdbcDriver);// 加载数据库驱动
					String user = "sa";// 数据库用户名
					String password = "123456";// 数据库密码
					Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
					int count = 0;
					Statement statement = connection.createStatement();// 创建一个Statement对象
					ResultSet rs;
					rs = statement.executeQuery(
							"select ROW_NUMBER() OVER (ORDER BY  SUM(Pay_message.Pay_money) DESC)'rowno',Customer.Customer_id , SUM(Pay_message.Pay_money) Sum from Orderform,Customer,Pay_message where Pay_message.Orderform_id = Orderform.Orderform_id and Customer.Customer_id = Orderform.Customer_id group by Customer.Customer_id");// 执行查询语句
					while (rs.next()) {
						count++;
					}
					rs = statement.executeQuery(
							"select ROW_NUMBER() OVER (ORDER BY  SUM(Pay_message.Pay_money) DESC)'rowno',Customer.Customer_id , SUM(Pay_message.Pay_money) Sum from Orderform,Customer,Pay_message where Pay_message.Orderform_id = Orderform.Orderform_id and Customer.Customer_id = Orderform.Customer_id group by Customer.Customer_id");// 执行查询语句
				
					Object[][] infomation = new Object[count][3];
					count = 0;
					while (rs.next()) {
						infomation[count][0] = String.valueOf(rs.getString("rowno"));
						infomation[count][1] = String.valueOf(rs.getString("Customer_id"));
						infomation[count][2] = String.valueOf(rs.getString("Sum"));

						count++;
						if (count >= 10)
							break;
					}
					String[] title = { "Top", "顾客号", "购买金额" };
					table = new JTable(infomation, title);
					table.setBounds(41, 122, 843, 372);
					scrollPane.setViewportView(table);/// 加表格
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btntop_2.setBounds(600, -2, 200, 40);
		panel.add(btntop_2);

		JButton btntop = new JButton("\u4F9B\u5E94\u5546\u4F9B\u5E94Top10");
		btntop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";// 声明数据库DRMSDB的URL

				try {
					Class.forName(jdbcDriver);// 加载数据库驱动
					String user = "sa";// 数据库用户名
					String password = "123456";// 数据库密码
					Connection connection;
					connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
					int count = 0;
					Statement statement = connection.createStatement();// 创建一个Statement对象
					ResultSet rs;

					rs = statement.executeQuery(
							"select ROW_NUMBER() OVER (ORDER BY SUM(Orderform.Supplier_total)desc)'rowno',Supplier.Supplier_id,Supplier_name,SUM(Orderform.Supplier_total) s from Supplier  ,Orderform  where(Supplier.Supplier_id=Orderform.Supplier_id) Group by Supplier.Supplier_id,Supplier_name; ");// 执行查询语句
					while (rs.next()) {
						count++;
					}
					rs = statement.executeQuery(
							"select ROW_NUMBER() OVER (ORDER BY SUM(Orderform.Supplier_total)desc)'rowno',Supplier.Supplier_id,Supplier_name,SUM(Orderform.Supplier_total) s from Supplier  ,Orderform  where(Supplier.Supplier_id=Orderform.Supplier_id) Group by Supplier.Supplier_id,Supplier_name; ");// 执行查询语句

					Object[][] infomation = new Object[count][4];
					count = 0;

					while (rs.next()) {
						infomation[count][0] = String.valueOf(rs.getString("rowno"));
						infomation[count][1] = String.valueOf(rs.getString("Supplier_id"));

						infomation[count][2] = String.valueOf(rs.getString("Supplier_name"));

						infomation[count][3] = String.valueOf(rs.getString("s"));

						count++;
						if (count == 10) {
							break;
						}
					}

					String[] title = { "Top","供应商号", "供应商名字", "供应数量" };
					table = new JTable(infomation, title);
					table.setBounds(41, 122, 843, 372);
					scrollPane.setViewportView(table);/// 加表格
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "程序错误");
				}
			}
		});
		btntop.setBounds(0, -2, 200, 40);
		panel.add(btntop);

		JButton btnNewButton = new JButton("\u4E66\u672C\u9500\u552E\u91CFTop10");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";// 声明数据库DRMSDB的URL

				try {
					Class.forName(jdbcDriver);// 加载数据库驱动
					String user = "sa";// 数据库用户名
					String password = "123456";// 数据库密码
					Connection connection;
					connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
					int count = 0;
					Statement statement = connection.createStatement();// 创建一个Statement对象
					ResultSet rs;

					rs = statement.executeQuery(
							"select ROW_NUMBER() OVER (ORDER BY SUM(Order_detail.Book_num)desc)'rowno',Book.Book_id,Book.Book_name,SUM(Order_detail.Book_num) s from Book,Order_detail,Orderform where Book.Book_id=Order_detail.Book_id and Order_detail.Orderform_id=Orderform.Orderform_id and Orderform.Customer_id is not null group by Book.Book_id,Book.Book_name; ");// 执行查询语句
					while (rs.next()) {
						count++;
					}
					rs = statement.executeQuery(
							"select ROW_NUMBER() OVER (ORDER BY SUM(Order_detail.Book_num)desc)'rowno',Book.Book_id,Book.Book_name,SUM(Order_detail.Book_num) s from Book,Order_detail,Orderform where Book.Book_id=Order_detail.Book_id and Order_detail.Orderform_id=Orderform.Orderform_id and Orderform.Customer_id is not null group by Book.Book_id,Book.Book_name; ");// 执行查询语句

					Object[][] infomation = new Object[count][4];
					count = 0;

					while (rs.next()) {
						infomation[count][0] = String.valueOf(rs.getString("rowno"));
						infomation[count][1] = String.valueOf(rs.getString("Book_id"));

						infomation[count][2] = String.valueOf(rs.getString("Book_name"));

						infomation[count][3] = String.valueOf(rs.getString("s"));

						count++;
						if (count == 11) {
							break;
						}
					}

					String[] title = { "Top","图书号", "图书名", "供应数量" };
					table = new JTable(infomation, title);
					table.setBounds(41, 122, 843, 372);
					scrollPane.setViewportView(table);/// 加表格
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "程序错误");
				}
			}
		});

		btnNewButton.setBounds(200, -2, 200, 40);
		panel.add(btnNewButton);

		JButton button = new JButton("\u8FD4\u56DE");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Front();
				setVisible(false);
			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 20));
		button.setBounds(580, 437, 200, 40);
		contentPane.add(button);
	}
}
