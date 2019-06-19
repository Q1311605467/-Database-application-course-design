package Frame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JList;
import javax.swing.JComboBox;

public class ZhiFu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JLabel label_1;
	JLabel label_2;
	JButton button_1;
	JButton button_2;

	JComboBox comboBox = new JComboBox();
	String ding;
	String money;
	String s;

	public ZhiFu() {
		setVisible(true);
		initialize();
		setLocationRelativeTo(null);

	}

	/**
	 * Create the dialog.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			label_1 = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
			label_1.setHorizontalAlignment(SwingConstants.LEFT);
			label_1.setBounds(64, 51, 305, 18);
			contentPanel.add(label_1);
		}
		{
			label_2 = new JLabel("\u652F\u4ED8\u603B\u989D\uFF1A");
			label_2.setHorizontalAlignment(SwingConstants.LEFT);
			label_2.setBounds(64, 90, 315, 18);
			contentPanel.add(label_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 216, 432, 37);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				button_1 = new JButton("\u786E\u8BA4\u652F\u4ED8");
				button_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							if (c() == 0) {
								setVisible(false);
								JOptionPane.showMessageDialog(null, "支付成功！");
							} else
								JOptionPane.showMessageDialog(null, "订单无效，请先添加订单！");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "支付失败！");
						}
					}
				});
				button_1.setBounds(174, 5, 93, 27);
				buttonPane.add(button_1);
			}
			{
				button_2 = new JButton("\u8FD4\u56DE");
				button_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
					}
				});
				button_2.setBounds(344, 5, 63, 27);
				buttonPane.add(button_2);
			}
		}
		{
			JLabel label = new JLabel("\u652F\u4ED8\u65B9\u5F0F\uFF1A");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setBounds(64, 129, 86, 18);
			contentPanel.add(label);
		}

		comboBox.addItem("支付宝");
		comboBox.addItem("银联卡");
		comboBox.addItem("现金");
		comboBox.setBounds(150, 126, 105, 24);
		contentPanel.add(comboBox);

	}

	public void set(String ding, String money) {
		this.ding = ding;
		this.money = money;
	}

	public int c() {
		// String s = "insert into Pay_message values ('" + textField.getText()
		// + "',"+money+",'" + a.comboBox.getSelectedItem() + "')";
		int count = 0;
	
		String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BRMSDB";
		try {
			Class.forName(jdbcDriver);// 加载数据库驱动
			String user = "sa";// 数据库用户名
			String password = "123456";// 数据库密码
			Connection connection = DriverManager.getConnection(connectDB, user, password);// 建立数据库连接，获得连接对象
			Statement statement = connection.createStatement();// 创建一个Statement对象

			s = "select * from Order_detail where Orderform_id = '" + ding + "'";
			ResultSet rs = statement.executeQuery(s);
			while (rs.next()) {
				count++;
			}
			if (count > 0) {
				s = "insert into Pay_message values ('" + ding + "'," + money + ",'" + comboBox.getSelectedItem()
						+ "')";
				rs = statement.executeQuery(s);
			}
			else
				return 1;

			// return 0;

			// while(rs.next()) {
			// System.out.println(rs);
			// }
		} catch (Exception e) {
		}
		return 0;
	}
}
