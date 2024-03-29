package Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Chaxun extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	 int num = 0;
	 private JTextField textField_4;

	/**
	 * Create the frame.
	 */
	public Chaxun() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setTitle("\u4E66\u5E97\u9500\u552E\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u67E5\u8BE2");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 30));
		label.setBounds(300, 0, 200, 50);
		contentPane.add(label);

		JPanel panel = new JPanel();
		panel.setFont(new Font("宋体", Font.PLAIN, 32));
		panel.setBounds(0, 63, 782, 40);
		contentPane.add(panel);
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("\u4E66\u7C4D\u53F7\uFF1A");///书籍
		label_1.setBounds(37, 15, 60, 25);
		panel_1.add(label_1);

		textField = new JTextField();
		textField.setBounds(114, 15, 120, 25);
		panel_1.add(textField);
		textField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);

		JLabel label_2 = new JLabel("\u987E\u5BA2\u53F7\uFF1A");///顾客号
		label_2.setBounds(37, 15, 60, 25);
		panel_2.add(label_2);

		textField_1 = new JTextField();
		textField_1.setBounds(114, 15, 120, 25);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 254, 650, 120);
		panel_4.setLayout(null);
		
		JLabel label_3 = new JLabel("\u8BA2\u5355\u53F7\uFF1A");///订单
		label_3.setBounds(37, 15, 60, 25);
		panel_4.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(114, 15, 120, 25);
		panel_4.add(textField_2);
		textField_2.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 254, 650, 120);
		panel_5.setLayout(null);
		
		JLabel label_4 = new JLabel("\u8BA2\u5355\u53F7\uFF1A");///订单明细
		label_4.setBounds(37, 15, 60, 25);
		panel_5.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(114, 15, 120, 25);
		panel_5.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("\u4E66\u7C4D");
		btnNewButton.setBounds(186, 5, 63, 27);
		
		panel.setLayout(null);
		panel.add(btnNewButton);

		JButton button_1 = new JButton("\u987E\u5BA2");
		button_1.setBounds(308, 5, 63, 27);
		panel.add(button_1);

		JButton button_2 = new JButton("\u8BA2\u5355");
		button_2.setBounds(420, 5, 63, 27);
		panel.add(button_2);

		JButton button_3 = new JButton("\u652F\u4ED8\u4FE1\u606F");
		button_3.setBounds(542, 5, 93, 27);
		panel.add(button_3);
		
		JButton btnNewButton_1 = new JButton("\u4F9B\u5E94\u5546");//供应商按钮
		
		btnNewButton_1.setBounds(38, 5, 113, 27);
		panel.add(btnNewButton_1);
		
		JButton button_9 = new JButton("\u8FD4\u56DE");
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Front();
				setVisible(false);
			}
		});
		button_9.setFont(new Font("宋体", Font.BOLD, 20));
		button_9.setBounds(564, 120, 113, 27);
		contentPane.add(button_9);


		panel_1.setVisible(true);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 101, 555, 92);
		contentPane.add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));
		panel_3.add(panel_1);
		
		JPanel panel_6 = new JPanel();
		
		panel_6.setLayout(null);
		panel_3.add(panel_6);
		
		JButton button_8 = new JButton("\u67E5\u8BE2");
		button_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String jdbcDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		        String connectDB="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";//声明数据库DRMSDB的URL
		        ///上面两句驱动语句，在
		        try
		        {
		            Class.forName(jdbcDriver);//加载数据库驱动
		            String user="sa";//数据库用户名
		            String password="123456";//数据库密码
		            Connection connection=DriverManager.getConnection(connectDB,user,password);//建立数据库连接，获得连接对象
		               int count = 0;
		               Statement statement=connection.createStatement();//创建一个Statement对象 
		               ResultSet rs;
		              		            	
			             rs =statement.executeQuery("SELECT * FROM Supplier");//执行查询语句
			                while(rs.next()) {
			                	count++;
			                }
			             rs = statement.executeQuery("SELECT * FROM Supplier");
			   
		            Object[][] infomation = new Object[count][7];
		            count = 0;
		            while(rs.next())
		            {
		            
		            	   infomation[count][0] = String.valueOf(rs.getString("Supplier_id"));
		            	   infomation[count][1] = String.valueOf(rs.getString("Supplier_name"));
		                   infomation[count][2] = String.valueOf(rs.getString("Supplier_city"));
		                   infomation[count][3] = String.valueOf(rs.getString("Supplier_tel"));
		                    
		            	count++;
		            }
		            num ++ ;
		            String[] title = {"供应商号","供应商名字","所在城市","联系电话",};
		            table = new JTable(infomation,title);
		            table.setBounds(41, 122, 843, 372);
		            scrollPane.setViewportView(table);///加表格
		        }
		        catch(Exception e1)
		        {
		        	
		        }
			}
		});
		button_8.setFont(new Font("宋体", Font.PLAIN, 18));
		button_8.setBounds(282, 14, 113, 27);
		panel_6.add(button_8);
		
		JLabel label_5 = new JLabel("\u67E5\u8BE2\u6240\u6709\u7684\u4F9B\u5E94\u5546\uFF1A");
		label_5.setFont(new Font("宋体", Font.PLAIN, 21));
		label_5.setBounds(62, 12, 206, 28);
		panel_6.add(label_5);
		panel_3.add(panel_1);
		
		JButton button = new JButton("\u6309\u4E66\u7C4D\u53F7\u67E5\u8BE2");///查询书籍号
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String jdbcDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		        String connectDB="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";//声明数据库DRMSDB的URL
		        ///上面两句驱动语句，在
		        try
		        {
		            Class.forName(jdbcDriver);//加载数据库驱动
		            String user="sa";//数据库用户名
		            String password="123456";//数据库密码
		            Connection connection=DriverManager.getConnection(connectDB,user,password);//建立数据库连接，获得连接对象
		               int count = 0;
		               Statement statement=connection.createStatement();//创建一个Statement对象 
		               ResultSet rs;
		               if(textField.getText().equals(""))
			            {		            	
			             rs =statement.executeQuery("SELECT * FROM Book");//执行查询语句
			                while(rs.next()) {
			                	count++;
			                }
			             rs = statement.executeQuery("SELECT * FROM Book");
			            }
		               else {
		            	   rs =statement.executeQuery("SELECT * FROM Book where Book_id = '"+textField.getText()+"'");//执行查询语句
			                while(rs.next()) {
			                	count++;
			                }
			                rs =statement.executeQuery("SELECT * FROM Book where Book_id = '"+textField.getText()+"'");//执行查询语句
		               }
		            Object[][] infomation = new Object[count][8];
		            count = 0;
		            while(rs.next())
		            {
		            
		            	   infomation[count][0] = String.valueOf(rs.getString("Book_id"));
		            	   infomation[count][1] = String.valueOf(rs.getString("Book_in_price"));
		                   infomation[count][2] = String.valueOf(rs.getString("Book_out_price"));
		                   infomation[count][3] = String.valueOf(rs.getString("Book_name"));
		                   infomation[count][4] = String.valueOf(rs.getString("Supplier_name"));
		                   infomation[count][5] = String.valueOf(rs.getString("Book_storage_time"));
		                   infomation[count][6] = String.valueOf(rs.getString("Book_stock")); 
		                   infomation[count][7] = String.valueOf(rs.getString("Book_kind"));
		            	count++;
		            }
		            num ++ ;
		            String[] title = {"图书号","进价","图书售价","书名","供应商名字","图书入库时间","库存","类别"};
		            table = new JTable(infomation,title);
		            table.setBounds(41, 122, 843, 372);
		            scrollPane.setViewportView(table);///加表格
		        }
		        catch(Exception e1)
		        {
		        	JOptionPane.showMessageDialog(null, "程序错误");
		        }
			}
				
		
		});
		button.setBounds(282, 14, 123, 27);
		panel_1.add(button);
		
		JLabel label_6 = new JLabel("\u7C7B\u522B\uFF1A");
		label_6.setBounds(37, 53, 72, 18);
		panel_1.add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setBounds(114, 50, 120, 24);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JButton button_10 = new JButton("\u6309\u7C7B\u522B\u67E5\u8BE2");
		button_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String jdbcDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		        String connectDB="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";//声明数据库DRMSDB的URL
		        ///上面两句驱动语句，在
		        try
		        {
		            Class.forName(jdbcDriver);//加载数据库驱动
		            String user="sa";//数据库用户名
		            String password="123456";//数据库密码
		            Connection connection=DriverManager.getConnection(connectDB,user,password);//建立数据库连接，获得连接对象
		               int count = 0;
		               Statement statement=connection.createStatement();//创建一个Statement对象 
		               ResultSet rs;
		               
		              
		               rs =statement.executeQuery("SELECT * FROM Book where Book_kind = '"+textField_4.getText()+"'");//执行查询语句
			           while(rs.next()) 
			           {
			                count++;
			           }
			           rs =statement.executeQuery("SELECT * FROM Book where Book_kind = '"+textField_4.getText()+"'");//执行查询语句
		             
		            Object[][] infomation = new Object[count][8];
		            count = 0;
		            while(rs.next())
		            {
		            
		            	   infomation[count][0] = String.valueOf(rs.getString("Book_id"));
		            	   infomation[count][1] = String.valueOf(rs.getString("Book_in_price"));
		                   infomation[count][2] = String.valueOf(rs.getString("Book_out_price"));
		                   infomation[count][3] = String.valueOf(rs.getString("Book_name"));
		                   infomation[count][4] = String.valueOf(rs.getString("Supplier_name"));
		                   infomation[count][5] = String.valueOf(rs.getString("Book_storage_time"));
		                   infomation[count][6] = String.valueOf(rs.getString("Book_stock"));
		                   infomation[count][7] = String.valueOf(rs.getString("Book_kind"));
		            	count++;
		            }
		            num ++ ;
		            String[] title = {"图书号","进价","图书售价","书名","供应商名字","图书入库时间","库存","类别"};
		            table = new JTable(infomation,title);
		            table.setBounds(41, 122, 843, 372);
		            scrollPane.setViewportView(table);///加表格
		        }
		        catch(Exception e1)
		        {
		        	JOptionPane.showMessageDialog(null, "程序错误");
		        }
				
			}
		});
		button_10.setBounds(282, 49, 123, 27);
		panel_1.add(button_10);
		panel_3.add(panel_2);
		
		JButton button_4 = new JButton("\u67E5\u8BE2"); //查询顾客号
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String jdbcDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		        String connectDB="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";//声明数据库DRMSDB的URL
		        ///上面两句驱动语句，在
		        try
		        {
		            Class.forName(jdbcDriver);//加载数据库驱动
		            String user="sa";//数据库用户名
		            String password="123456";//数据库密码
		            Connection connection=DriverManager.getConnection(connectDB,user,password);//建立数据库连接，获得连接对象
		            int count = 0;
		              Statement statement=connection.createStatement();//创建一个Statement对象 
		            ResultSet rs;
		               if(textField_1.getText().equals(""))
			            {		            	
			             rs =statement.executeQuery("SELECT * FROM Customer");//执行查询语句
			                while(rs.next()) {
			                	count++;
			                }
			             rs = statement.executeQuery("SELECT * FROM Customer");
			            }
		               else {
		            	   rs =statement.executeQuery("SELECT * FROM Customer where Customer_id = '"+textField_1.getText()+"'");//执行查询语句
			                while(rs.next()) {
			                	count++;
			                }
			                rs =statement.executeQuery("SELECT * FROM Customer where Customer_id = '"+textField_1.getText()+"'");//执行查询语句
		               }
		            Object[][] infomation = new Object[count][4];
		            count = 0;
		            while(rs.next())
		            {
		            	
		            	   infomation[count][0] = String.valueOf(rs.getString("Customer_id"));
		            	   infomation[count][1] = String.valueOf(rs.getString("Customer_vip"));
		                   infomation[count][2] = String.valueOf(rs.getString("Customer_vip_money"));
		                   infomation[count][3] = String.valueOf(rs.getString("Customer_tel"));
		            	     count++;
		      
		            }
		            String[] title = {"顾客号","是否会员","会员卡金额","联系电话"};
		            table = new JTable(infomation,title);
		            table.setBounds(41, 122, 843, 372);
		            
		            scrollPane.setViewportView(table);///加表格
		        }
		        catch(Exception e1)
		        {
		        	
		        }
			}
		});
		button_4.setBounds(282, 14, 113, 27);
		panel_2.add(button_4);
		panel_3.add(panel_4);
		
		JButton button_5 = new JButton("\u67E5\u8BE2");
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				String jdbcDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		        String connectDB="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";//声明数据库DRMSDB的URL
		        ///上面两句驱动语句，在
		        try
		        {
		            Class.forName(jdbcDriver);//加载数据库驱动
		            String user="sa";//数据库用户名
		            String password="123456";//数据库密码
		            Connection connection=DriverManager.getConnection(connectDB,user,password);//建立数据库连接，获得连接对象
		            int count = 0;
		              Statement statement=connection.createStatement();//创建一个Statement对象 
		            ResultSet rs;
		               if(textField_2.getText().equals(""))
			            {		            	
			             rs =statement.executeQuery("SELECT * FROM Orderform");//执行查询语句
			                while(rs.next()) {
			                	count++;
			                }
			             rs = statement.executeQuery("SELECT * FROM Orderform");
			            }
		               else {
		            	   rs =statement.executeQuery("SELECT * FROM Orderform where Orderform_id = '"+textField_2.getText()+"'");//执行查询语句
			                while(rs.next()) {
			                	count++;
			                }
			                rs =statement.executeQuery("SELECT * FROM Orderform where Orderform_id = '"+textField_2.getText()+"'");//执行查询语句
		               }
		            Object[][] infomation = new Object[count][5];
		            count = 0;
		            while(rs.next())
		            {
		            
		            	   infomation[count][0] = String.valueOf(rs.getString("Orderform_id"));
		            	   infomation[count][1] = String.valueOf(rs.getString("Orderform_time"));
		            	   infomation[count][2] = String.valueOf(rs.getString("Customer_id"));
		            	   infomation[count][3] = String.valueOf(rs.getString("Supplier_id"));
		            	   infomation[count][4] = String.valueOf(rs.getString("Supplier_total"));
		            	count++;
		            }
		            String[] title = {"订单号","订单时间","顾客编号","供应商编号","交易量"};
		            table = new JTable(infomation,title);
		            table.setBounds(41, 122, 843, 372);
		            scrollPane.setViewportView(table);///加表格
		        }
		        catch(Exception e1)
		        {
		        	
		        }
			}
		});
		button_5.setBounds(282, 14, 113, 27);
		panel_4.add(button_5);
		panel_3.add(panel_5);
		JButton button_7 = new JButton("\u660E\u7EC6\u67E5\u770B");
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";

				try {
					Class.forName(jdbcDriver);// 加载数据库驱动
					String user = "sa";// 数据库用户名
					String password = "123456";// 数据库密码
					Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
					
				int count = 0;
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM Order_detail where Orderform_id='"+textField_2.getText()+"'");
				
				while (rs.next()) {
					count++;
				}
				//num+=count;
				Object[][] infomation = new Object[count][4];
				count = 0;
				rs = statement.executeQuery("SELECT * FROM Order_detail where Orderform_id='"+textField_2.getText()+"'");
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
			} 
				catch (Exception e1) {
				System.out.println("错误");
				}
			}
		});
		
		button_7.setBounds(405, 14, 113, 27);
		panel_4.add(button_7);
		panel_3.add(panel_5);
		
		JButton button_6 = new JButton("\u67E5\u8BE2");
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String jdbcDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		        String connectDB="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";//声明数据库DRMSDB的URL
		        ///上面两句驱动语句，在
		        try
		        {
		            Class.forName(jdbcDriver);//加载数据库驱动
		            String user="sa";//数据库用户名
		            String password="123456";//数据库密码
		            Connection connection=DriverManager.getConnection(connectDB,user,password);//建立数据库连接，获得连接对象
		            int count = 0;
		              Statement statement=connection.createStatement();//创建一个Statement对象 
		            ResultSet rs;
		               if(textField_3.getText().equals(""))
			            {		            	
			             rs =statement.executeQuery("SELECT * FROM Pay_message");//执行查询语句
			                while(rs.next()) {
			                	count++;
			                }
			             rs = statement.executeQuery("SELECT * FROM Pay_message");
			            }
		               else {
		            	   rs =statement.executeQuery("SELECT * FROM Pay_message where Orderform_id = '"+textField_3.getText()+"'");//执行查询语句
			                while(rs.next()) {
			                	count++;
			                }
			                rs =statement.executeQuery("SELECT * FROM Pay_message where Orderform_id = '"+textField_3.getText()+"'");//执行查询语句
		               }
		            Object[][] infomation = new Object[count][3];
		            count = 0;
		            while(rs.next())
		            {
		            
		            	   infomation[count][0] = String.valueOf(rs.getString("Orderform_id"));
		            	   infomation[count][1] = String.valueOf(rs.getString("Pay_money"));
		                   infomation[count][2] = String.valueOf(rs.getString("Pay_mode"));
		                  
		            	count++;
		            }
		            String[] title = {"订单号","支付总额","支付方式"};
		            table = new JTable(infomation,title);
		            table.setBounds(41, 122, 843, 372);
		            scrollPane.setViewportView(table);///加表格
		        }
		        catch(Exception e1)
		        {
		        	
		        }
				
			}
		});
		button_6.setBounds(282, 14, 113, 27);
		panel_5.add(button_6);
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 220, 660, 290);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
				
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.setVisible(true);
				panel_2.setVisible(false);
				panel_4.setVisible(false);
				panel_5.setVisible(false);
				panel_6.setVisible(false);
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
				panel_4.setVisible(false);
				panel_5.setVisible(false);
				panel_6.setVisible(false);
			}
		});
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				panel_4.setVisible(true);
				panel_5.setVisible(false);
				panel_6.setVisible(false);
			}
		});
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				panel_4.setVisible(false);
				panel_5.setVisible(true);
				panel_6.setVisible(false);
			}
		});
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				panel_4.setVisible(false);
				panel_5.setVisible(false);
				panel_6.setVisible(true);
			}
		});


	}
}